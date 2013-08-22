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
		insertSpeakerExamples(db);
	}
	
	// upgrade table speakers
	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		Log.w(TableSpeakers.class.getName(), "onUpgrade(): will upgrade table: " + TABLE_SPEAKERS);
		db.execSQL("drop table if exists " + TABLE_SPEAKERS + ";\n");
		onCreate(db);
	}
	
	
	public static void insertSpeakerExamples(SQLiteDatabase db) 
	{
		Log.w(TableSpeakers.class.getName(), "insertSpeakerExamples() will insert examples");
		ContentValues values = new ContentValues(); 

		for(int i = 1; i < 6; i++)
		{
			values.put(TableSpeakers.COLUMN_FIRSTNAME, "Testman" + "#" + i);
			values.put(TableSpeakers.COLUMN_SURNAME, "Exp");
			values.put(TableSpeakers.COLUMN_SEX, "male");
			values.put(TableSpeakers.COLUMN_ACCENT, "bayerisch");
			db.insert(TableSpeakers.TABLE_SPEAKERS, null, values);
			
			values.put(TableSpeakers.COLUMN_FIRSTNAME, "Testwoman" + "#" + i);
			values.put(TableSpeakers.COLUMN_SURNAME, "Exp");
			values.put(TableSpeakers.COLUMN_SEX, "female");
			values.put(TableSpeakers.COLUMN_ACCENT, "bayerisch");
			db.insert(TableSpeakers.TABLE_SPEAKERS, null, values);
			
		}
		
		
	}
	

	public static class SpeakerItem {

		private String item_id = "speaker#";
		private String key_id = null; // key
		private String firstname = null; // text not null
		private String surname = null; // text not null
		private String accent = null; // text not null
		private String sex = null; // text not null 
		private String birthday = null; // text
		
		
		public SpeakerItem() 
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
		 * @return the firstname
		 */
		public String getFirstname() {
			return firstname;
		}


		/**
		 * @param firstname the firstname to set
		 */
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}


		/**
		 * @return the surname
		 */
		public String getSurname() {
			return surname;
		}


		/**
		 * @param surname the surname to set
		 */
		public void setSurname(String surname) {
			this.surname = surname;
		}


		/**
		 * @return the accent
		 */
		public String getAccent() {
			return accent;
		}


		/**
		 * @param accent the accent to set
		 */
		public void setAccent(String accent) {
			this.accent = accent;
		}


		/**
		 * @return the sex
		 */
		public String getSex() {
			return sex;
		}


		/**
		 * @param sex the sex to set
		 */
		public void setSex(String sex) {
			this.sex = sex;
		}


		/**
		 * @return the birthday
		 */
		public String getBirthday() {
			return birthday;
		}


		/**
		 * @param birthday the birthday to set
		 */
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

	}

	
}
