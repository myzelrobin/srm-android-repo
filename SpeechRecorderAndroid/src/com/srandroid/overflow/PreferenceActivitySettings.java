/**
 * Package of activities for buttons in overflow
 */
package com.srandroid.overflow;

import java.util.Arrays;

import com.srandroid.R;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Activity settings
 *
 */
public class PreferenceActivitySettings extends PreferenceActivity implements OnSharedPreferenceChangeListener
{
	
	//private CharSequence old_theme, new_theme;
	//private String[] array_theme_items_values = getResources().getStringArray(R.array.theme_items_values);
	//private CharSequence old_lang, new_lang;
	
	private final String LANGUAGE_KEY = "lang";
	private final String LANGUAGE_DEF = "en";
	
	private final String MICROPHONE_KEY = "mic";
	
	private final String RECVALUE_KEY = "recvalue";
	private final String RECVALUE_DEF = "1000";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// Validates new theme if theme is changed
		// Utils.onActivityCreateSetTheme(this);
				
		super.onCreate(savedInstanceState);
		
		
		
		// create a PreferenceFragment to load the Preference layout
		// addPreferencesFromResource(R.xml.preference_settings);
		getFragmentManager().beginTransaction().replace(android.R.id.content, new PrefFragmentInSettings()).commit();
		
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
	/**
	 * handles changes of the settings
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub
		if(key.equals(LANGUAGE_KEY))
		{
			Toast.makeText(getApplicationContext(), 
					"changed language to " + getPreferenceScreen().getSharedPreferences().getString(LANGUAGE_KEY, LANGUAGE_DEF), 
					3 * Toast.LENGTH_LONG).show();
		}
		if(key.equals(MICROPHONE_KEY))
		{
			Toast.makeText(getApplicationContext(), 
					"changed microphone to " + getPreferenceScreen().getSharedPreferences().getString(MICROPHONE_KEY, "true"), 
					3 * Toast.LENGTH_LONG).show();
		}
		if(key.equals(RECVALUE_KEY))
		{
			Toast.makeText(getApplicationContext(), 
					"changed microphone to " + getPreferenceScreen().getSharedPreferences().getString(RECVALUE_KEY, RECVALUE_DEF), 
					3 * Toast.LENGTH_LONG).show();
		}
	}
	
	/**
	 * @param savedInstanceState
	 */
	@SuppressWarnings("deprecation")
	protected void onResume(Bundle savedInstanceState) 
	{
	    super.onResume();
	    getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}
	
	
	@SuppressWarnings("deprecation")
	protected void onPause() 
	{
	    super.onPause();
	    getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}
	
	
	
	
	
	
	
	
	/**
	 * 
	 *
	 */
	public static class PrefFragmentInSettings extends PreferenceFragment 
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_settings);
        }

        
    }


	
	
}
















/*
//get data from settings activity in this case the language
SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

//in the method getString "date" represents the date from the key value from step 2 and "31/12/2011" 
//represents a default value if the key doesn't exist
String s = settings.getString("date","31/12/2011");
/**/

/*
// Get new settings values
new_theme = settings.getString("Theme", "light");
new_lang = settings.getString("Language", "en");

int sTheme = Arrays.asList(array_theme_items_values).indexOf(new_theme);

if(new_theme != old_theme)
{
	Toast.makeText(getApplicationContext(), "theme changed, validates new theme", 3 * Toast.LENGTH_LONG).show();
	// change theme
	Utils.changeToTheme(this, sTheme);
}

if(new_lang != old_lang)
{
	// change language
	
}
/**/