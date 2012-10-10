package gem.test.mainform;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.util.Log;

public class MainForm extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_form);
        final Intent tent = new Intent(MainForm.this,ContWind.class);
		final Bundle bndInfo = new Bundle();
		
		final EditText txtName = (EditText)findViewById(R.id.txtName);
		final EditText txtSurname = (EditText)findViewById(R.id.txtSurname);
        final Button btnContinue = (Button)findViewById(R.id.btnCont);
        final Button btnCancel = (Button)findViewById(R.id.btnCancl);
        final RadioGroup rg = (RadioGroup)findViewById(R.id.radioGbtns);
        rg.clearCheck();
        rg.check(R.id.radioBtnMale);
        btnContinue.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//int idRadioSelect = rg.getCheckedRadioButtonId();
				bndInfo.putString("NAME", txtName.getText().toString());
				bndInfo.putString("SURNAME", txtSurname.getText().toString());
				//bndInfo.putInt("RADIOBTN", idRadioSelect);
				Log.d("MainForm", "Name: "+bndInfo.getString("NAME"));
				tent.putExtras(bndInfo);
				startActivity(tent);
				
			}
		});
        btnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				confirmDialog();
				
			}
		});
    }
    private void confirmDialog(){
    	AlertDialog.Builder confirm = 	new AlertDialog.Builder(this);
    	confirm.setTitle("Confirmation");
    	confirm.setMessage("¿Ya te vas?");
    	confirm.setPositiveButton("Si", new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				System.exit(0);
							
			}
		});
    	confirm.setNegativeButton("No", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		});
		confirm.show();
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_form, menu);
        return true;
    }
}
