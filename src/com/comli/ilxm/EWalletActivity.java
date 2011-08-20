package com.comli.ilxm;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class EWalletActivity extends Activity {
	public static Database db;
	public static String TAG = "eWallet";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        db = new Database(this);
        
        final EditText amount = (EditText) findViewById(R.id.amount);
        final EditText description = (EditText) findViewById(R.id.description);
        final DatePicker createDate = (DatePicker) findViewById(R.id.datePicker1);
        final TimePicker createTime = (TimePicker) findViewById(R.id.timePicker1);
        final Button addNew = (Button) findViewById(R.id.add_new_record);
        addNew.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				float amt = Float.parseFloat(amount.getText().toString());
				String desc = description.getText().toString();
				int cDate_day = createDate.getDayOfMonth();
				int cDate_month = createDate.getMonth()+1;
				int cDate_year = createDate.getYear();
				int cTime_hour = createTime.getCurrentHour();
				int cTime_min = createTime.getCurrentMinute();
				String dateTime = cDate_year+"/"+cDate_month+"/"+cDate_day+" "+cTime_hour+":"+cTime_min;
				db.insertRecord(amt, desc, dateTime);
				
				amount.setText("");
				description.setText("");
			}
		});
    }
    
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	menu.add("View List");
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
    	if (item.getTitle().equals("View List") ) {
    		Intent i = new Intent("com.comli.ilxm.intent.action.listRecords");
    		startActivity(i);
    		Log.d(TAG, "view lsit selected");
    	}
    	return true;
    }
}