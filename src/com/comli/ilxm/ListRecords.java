package com.comli.ilxm;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

public class ListRecords extends ListActivity {
	private Database db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 db = EWalletActivity.db;
		 
		 Cursor records = db.getRecords();
		 SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_item, records,
	                new String[] { "amount", "description", "create_date" }, new int[] {R.id.item_amt, R.id.item_desc, R.id.item_date });
	        setListAdapter(adapter);
		 
	}
}
