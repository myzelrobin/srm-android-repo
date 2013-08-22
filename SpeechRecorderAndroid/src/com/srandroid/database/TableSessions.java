/**
 * 
 */
package com.srandroid.database;

import com.srandroid.util.Utils;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/*
 *
 */
public class TableSessions 
{

	// Table Attributes
	public static final String TABLE_SESSIONS = "sessions";
	public static final String COLUMN_ID = "_id"; // key
	public static final String COLUMN_DATE = "date"; // text not null
	public static final String COLUMN_TIME = "time"; // text not null
	public static final String COLUMN_PLACE = "place"; // text not null
	public static final String COLUMN_DEVICE_DATA = "device_data"; // text not null
	public static final String COLUMN_GPS_DATA = "gps_data"; // text, android needs a little time to set GPS sensors, only get it when is needed
	public static final String COLUMN_IS_FINISHED = "is_finished"; // text not null finished/unfinished
	public static final String COLUMN_COUNT = "count"; // integer
	public static final String COLUMN_LAST_SECTION = "last_section"; // text 
	public static final String COLUMN_SCRIPT_ID = "script_id"; // foreign key reference scripts(_id)
	public static final String COLUMN_SPEAKER_ID = "speaker_id"; // foreign key reference speakers(_id)
	
	// SQL statement CREATE TABLE sessions
	private static final String CREATE_TABLE_SESSIONS = 
		"create table "
		+ TABLE_SESSIONS
		+ " ( "
		+ COLUMN_ID + " integer primary key autoincrement, "
		+ COLUMN_DATE + " text not null, "
		+ COLUMN_TIME + " text not null, "
		+ COLUMN_PLACE + " text not null, "
		+ COLUMN_DEVICE_DATA + " text not null, "
		+ COLUMN_GPS_DATA + " text, "
		+ COLUMN_IS_FINISHED + " text not null, "
		+ COLUMN_COUNT + " integer, "
		+ COLUMN_LAST_SECTION + " text, "
		+ COLUMN_SCRIPT_ID + " integer, "
		+ COLUMN_SPEAKER_ID + " integer, "
		+ " FOREIGN KEY (" + COLUMN_SCRIPT_ID + ") REFERENCES scripts(_id),"
		+ " FOREIGN KEY (" + COLUMN_SPEAKER_ID + ") REFERENCES speakers(_id)"
		+ " );";
	
	// create table sessions
	public static void onCreate(SQLiteDatabase db)
	{
		Log.w(TableSessions.class.getName(), "onCreate(): will create table: " + TABLE_SESSIONS);
		db.execSQL(CREATE_TABLE_SESSIONS);
		insertSessionExamples(db);
	}
	
	// upgrade table sessions
	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		Log.w(TableSessions.class.getName(), "onUpgrade(): will upgrade table: " + TABLE_SESSIONS);
		db.execSQL("drop table if exists " + TABLE_SESSIONS + ";\n");
		onCreate(db);
	}
	
	public static void insertSessionExamples(SQLiteDatabase db) 
	{
		Log.w(TableSessions.class.getName(), "insertSessionExamples() will insert examples");
		ContentValues values = new ContentValues(); 
		
		for(int i=1; i<6; i++)
		{
			values.put(TableSessions.COLUMN_DATE, "1234-56-78");
			values.put(TableSessions.COLUMN_TIME, "12-34-56");
			values.put(TableSessions.COLUMN_PLACE, "Munich");
			values.put(TableSessions.COLUMN_DEVICE_DATA, Utils.ConstantVars.DEVICE_ID);
			values.put(TableSessions.COLUMN_GPS_DATA, Utils.ConstantVars.GPS_INFO);
			values.put(TableSessions.COLUMN_IS_FINISHED, "finished");
			values.put(TableSessions.COLUMN_COUNT, "0");
			values.put(TableSessions.COLUMN_SCRIPT_ID, ""+i);
			values.put(TableSessions.COLUMN_SPEAKER_ID, ""+i);
			db.insert(TableSessions.TABLE_SESSIONS, null, values);
			
			values.put(TableSessions.COLUMN_DATE, "9876-54-32");
			values.put(TableSessions.COLUMN_TIME, "23-59-59");
			values.put(TableSessions.COLUMN_PLACE, "Berlin");
			values.put(TableSessions.COLUMN_DEVICE_DATA, Utils.ConstantVars.DEVICE_ID);
			values.put(TableSessions.COLUMN_GPS_DATA, Utils.ConstantVars.GPS_INFO);
			values.put(TableSessions.COLUMN_IS_FINISHED, "unfinished");
			values.put(TableSessions.COLUMN_COUNT, "0");
			values.put(TableSessions.COLUMN_SCRIPT_ID, ""+i);
			values.put(TableSessions.COLUMN_SPEAKER_ID, ""+i);
			db.insert(TableSessions.TABLE_SESSIONS, null, values);
		}
		
	}
	
}
