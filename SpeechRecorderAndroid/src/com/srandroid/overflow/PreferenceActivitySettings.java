/**
 * Package of activities for buttons in overflow
 */
package com.srandroid.overflow;

import java.util.Arrays;

import com.srandroid.R;
import com.srandroid.util.Utils;

import android.content.SharedPreferences;
import android.os.Bundle;
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
public class PreferenceActivitySettings extends PreferenceActivity 
{
	
	private CharSequence old_theme, new_theme;
	private String[] array_theme_items_values = getResources().getStringArray(R.array.theme_items_values);
	private CharSequence old_lang, new_lang;
	private SharedPreferences settings;
	
	//private FragmentInSettings fragmentInSettings = new FragmentInSettings();

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// Validates new theme if theme is changed
		Utils.onActivityCreateSetTheme(this);
				
		super.onCreate(savedInstanceState);
		
		// create a PreferenceFragment to load the Preference layout
		addPreferencesFromResource(R.xml.preference_settings);
		// getFragmentManager().beginTransaction().replace(android.R.id.content, fragmentInSettings).commit();
		
		// Shows Up button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		// Get old settings values
		// get data from settings activity in this case the language
	    settings = PreferenceManager.getDefaultSharedPreferences(this);
	    old_theme = settings.getString("Theme", "light");
	    old_lang = settings.getString("Language", "en");
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
	 * Validates app settings 
	 * @param savedInstanceState
	 */
	protected void onResume(Bundle savedInstanceState) 
	{
		
		// Get new settings values
		new_theme = settings.getString("Theme", "light");
	    new_lang = settings.getString("Language", "en");
	    
	    if(new_theme != old_theme)
	    {
	    	Toast.makeText(getApplicationContext(), "theme changed, validates new theme", 3 * Toast.LENGTH_LONG).show();
	    	// change theme
	    	Utils.changeToTheme(this, Arrays.asList(array_theme_items_values).indexOf(new_theme));
	    }
	    
	    if(new_lang != old_lang)
	    {
	    	// change language
	    	
	    }
	    
	    super.onResume();
	}
	
	/*
	public static class FragmentInSettings extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_settings);
        }
        
    }
	/**/
	
	/*
	//get data from settings activity in this case the language
    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

    //in the method getString "date" represents the date from the key value from step 2 and "31/12/2011" 
    //represents a default value if the key doesn't exist
    String s = settings.getString("date","31/12/2011");
	/**/
	
}
