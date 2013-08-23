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
	private static final int DATABASE_VERSION = 6;
	

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

	public static final String[] AVAILABLE_COLUMNS = 
		{
			TableRecords.TABLE_RECORDS,
			TableRecords.COLUMN_ID, 
			TableRecords.COLUMN_FILEPATH,
			TableRecords.COLUMN_SECTION_ID,
			TableRecords.COLUMN_ISUPLOADED,
			TableScripts.COLUMN_DESCRIPTION,
			TableScripts.COLUMN_FILEPATH,
			TableScripts.COLUMN_ID,
			TableScripts.COLUMN_SERVER_ID,
			TableSections.COLUMN_ID,
			TableSections.COLUMN_SCRIPT_ID,
			TableSections.COLUMN_SECTIONID_INSCRIPT,
			TableServers.COLUMN_ADDRESS,
			TableServers.COLUMN_DESCRIPTION,
			TableServers.COLUMN_ID,
			TableServers.COLUMN_PASSWORD,
			TableServers.COLUMN_USERNAME,
			TableSessions.COLUMN_COUNT,
			TableSessions.COLUMN_DATE,
			TableSessions.COLUMN_DEVICE_DATA,
			TableSessions.COLUMN_GPS_DATA,
			TableSessions.COLUMN_ID,
			TableSessions.COLUMN_IS_FINISHED,
			TableSessions.COLUMN_PLACE,
			TableSessions.COLUMN_SCRIPT_ID,
			TableSessions.COLUMN_SPEAKER_ID,
			TableSessions.COLUMN_TIME,
			TableSessions.COLUMN_LAST_SECTION,
			TableSpeakers.COLUMN_ACCENT,
			TableSpeakers.COLUMN_BIRTHDAY,
			TableSpeakers.COLUMN_FIRSTNAME,
			TableSpeakers.COLUMN_ID,
			TableSpeakers.COLUMN_SEX,
			TableSpeakers.COLUMN_SURNAME,
			"speaker_key_id", // for join query
			"script_key_id",
			"server_key_id",
			"session_key_id",
			"section_key_id",
			"record_key_id"
	};
	
}
