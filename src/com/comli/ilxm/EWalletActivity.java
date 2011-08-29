package com.comli.ilxm;


import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
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
	static final int DEFAULTDATESELECTOR_ID = 0;
	EditText dateTxt, amount, description;
	Button addNew;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        db = new Database(this);
        
        amount = (EditText) findViewById(R.id.amount);
        description = (EditText) findViewById(R.id.description);
        addNew = (Button) findViewById(R.id.add_new_record);
        dateTxt = (EditText) findViewById(R.id.date);
        addNew.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				float amt = Float.parseFloat(amount.getText().toString());
				String desc = description.getText().toString();
				String dat = dateTxt.getText().toString();
				db.insertRecord(amt, desc, dat);
				
				amount.setText("");
				description.setText("");
			}
		});
        final Button timeSlider = (Button) findViewById(R.id.show_timeSlider);
        
        timeSlider.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				showDialog(DEFAULTDATESELECTOR_ID);
			}
		});
    }
    
    // define the listener which is called once a user selected the date.
    private DateSlider.OnDateSetListener mDateSetListener =
        new DateSlider.OnDateSetListener() {
            public void onDateSet(DateSlider view, Calendar selectedDate) {
                // update the dateText view with the corresponding date
                dateTxt.setText(String.format("%n%te. %tB %tY", selectedDate, selectedDate, selectedDate));
            }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        // this method is called after invoking 'showDialog' for the first time
        // here we initiate the corresponding DateSlideSelector and return the dialog to its caller
    	
    	// get today's date and time
        final Calendar c = Calendar.getInstance();
        
        switch (id) {
        case DEFAULTDATESELECTOR_ID:
            return new DefaultDateSlider(this,mDateSetListener,c);
        }
        return null;
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