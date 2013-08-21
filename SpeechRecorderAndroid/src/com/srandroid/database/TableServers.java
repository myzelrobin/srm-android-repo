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
public class TableServers 
{

	// Table Attributes
	public static final String TABLE_SERVERS = "servers";
	public static final String COLUMN_ID = "_id"; // key
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_PASSWORD = "password"; 
	public static final String COLUMN_DESCRIPTION = "description";
	

	
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
		db.execSQL("drop table if exists " + TABLE_SERVERS + ";\n");
		onCreate(db);
	}
	
	public static  void insertServerExamples(SQLiteDatabase db) 
	{
		Log.w(TableServers.class.getName(), "insertServerExamples() will insert examples");
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
}
