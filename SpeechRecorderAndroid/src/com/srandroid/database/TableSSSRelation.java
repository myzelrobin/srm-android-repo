/**
 * 
 */
package com.srandroid.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/*
 *
 */
public class TableSSSRelation 
{

	// Table Attributes
	public static final String TABLE_SSSRELATION = "session_speaker_script_relation";
	public static final String COLUMN_ID = "_id"; // key
	public static final String COLUMN_SESSION_ID = "session_id"; // foreign key reference sessions(id)
	public static final String COLUMN_SPEAKER_ID = "speaker_id"; // foreign key reference speakers(id)
	public static final String COLUMN_SCRIPT_ID = "script_id"; // foreign key reference script(id) 
	public static final String COLUMN_IS_RECORDED = "is_recorded"; 
	

	
	// SQL statement CREATE TABLE servers
	private static final String CREATE_TABLE_SSSRELATION = 
		"create table "
		+ TABLE_SSSRELATION
		+ " ( "
		+ COLUMN_ID + " integer primary key autoincrement, "
		+ " FOREIGN KEY (" + COLUMN_SESSION_ID + ") REFERENCES sessions(_id)"
		+ " FOREIGN KEY (" + COLUMN_SPEAKER_ID + ") REFERENCES speakers(_id)"
		+ " FOREIGN KEY (" + COLUMN_SCRIPT_ID + ") REFERENCES scripts(_id)"
		+ COLUMN_IS_RECORDED + " integer "
		+ " );";
	
	// create table servers
	public static void onCreate(SQLiteDatabase db)
	{
		Log.w(TableSSSRelation.class.getName(), "onCreate(): will create table: " + TABLE_SSSRELATION);
		db.execSQL(CREATE_TABLE_SSSRELATION);
	}
	
	// upgrade table servers
	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		Log.w(TableSSSRelation.class.getName(), "onUpgrade(): will upgrade table: " + TABLE_SSSRELATION);
		db.execSQL("drop table if exists: " + TABLE_SSSRELATION);
		onCreate(db);
	}
}
