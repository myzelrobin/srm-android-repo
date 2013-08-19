/**
 * 
 */
package com.srandroid.database;

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
	public static final String COLUMN_PATH = "filepath";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_IS_RECORDED = "is_recorded";
	public static final String COLUMN_SERVER_ID = "server_id"; // foreign key reference servers(id)
	

	
	// SQL statement CREATE TABLE scripts
	private static final String CREATE_TABLE_SCRIPTS = 
		"create table "
		+ TABLE_SCRIPTS
		+ " ( "
		+ COLUMN_ID + " integer primary key autoincrement, "
		+ COLUMN_PATH + " text not null, "
		+ COLUMN_DESCRIPTION + " text, "
		+ COLUMN_IS_RECORDED + " integer, "
		+ " FOREIGN KEY (" + COLUMN_SERVER_ID + ") REFERENCES servers(_id)"
		+ " );";
	
	// create table scripts
	public static void onCreate(SQLiteDatabase db)
	{
		Log.w(TableScripts.class.getName(), "onCreate(): will create table: " + TABLE_SCRIPTS);
		db.execSQL(CREATE_TABLE_SCRIPTS);
	}
	
	// upgrade table scripts
	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		Log.w(TableScripts.class.getName(), "onUpgrade(): will upgrade table: " + TABLE_SCRIPTS);
		db.execSQL("drop table if exists: " + TABLE_SCRIPTS);
		onCreate(db);
	}
}
