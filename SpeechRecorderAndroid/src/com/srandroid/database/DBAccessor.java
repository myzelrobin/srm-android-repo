/**
 * 
 */
package com.srandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 *
 */
public class DBAccessor extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME = "srandroid_database.db";
	private static final int DATABASE_VERSION = 1;
	

	public DBAccessor(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// create database, create tables
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		Log.w(DBAccessor.class.getName(), "onCreate() will create database");
		TableServers.onCreate(db);
		TableScripts.onCreate(db);
		
		TableSSSRelation.onCreate(db);
		
		insertExamples(db);
	}

	// upgrade database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		Log.w(DBAccessor.class.getName(), 
				"will upgrade database from version " 
				+ oldVersion + " to " + newVersion 
				+ ", which will destroy all old data");
			TableServers.onUpgrade(db, oldVersion, newVersion);
			TableScripts.onUpgrade(db, oldVersion, newVersion);
			TableSSSRelation.onUpgrade(db, oldVersion, newVersion);
	}
	
	private void insertExamples(SQLiteDatabase db)
	{
		insertServerExamples(db);
		insertScriptExamples(db);
	}

	private void insertServerExamples(SQLiteDatabase db) 
	{
		Log.w(DBAccessor.class.getName(), "insertServerExamples() will insert examples");
		ContentValues values = new ContentValues(); 
		
		values.put(TableServers.COLUMN_ADDRESS, "1.1.1.1:8080");
		values.put(TableServers.COLUMN_DESCRIPTION, "Example server 1");
		values.put(TableServers.COLUMN_USERNAME, "testuser");
		values.put(TableServers.COLUMN_PASSWORD, "11111111");
		db.insert(TableServers.TABLE_SERVERS, null, values);
		
		values.put(TableServers.COLUMN_ADDRESS, "2.2.2.2:8080");
		values.put(TableServers.COLUMN_DESCRIPTION, "Example server 2");
		values.put(TableServers.COLUMN_USERNAME, "testuser2");
		values.put(TableServers.COLUMN_PASSWORD, "22222222");
		db.insert(TableServers.TABLE_SERVERS, null, values);
		
	}

	private void insertScriptExamples(SQLiteDatabase db) 
	{
		Log.w(DBAccessor.class.getName(), "insertScriptExamples() will insert examples");
		ContentValues values = new ContentValues(); 
		
		values.put(TableScripts.COLUMN_PATH, "/mnt/sdcard/srandroid_testfolder/scripts/script_exp1.xml");
		values.put(TableScripts.COLUMN_DESCRIPTION, "Example script 1");
		values.put(TableScripts.COLUMN_IS_RECORDED, "2");
		values.put(TableScripts.COLUMN_SERVER_ID, "2");
		db.insert(TableScripts.TABLE_SCRIPTS, null, values);
		
		values.put(TableScripts.COLUMN_PATH, "/mnt/sdcard/srandroid_testfolder/scripts/script_exp2.xml");
		values.put(TableScripts.COLUMN_DESCRIPTION, "Example script 2");
		values.put(TableScripts.COLUMN_IS_RECORDED, "5");
		values.put(TableScripts.COLUMN_SERVER_ID, "1");
		db.insert(TableScripts.TABLE_SCRIPTS, null, values);
	}
	
}
