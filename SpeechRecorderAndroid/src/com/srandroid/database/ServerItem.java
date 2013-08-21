package com.srandroid.database;

public class ServerItem {

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
