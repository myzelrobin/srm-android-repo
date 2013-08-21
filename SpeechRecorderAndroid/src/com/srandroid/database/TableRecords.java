/**
 * 
 */
package com.srandroid.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/*
 *
 */
public class TableRecords 
{

	// Table Attributes
	public static final String TABLE_RECORDS = "records";
	public static final String COLUMN_ID = "_id"; // key
	public static final String COLUMN_FILEPATH = "filepath"; // text not null
	public static final String COLUMN_SECTION_ID = "section_id"; // foreign key reference sections(_id)
	
	// SQL statement CREATE TABLE records
	private static final String CREATE_TABLE_RECORDS = 
		"create table "
		+ TABLE_RECORDS
		+ " ( "
		+ COLUMN_ID + " integer primary key autoincrement, "
		+ COLUMN_FILEPATH + " text not null, "
		+ COLUMN_SECTION_ID + " integer, "
		+ " FOREIGN KEY (" + COLUMN_SECTION_ID + ") REFERENCES sections(_id)"
		+ " );";
	
	// create table records
	public static void onCreate(SQLiteDatabase db)
	{
		Log.w(TableRecords.class.getName(), "onCreate(): will create table: " + TABLE_RECORDS);
		db.execSQL(CREATE_TABLE_RECORDS);
	}
	
	// upgrade table records
	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		Log.w(TableRecords.class.getName(), "onUpgrade(): will upgrade table: " + TABLE_RECORDS);
		db.execSQL("drop table if exists " + TABLE_RECORDS  + ";\n");
		onCreate(db);
	}
}
