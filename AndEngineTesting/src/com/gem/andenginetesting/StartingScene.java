package com.gem.andenginetesting;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.sensor.acceleration.AccelerationData;
import org.andengine.input.sensor.acceleration.IAccelerationListener;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

import android.util.Log;
import android.widget.Toast;

public class StartingScene extends SimpleBaseGameActivity  implements IAccelerationListener, IOnSceneTouchListener{
	public static final int CAMERA_WIDTH = 720;
	public static final int CAMERA_HEIGHT = 480;
	
	private Camera mCamera;
	private Scene sScene;
	private BitmapTextureAtlas mBitmapTextureAtlas;
	private ITextureRegion bean;
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR,
				new FillResolutionPolicy(), mCamera);
	}

	@Override
	protected void onCreateResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		
		this.mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		this.bean = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas,this,"jellyBeanRedTiny.png",0,0);
		this.mBitmapTextureAtlas.load();
	}

	@Override
	protected Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());
		
		this.sScene = new Scene();
		this.sScene.setBackground(new Background(0,0,0));
		this.sScene.setOnSceneTouchListener(this);
		/*final float centerX = (CAMERA_WIDTH - this.bean.getWidth()) / 2;
		final float centerY = (CAMERA_HEIGHT - this.bean.getHeight()) / 2;
		final Sprite beanDrag = new Sprite(centerX, centerY, this.bean,this.getVertexBufferObjectManager()){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float areaX, final float areaY){
				this.setPosition(pSceneTouchEvent.getX() - this.getWidth()/2, pSceneTouchEvent.getY() - this.getHeight()/2);
				return true;
			}
		};
		beanDrag.setScale(4);
		this.sScene.attachChild(beanDrag);
		this.sScene.registerTouchArea(beanDrag);
		this.sScene.setTouchAreaBindingOnActionDownEnabled(true);*/
		return this.sScene;
		
	}
	@Override
	public boolean onSceneTouchEvent(final Scene pScene,final TouchEvent pSceneTouchEvent){
		if(pSceneTouchEvent.isActionDown()){
			this.addBean(pSceneTouchEvent.getX(),pSceneTouchEvent.getY());
			return true;
		}
		return false;
	}
	private void addBean(final float pX, final float pY){
		final Sprite beanDrag = new Sprite(pX, pY, this.bean,this.getVertexBufferObjectManager()){
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float areaX, final float areaY){
				this.setPosition(pSceneTouchEvent.getX() - this.getWidth()/2, pSceneTouchEvent.getY() - this.getHeight()/2);
				return true;
			}
		};
		beanDrag.setScale(4);
		this.sScene.attachChild(beanDrag);
		this.sScene.registerTouchArea(beanDrag);
		this.sScene.setTouchAreaBindingOnActionDownEnabled(true);
	}

	@Override
	public void onAccelerationAccuracyChanged(AccelerationData pAccelerationData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAccelerationChanged(AccelerationData pAccelerationData) {
		// TODO Auto-generated method stub
		
	}
	
}
