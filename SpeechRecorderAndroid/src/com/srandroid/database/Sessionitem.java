package com.srandroid.database;

public class Sessionitem {

	private String item_id = "session#";
	private String key_id = null; // key
	private String date = null; // text not null
	private String time = null; // text not null
	private String place = null; // text not null
	private String device_data = null; // text not null
	private String gps_data = null; // text, android needs a little time to set GPS sensors, only get it when is needed
	private String is_finished = null; // text not null finished/unfinished
	private String count = null; // integer
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

}
