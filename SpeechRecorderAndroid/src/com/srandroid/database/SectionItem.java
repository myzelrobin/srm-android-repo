package com.srandroid.database;

public class SectionItem {

	
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
