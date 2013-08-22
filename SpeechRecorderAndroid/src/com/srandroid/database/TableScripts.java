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


	public static class ScriptItem {

		private String item_id = "script#";
		private String key_id = null; // key
		private String filepath = null;
		private String description = null;
		private String server_id = null;
		
		public ScriptItem() {
			// TODO Auto-generated constructor stub
		}

		/**
		 * @return the item_id
		 */
		public String getItem_id() {
			return item_id;
		}

		/**
		 * @param item_id the item_id to set
		 */
		public void setItem_id(String item_id) {
			this.item_id = item_id;
		}

		/**
		 * @return the key_id
		 */
		public String getKey_id() {
			return key_id;
		}

		/**
		 * @param key_id the key_id to set
		 */
		public void setKey_id(String key_id) {
			this.key_id = key_id;
		}

		/**
		 * @return the filepath
		 */
		public String getFilepath() {
			return filepath;
		}

		/**
		 * @param filepath the filepath to set
		 */
		public void setFilepath(String filepath) {
			this.filepath = filepath;
		}

		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}

		/**
		 * @return the server_id
		 */
		public String getServer_id() {
			return server_id;
		}

		/**
		 * @param server_id the server_id to set
		 */
		public void setServer_id(String server_id) {
			this.server_id = server_id;
		}

	}

}
