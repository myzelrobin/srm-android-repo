package com.srandroid.database;

public class RecordItem {

	private String item_id = "record#";
	private String key_id = null; 
	private String filepath = null; 
	private String section_id = null;
	public RecordItem() {
		
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
	 * @return the section_id
	 */
	public String getSection_id() {
		return section_id;
	}
	/**
	 * @param section_id the section_id to set
	 */
	public void setSection_id(String section_id) {
		this.section_id = section_id;
	}

}
