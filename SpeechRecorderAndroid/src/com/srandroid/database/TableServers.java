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
		insertServerExamples(db);
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
	

	public static class ServerItem {

		private String item_id = "server#";
		private String key_id = null; 
		private String address = null;
		private String username = null;
		private String password = null; 
		private String description= null;
		public ServerItem() {
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
		 * @return the address
		 */
		public String getAddress() {
			return address;
		}
		/**
		 * @param address the address to set
		 */
		public void setAddress(String address) {
			this.address = address;
		}
		/**
		 * @return the username
		 */
		public String getUsername() {
			return username;
		}
		/**
		 * @param username the username to set
		 */
		public void setUsername(String username) {
			this.username = username;
		}
		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}
		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
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
		
		

	}

}
