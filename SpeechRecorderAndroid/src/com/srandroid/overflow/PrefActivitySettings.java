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
import android.preference.PreferenceFragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;

/**
 * Activity settings
 *
 */
public class PrefActivitySettings extends PreferenceActivity 
{
	public static final String KEY_LANGUAGE = "lang";
	public static final String KEY_LANGUAGE_DEF = "en";
	public static String LANGUAGE = KEY_LANGUAGE_DEF;
	
	public static final String KEY_MICVOL = "mic_vol";
	public static final String KEY_MICVOL_DEF = "-1";
	public static String MICVOL = KEY_MICVOL_DEF;
	
	public static final String KEY_PREFSCREEN_RECVALUE = "prefscreen_recvalue";
	
	public static final String KEY_SAMPLE_RATE = "sample_rate";
	public static final String KEY_SAMPLE_RATE_DEF = "22050";
	public static String SAMPLE_RATE = KEY_SAMPLE_RATE_DEF;
	
	public static final String KEY_CHANNELS = "channels";
	public static final String KEY_CHANNELS_DEF = "2";
	public static String CHANNELS = KEY_CHANNELS_DEF;
	
	public static final String KEY_OVERWRITE = "overwrite"; // boolean value, default true
	public static boolean ALLOW_OVERWRITE = true;
	
	public static final String KEY_OVERWRITE_WARNING = "overwrite_warning"; // boolean value, default true
	public static boolean ALLOW_OVERWRITE_WARNING = true;
	
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
	





	public static String getLANGUAGE() {
		return LANGUAGE;
	}

	public static void setLANGUAGE(String lANGUAGE) {
		LANGUAGE = lANGUAGE;
	}

	public static String getMICVOL() {
		return MICVOL;
	}

	public static void setMICVOL(String mICVOL) {
		MICVOL = mICVOL;
	}

	public static String getSAMPLE_RATE() {
		return SAMPLE_RATE;
	}

	public static void setSAMPLE_RATE(String sAMPLE_RATE) {
		SAMPLE_RATE = sAMPLE_RATE;
	}

	public static String getCHANNELS() {
		return CHANNELS;
	}

	public static void setCHANNELS(String cHANNELS) {
		CHANNELS = cHANNELS;
	}

	public static boolean isALLOW_OVERWRITE() {
		return ALLOW_OVERWRITE;
	}

	public static void setALLOW_OVERWRITE(boolean aLLOW_OVERWRITE) {
		ALLOW_OVERWRITE = aLLOW_OVERWRITE;
	}

	public static boolean isALLOW_OVERWRITE_WARNING() {
		return ALLOW_OVERWRITE_WARNING;
	}

	public static void setALLOW_OVERWRITE_WARNING(boolean aLLOW_OVERWRITE_WARNING) {
		ALLOW_OVERWRITE_WARNING = aLLOW_OVERWRITE_WARNING;
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
    		if(key.equals(PrefActivitySettings.KEY_LANGUAGE))
    		{
    			setLANGUAGE(sharedPreferences.getString(PrefActivitySettings.KEY_LANGUAGE, 
    												PrefActivitySettings.KEY_LANGUAGE_DEF));
    			Log.w(this.getClass().getName(), "changed language");
    			Utils.toastText(getActivity(), 
    					"changed language to " + 
    					sharedPreferences.getString(PrefActivitySettings.KEY_LANGUAGE, 
    												PrefActivitySettings.KEY_LANGUAGE_DEF));
    		}
    		if(key.equals(PrefActivitySettings.KEY_MICVOL))
    		{
    			setMICVOL(sharedPreferences.getString(PrefActivitySettings.KEY_MICVOL, 
    							PrefActivitySettings.KEY_MICVOL_DEF));
    			Log.w(this.getClass().getName(), "changed microphone");
    			Utils.toastText(getActivity(), 
    					"changed microphone to " 
    					+ sharedPreferences.getString(PrefActivitySettings.KEY_MICVOL, 
    							PrefActivitySettings.KEY_MICVOL_DEF));
    		}
    		if(key.equals(PrefActivitySettings.KEY_SAMPLE_RATE))
    		{
    			setSAMPLE_RATE(sharedPreferences.getString(PrefActivitySettings.KEY_SAMPLE_RATE, 
    													PrefActivitySettings.KEY_SAMPLE_RATE_DEF));
    			Log.w(this.getClass().getName(), "changed recording_values->sample_rate");
    			Utils.toastText(getActivity(), 
    					"changed recording_value->sample_rate to " 
    					+ sharedPreferences.getString(PrefActivitySettings.KEY_SAMPLE_RATE, 
    													PrefActivitySettings.KEY_SAMPLE_RATE_DEF));
    		}
    		if(key.equals(PrefActivitySettings.KEY_CHANNELS))
    		{
    			setCHANNELS(sharedPreferences.getString(PrefActivitySettings.KEY_CHANNELS, 
    													PrefActivitySettings.KEY_CHANNELS_DEF));
    			Log.w(this.getClass().getName(), "changed recording_values->channels");
    			Utils.toastText(getActivity(), 
    					"changed recording_value->channels to " 
    					+ sharedPreferences.getString(PrefActivitySettings.KEY_CHANNELS, 
    													PrefActivitySettings.KEY_CHANNELS_DEF));
    		}
    		if(key.equals(PrefActivitySettings.KEY_OVERWRITE))
    		{
    			setALLOW_OVERWRITE(sharedPreferences.getBoolean(PrefActivitySettings.KEY_OVERWRITE, true));
    			Log.w(this.getClass().getName(), "changed recording_values->overwrite");
    			Utils.toastText(getActivity(), 
    					"changed recording_value->overwrite to " 
    					+ sharedPreferences.getBoolean(PrefActivitySettings.KEY_OVERWRITE, true));
    		}
    		if(key.equals(PrefActivitySettings.KEY_OVERWRITE_WARNING))
    		{
    			setALLOW_OVERWRITE_WARNING(sharedPreferences.getBoolean(PrefActivitySettings.KEY_OVERWRITE_WARNING, 
    					true));
    			Log.w(this.getClass().getName(), "changed recording_values->overwrite_warning");
    			Utils.toastText(getActivity(), 
    					"changed recording_value->overwrite_warning to " 
    					+ sharedPreferences.getBoolean(PrefActivitySettings.KEY_OVERWRITE_WARNING, true));
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