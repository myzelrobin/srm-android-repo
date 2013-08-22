/**
 * 
 */
package com.srandroid.database;

import com.srandroid.util.Utils;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/*
 *
 */
public class TableSessions 
{

	// Table Attributes
	public static final String TABLE_SESSIONS = "sessions";
	public static final String COLUMN_ID = "_id"; // key
	public static final String COLUMN_DATE = "date"; // text not null
	public static final String COLUMN_TIME = "time"; // text not null
	public static final String COLUMN_PLACE = "place"; // text not null
	public static final String COLUMN_DEVICE_DATA = "device_data"; // text not null
	public static final String COLUMN_GPS_DATA = "gps_data"; // text, android needs a little time to set GPS sensors, only get it when is needed
	public static final String COLUMN_IS_FINISHED = "is_finished"; // text not null finished/unfinished
	public static final String COLUMN_COUNT = "count"; // integer
	public static final String COLUMN_LAST_SECTION = "last_section"; // text 
	public static final String COLUMN_SCRIPT_ID = "script_id"; // foreign key reference scripts(_id)
	public static final String COLUMN_SPEAKER_ID = "speaker_id"; // foreign key reference speakers(_id)
	
	// SQL statement CREATE TABLE sessions
	private static final String CREATE_TABLE_SESSIONS = 
		"create table "
		+ TABLE_SESSIONS
		+ " ( "
		+ COLUMN_ID + " integer primary key autoincrement, "
		+ COLUMN_DATE + " text not null, "
		+ COLUMN_TIME + " text not null, "
		+ COLUMN_PLACE + " text not null, "
		+ COLUMN_DEVICE_DATA + " text not null, "
		+ COLUMN_GPS_DATA + " text, "
		+ COLUMN_IS_FINISHED + " text not null, "
		+ COLUMN_COUNT + " integer, "
		+ COLUMN_LAST_SECTION + " text, "
		+ COLUMN_SCRIPT_ID + " integer, "
		+ COLUMN_SPEAKER_ID + " integer, "
		+ " FOREIGN KEY (" + COLUMN_SCRIPT_ID + ") REFERENCES scripts(_id),"
		+ " FOREIGN KEY (" + COLUMN_SPEAKER_ID + ") REFERENCES speakers(_id)"
		+ " );";
	
	// create table sessions
	public static void onCreate(SQLiteDatabase db)
	{
		Log.w(TableSessions.class.getName(), "onCreate(): will create table: " + TABLE_SESSIONS);
		db.execSQL(CREATE_TABLE_SESSIONS);
		insertSessionExamples(db);
	}
	
	// upgrade table sessions
	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		Log.w(TableSessions.class.getName(), "onUpgrade(): will upgrade table: " + TABLE_SESSIONS);
		db.execSQL("drop table if exists " + TABLE_SESSIONS + ";\n");
		onCreate(db);
	}
	
	public static void insertSessionExamples(SQLiteDatabase db) 
	{
		Log.w(TableSessions.class.getName(), "insertSessionExamples() will insert examples");
		ContentValues values = new ContentValues(); 
		
		for(int i=1; i<6; i++)
		{
			values.put(TableSessions.COLUMN_DATE, "1234-56-78");
			values.put(TableSessions.COLUMN_TIME, "12-34-56");
			values.put(TableSessions.COLUMN_PLACE, "Munich");
			values.put(TableSessions.COLUMN_DEVICE_DATA, Utils.ConstantVars.DEVICE_ID);
			values.put(TableSessions.COLUMN_GPS_DATA, Utils.ConstantVars.GPS_INFO);
			values.put(TableSessions.COLUMN_IS_FINISHED, "finished");
			values.put(TableSessions.COLUMN_COUNT, "0");
			values.put(TableSessions.COLUMN_SCRIPT_ID, ""+i);
			values.put(TableSessions.COLUMN_SPEAKER_ID, ""+i);
			db.insert(TableSessions.TABLE_SESSIONS, null, values);
			
			values.put(TableSessions.COLUMN_DATE, "9876-54-32");
			values.put(TableSessions.COLUMN_TIME, "23-59-59");
			values.put(TableSessions.COLUMN_PLACE, "Berlin");
			values.put(TableSessions.COLUMN_DEVICE_DATA, Utils.ConstantVars.DEVICE_ID);
			values.put(TableSessions.COLUMN_GPS_DATA, Utils.ConstantVars.GPS_INFO);
			values.put(TableSessions.COLUMN_IS_FINISHED, "unfinished");
			values.put(TableSessions.COLUMN_COUNT, "0");
			values.put(TableSessions.COLUMN_SCRIPT_ID, ""+i);
			values.put(TableSessions.COLUMN_SPEAKER_ID, ""+i);
			db.insert(TableSessions.TABLE_SESSIONS, null, values);
		}
		
	}
	

	public static class Sessionitem {
	
		private String item_id = "session#";
		private String key_id = null; // key
		private String date = null; // text not null
		private String time = null; // text not null
		private String place = null; // text not null
		private String device_data = null; // text not null
		private String gps_data = null; // text, android needs a little time to set GPS sensors, only get it when is needed
		private String is_finished = null; // text not null finished/unfinished
		private String count = null; // integer
		private String last_section = null; // text
		private String script_id = null; // foreign key reference scripts(_id)
		private String speaker_id = null; // foreign key reference speakers(_id)
		public Sessionitem() {
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
		 * @return the date
		 */
		public String getDate() {
			return date;
		}
		/**
		 * @param date the date to set
		 */
		public void setDate(String date) {
			this.date = date;
		}
		/**
		 * @return the time
		 */
		public String getTime() {
			return time;
		}
		/**
		 * @param time the time to set
		 */
		public void setTime(String time) {
			this.time = time;
		}
		/**
		 * @return the place
		 */
		public String getPlace() {
			return place;
		}
		/**
		 * @param place the place to set
		 */
		public void setPlace(String place) {
			this.place = place;
		}
		/**
		 * @return the device_data
		 */
		public String getDevice_data() {
			return device_data;
		}
		/**
		 * @param device_data the device_data to set
		 */
		public void setDevice_data(String device_data) {
			this.device_data = device_data;
		}
		/**
		 * @return the gps_data
		 */
		public String getGps_data() {
			return gps_data;
		}
		/**
		 * @param gps_data the gps_data to set
		 */
		public void setGps_data(String gps_data) {
			this.gps_data = gps_data;
		}
		/**
		 * @return the is_finished
		 */
		public String getIs_finished() {
			return is_finished;
		}
		/**
		 * @param is_finished the is_finished to set
		 */
		public void setIs_finished(String is_finished) {
			this.is_finished = is_finished;
		}
		/**
		 * @return the count
		 */
		public String getCount() {
			return count;
		}
		/**
		 * @param count the count to set
		 */
		public void setCount(String count) {
			this.count = count;
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
		/**
		 * @return the speaker_id
		 */
		public String getSpeaker_id() {
			return speaker_id;
		}
		/**
		 * @param speaker_id the speaker_id to set
		 */
		public void setSpeaker_id(String speaker_id) {
			this.speaker_id = speaker_id;
		}
		/**
		 * @return the last_section
		 */
		public String getLast_section() {
			return last_section;
		}
		/**
		 * @param last_section the last_section to set
		 */
		public void setLast_section(String last_section) {
			this.last_section = last_section;
		}
	
	}
	
}
