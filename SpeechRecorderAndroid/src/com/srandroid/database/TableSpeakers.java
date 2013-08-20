/**
 * 
 */
package com.srandroid.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/*
 *
 */
public class TableSpeakers 
{

	// Table Attributes
	public static final String TABLE_SPEAKERS = "speakers";
	public static final String COLUMN_ID = "_id"; // key
	public static final String COLUMN_FIRSTNAME = "firstname"; // text not null
	public static final String COLUMN_SURNAME = "surname"; // text not null
	public static final String COLUMN_ACCENT = "accent"; // text not null
	public static final String COLUMN_SEX = "sex"; // text not null 
	public static final String COLUMN_BIRTHDAY = "birthday"; // text
	
	
	// SQL statement CREATE TABLE speakers
	private static final String CREATE_TABLE_SPEAKERS = 
		"create table "
		+ TABLE_SPEAKERS
		+ " ( "
		+ COLUMN_ID + " integer primary key autoincrement, "
		+ COLUMN_FIRSTNAME + " text not null, "
		+ COLUMN_SURNAME + " text not null, "
		+ COLUMN_ACCENT + " text not null, "
		+ COLUMN_SEX + " text not null, "
		+ COLUMN_BIRTHDAY + " text "
		+ " );";
	
	// create table speakers
	public static void onCreate(SQLiteDatabase db)
	{
		Log.w(TableSpeakers.class.getName(), "onCreate(): will create table: " + TABLE_SPEAKERS);
		db.execSQL(CREATE_TABLE_SPEAKERS);
	}
	
	// upgrade table speakers
	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		Log.w(TableSpeakers.class.getName(), "onUpgrade(): will upgrade table: " + TABLE_SPEAKERS);
		db.execSQL("drop table if exists: " + TABLE_SPEAKERS);
		onCreate(db);
	}
}
