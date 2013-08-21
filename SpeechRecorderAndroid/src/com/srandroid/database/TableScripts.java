/**
 * 
 */
package com.srandroid.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/*
 *
 */
public class TableScripts 
{

	// Table Attributes
	public static final String TABLE_SCRIPTS = "scripts";
	public static final String COLUMN_ID = "_id"; // key
	public static final String COLUMN_FILEPATH = "filepath";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_SERVER_ID = "server_id"; // foreign key reference servers(id)
	

	
	// SQL statement CREATE TABLE scripts
	private static final String CREATE_TABLE_SCRIPTS = 
		"create table "
		+ TABLE_SCRIPTS
		+ " ( "
		+ COLUMN_ID + " integer primary key autoincrement, "
		+ COLUMN_FILEPATH + " text not null, "
		+ COLUMN_DESCRIPTION + " text not null, "
		+ COLUMN_SERVER_ID + " integer, "
		+ " FOREIGN KEY (" + COLUMN_SERVER_ID + ") REFERENCES servers(_id)"
		+ " );";
	
	// create table scripts
	public static void onCreate(SQLiteDatabase db)
	{
		Log.w(TableScripts.class.getName(), "onCreate(): will create table: " + TABLE_SCRIPTS);
		db.execSQL(CREATE_TABLE_SCRIPTS);
		insertScriptExamples(db);
	}
	
	// upgrade table scripts
	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		Log.w(TableScripts.class.getName(), "onUpgrade(): will upgrade table: " + TABLE_SCRIPTS);
		db.execSQL("drop table if exists " + TABLE_SCRIPTS  + ";\n");
		onCreate(db);
	}
	
	public static void insertScriptExamples(SQLiteDatabase db) 
	{
		Log.w(TableScripts.class.getName(), "insertScriptExamples() will insert examples");
		ContentValues values = new ContentValues(); 
		for(int i=1; i<6; i++)
		{
			values.put(TableScripts.COLUMN_FILEPATH, "/mnt/sdcard/srandroid_testfolder/scripts/script_exp1.xml");
			values.put(TableScripts.COLUMN_DESCRIPTION, "Example script A"+i);
			values.put(TableScripts.COLUMN_SERVER_ID, "2");
			db.insert(TableScripts.TABLE_SCRIPTS, null, values);
			
			values.put(TableScripts.COLUMN_FILEPATH, "/mnt/sdcard/srandroid_testfolder/scripts/script_exp2.xml");
			values.put(TableScripts.COLUMN_DESCRIPTION, "Example script B"+i);
			values.put(TableScripts.COLUMN_SERVER_ID, "1");
			db.insert(TableScripts.TABLE_SCRIPTS, null, values);
		}
		
	}
	
}
