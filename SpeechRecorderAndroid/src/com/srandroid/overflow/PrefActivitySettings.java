/**
 * Package of activities for buttons in overflow
 */
package com.srandroid.overflow;

import com.srandroid.R;
import com.srandroid.util.Utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;

/**
 * Activity settings
 *
 */
public class PrefActivitySettings extends PreferenceActivity 
{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
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
	 * 
	 *
	 */
	public static class PrefFragmentInSettings extends PreferenceFragment implements OnSharedPreferenceChangeListener
    {
		private CheckBoxPreference overwriteCheckBox;
		
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_settings);
            
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
            
        }
        
        @Override
    	public void onDestroy() 
    	{
    	    super.onDestroy();
    	    getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    	}
    	/**
    	 * handles changes of the settings
    	 */
    	@Override
    	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
    			String key) {
    		// TODO Auto-generated method stub
    		if(key.equals(Utils.ConstantVars.KEY_LANGUAGE))
    		{
    			Utils.ConstantVars.setLANGUAGE(sharedPreferences.getString(Utils.ConstantVars.KEY_LANGUAGE, 
    							Utils.ConstantVars.KEY_LANGUAGE_DEF));
    			Log.w(this.getClass().getName(), "changed language");
    			Utils.toastText(getActivity(), 
    					"changed language to " + 
    					sharedPreferences.getString(Utils.ConstantVars.KEY_LANGUAGE, 
    							Utils.ConstantVars.KEY_LANGUAGE_DEF));
    		}
    		if(key.equals(Utils.ConstantVars.KEY_MICVOL))
    		{
    			Utils.ConstantVars.setMICVOL(sharedPreferences.getString(Utils.ConstantVars.KEY_MICVOL, 
    							Utils.ConstantVars.KEY_MICVOL_DEF));
    			Log.w(this.getClass().getName(), "changed microphone");
    			Utils.toastText(getActivity(), 
    					"changed microphone to " 
    					+ sharedPreferences.getString(Utils.ConstantVars.KEY_MICVOL, 
    							Utils.ConstantVars.KEY_MICVOL_DEF));
    		}
    		if(key.equals(Utils.ConstantVars.KEY_SAMPLE_RATE))
    		{
    			Utils.ConstantVars.setSAMPLE_RATE(sharedPreferences.getString(Utils.ConstantVars.KEY_SAMPLE_RATE, 
    							Utils.ConstantVars.KEY_SAMPLE_RATE_DEF));
    			Log.w(this.getClass().getName(), "changed recording_values->sample_rate");
    			Utils.toastText(getActivity(), 
    					"changed recording_value->sample_rate to " 
    					+ sharedPreferences.getString(Utils.ConstantVars.KEY_SAMPLE_RATE, 
    							Utils.ConstantVars.KEY_SAMPLE_RATE_DEF));
    		}
    		if(key.equals(Utils.ConstantVars.KEY_CHANNELS))
    		{
    			Utils.ConstantVars.setCHANNELS(sharedPreferences.getString(Utils.ConstantVars.KEY_CHANNELS, 
    							Utils.ConstantVars.KEY_CHANNELS_DEF));
    			Log.w(this.getClass().getName(), "changed recording_values->channels");
    			Utils.toastText(getActivity(), 
    					"changed recording_value->channels to " 
    					+ sharedPreferences.getString(Utils.ConstantVars.KEY_CHANNELS, 
    							Utils.ConstantVars.KEY_CHANNELS_DEF));
    		}
    		if(key.equals(Utils.ConstantVars.KEY_OVERWRITE))
    		{
    			Utils.ConstantVars.setALLOW_OVERWRITE(sharedPreferences.getBoolean(Utils.ConstantVars.KEY_OVERWRITE, 
    					true));
    			Log.w(this.getClass().getName(), "changed recording_values->overwrite");
    			Utils.toastText(getActivity(), 
    					"changed recording_value->overwrite to " 
    					+ sharedPreferences.getBoolean(Utils.ConstantVars.KEY_OVERWRITE, true));
    		}
    		if(key.equals(Utils.ConstantVars.KEY_OVERWRITE_WARNING))
    		{
    			Utils.ConstantVars.setALLOW_OVERWRITE_WARNING(sharedPreferences.getBoolean(Utils.ConstantVars.KEY_OVERWRITE_WARNING, 
    					true));
    			Log.w(this.getClass().getName(), "changed recording_values->overwrite_warning");
    			Utils.toastText(getActivity(), 
    					"changed recording_value->overwrite_warning to " 
    					+ sharedPreferences.getBoolean(Utils.ConstantVars.KEY_OVERWRITE_WARNING, true));
    		}
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