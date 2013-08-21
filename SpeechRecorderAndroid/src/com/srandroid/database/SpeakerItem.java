package com.srandroid.database;

public class SpeakerItem {

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
