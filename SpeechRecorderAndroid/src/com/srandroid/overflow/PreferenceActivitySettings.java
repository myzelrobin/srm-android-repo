/**
 * Package of activities for buttons in overflow
 */
package com.srandroid.overflow;

import com.srandroid.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Activity settings
 *
 */
public class PreferenceActivitySettings extends PreferenceActivity 
{
	private CharSequence activity_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.preference_settings);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}	
	
	
	/**
	 * 
	 * @param title
	 */
	@Override
	public void setTitle(CharSequence title) 
	{
	    activity_title = title;
	    getActionBar().setTitle(activity_title);
	}
	
	
}
