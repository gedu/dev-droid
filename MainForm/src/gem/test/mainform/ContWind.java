package gem.test.mainform;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

public class ContWind extends Activity{
	
	@Override	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i("COntWind","estoy en contWind");
		setContentView(R.layout.show_data);
		
		TextView viewName = (TextView)findViewById(R.id.contName);
		TextView viewSurname = (TextView)findViewById(R.id.contSurname);
		//TextView viewSex = (TextView)findViewById(R.id.sex);
		Toast toast3 = Toast.makeText(getApplicationContext(),"Hi there!",Toast.LENGTH_SHORT);
		toast3.show();
		Toast toast2 = Toast.makeText(getApplicationContext(),"Helo!",Toast.LENGTH_SHORT);
		toast2.show();
		Bundle bndInf = getIntent().getExtras();
		Log.d("ContWind","name: "+bndInf.getString("NAME"));
		viewName.setText("Tu nombre es: "+bndInf.getString("NAME"));
		viewSurname.setText("Tu apellido: "+bndInf.getString("SURNAME"));
		
	}
}
