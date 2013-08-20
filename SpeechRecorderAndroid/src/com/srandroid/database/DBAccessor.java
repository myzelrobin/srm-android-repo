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
		TableSpeakers.onCreate(db);
		TableServers.onCreate(db);
		TableScripts.onCreate(db);
		TableSessions.onCreate(db);
		TableSections.onCreate(db);
		TableRecords.onCreate(db);
		
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
			TableSpeakers.onUpgrade(db, oldVersion, newVersion);
			TableServers.onUpgrade(db, oldVersion, newVersion);
			TableScripts.onUpgrade(db, oldVersion, newVersion);
			TableSessions.onUpgrade(db, oldVersion, newVersion);
			TableSections.onUpgrade(db, oldVersion, newVersion);
			TableRecords.onUpgrade(db, oldVersion, newVersion);
	}
	
	private void insertExamples(SQLiteDatabase db)
	{
		Log.w(DBAccessor.class.getName(), "insertExamples() will insert examples");
		TableSpeakers.insertSpeakerExamples(db);
		TableServers.insertServerExamples(db);
		TableScripts.insertScriptExamples(db);
		TableSessions.insertSessionExamples(db);
		// TableSections.insertScriptExamples(db);
		// TableRecords.insertScriptExamples(db);
		
	}

	
}
