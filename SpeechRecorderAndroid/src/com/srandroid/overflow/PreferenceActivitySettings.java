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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.preference_settings);
	}	
}
