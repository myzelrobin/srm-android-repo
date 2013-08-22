/**
 * 
 */
package com.srandroid.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/*
 *
 */
public class TableSections 
{

	// Table Attributes
	public static final String TABLE_SECTIONS = "sections";
	public static final String COLUMN_ID = "_id"; // key
	public static final String COLUMN_SECTIONID_INSCRIPT = "sctionid_inscript"; // text not null
	public static final String COLUMN_SCRIPT_ID = "script_id"; // foreign key reference scritps(_id)
	
	// SQL statement CREATE TABLE sections
	private static final String CREATE_TABLE_SECTIONS = 
		"create table "
		+ TABLE_SECTIONS
		+ " ( "
		+ COLUMN_ID + " integer primary key autoincrement, "
		+ COLUMN_SECTIONID_INSCRIPT + " text not null, "
		+ COLUMN_SCRIPT_ID + " integer, "
		+ " FOREIGN KEY (" + COLUMN_SCRIPT_ID + ") REFERENCES scripts(_id)"
		+ " );";
	
	// create table sections
	public static void onCreate(SQLiteDatabase db)
	{
		Log.w(TableSections.class.getName(), "onCreate(): will create table: " + TABLE_SECTIONS);
		db.execSQL(CREATE_TABLE_SECTIONS);
	}
	
	// upgrade table sections
	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		Log.w(TableSections.class.getName(), "onUpgrade(): will upgrade table: " + TABLE_SECTIONS);
		db.execSQL("drop table if exists " + TABLE_SECTIONS  + ";\n");
		onCreate(db);
	}
	

	public static class SectionItem {

		
		private String item_id = "section#";
		private String key_id = null; // key
		private String sectionIdInScript = null; // section id in script
		private String script_id= null;
		
		
		public SectionItem() 
		{
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
		 * @return the sectionIdInScript
		 */
		public String getSectionIdInScript() {
			return sectionIdInScript;
		}


		/**
		 * @param sectionIdInScript the sectionIdInScript to set
		 */
		public void setSectionIdInScript(String sectionIdInScript) {
			this.sectionIdInScript = sectionIdInScript;
		}


		/**
		 * @return the script_id
		 */
		public String getScript_id() {
			return script_id;
		}


		/**
		 * @param script_id the script_id to set
		 */
		public void setScript_id(String script_id) {
			this.script_id = script_id;
		}

	}

}
