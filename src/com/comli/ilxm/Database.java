package com.comli.ilxm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ewallet";
	private static final String RECORD_TABLE_CREATE = "CREATE TABLE records (_id INTEGER PRIMARY KEY AUTOINCREMENT, amount NUMERIC, create_date TEXT, description TEXT);";
	private SQLiteDatabase myDb;
	
	public Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		if (myDb==null) {
			myDb = getWritableDatabase();
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(RECORD_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void runSql(String sql) {
		myDb.execSQL(sql);
	}
	
	public void insertRecord(float amt, String desc, String date) {
		ContentValues cv = new ContentValues();
		cv.put("amount", amt);
		cv.put("create_date", date);
		cv.put("description", desc);
		myDb.insert("records", null, cv);
	}

	public Cursor getRecords() {
		return myDb.query("records", null, null, null, null, null, null);
	}
}
