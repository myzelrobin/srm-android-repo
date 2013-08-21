package com.srandroid.database;

public class ScriptItem {

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
