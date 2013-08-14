/**
 * Package of activities for buttons in overflow
 */
package com.srandroid.overflow;

import com.srandroid.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

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
		
		// Shows Up button
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}	
	
	/**
	 * handles the click on Up button.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	        NavUtils.navigateUpFromSameTask(this);
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	
}
