/**
 * 
 */
package com.srandroid.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/*
 *
 */
public class TableServers 
{

	// Table Attributes
	public static final String TABLE_SERVERS = "servers";
	public static final String COLUMN_ID = "_id"; // key
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_PASSWORD = "password"; 
	

	
	// SQL statement CREATE TABLE servers
	private static final String CREATE_TABLE_SERVERS = 
		"create table "
		+ TABLE_SERVERS
		+ " ( "
		+ COLUMN_ID + " integer primary key autoincrement, "
		+ COLUMN_ADDRESS + " text not null, "
		+ COLUMN_USERNAME + " text, "
		+ COLUMN_PASSWORD + " text, "
		+ COLUMN_DESCRIPTION + " text "
		+ " );";
	
	// create table servers
	public static void onCreate(SQLiteDatabase db)
	{
		Log.w(TableServers.class.getName(), "onCreate(): will create table: " + TABLE_SERVERS);
		db.execSQL(CREATE_TABLE_SERVERS);
	}
	
	// upgrade table servers
	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		Log.w(TableServers.class.getName(), "onUpgrade(): will upgrade table: " + TABLE_SERVERS);
		db.execSQL("drop table if exists: " + TABLE_SERVERS);
		onCreate(db);
	}
}
