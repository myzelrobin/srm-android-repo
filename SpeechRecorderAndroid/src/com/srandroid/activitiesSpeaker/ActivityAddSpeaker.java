/**
 * 
 */
package com.srandroid.activitiesSpeaker;

import java.util.Arrays;

import com.srandroid.R;
import com.srandroid.database.SpeakerItem;
import com.srandroid.main.ActivityMain;
import com.srandroid.overflow.PrefActivitySettings;
import com.srandroid.util.Utils;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

/**
 *
 */
public class ActivityAddSpeaker extends Activity
{

	
	private CharSequence activity_title = null;
	
	private SpeakerItem speaker =  new SpeakerItem();
	/**
	 * 
	 */
	public ActivityAddSpeaker() {
		// TODO Auto-generated constructor stub
	}
	
	
	// Creates View of this activity
		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
			
			Log.w(ActivityAddSpeaker.class.getName(), " will create view of this activity.");
			
			setContentView(R.layout.linearlayout_activity_addspeaker);
			
			activity_title = this.getTitle();
					
	        
	        
	        
	        // orientation changed
	        if(savedInstanceState != null)
	        {
	        	
	        }
	        
	        EditText firstnameInput = (EditText) findViewById(R.id.id_addspeaker_firstname_textinput);
	        EditText surnameInput = (EditText) findViewById(R.id.id_addspeaker_surname_textinput);
	        EditText accentInput = (EditText) findViewById(R.id.id_addspeaker_accent_textinput);
	        Spinner sexDropdownlist = (Spinner) findViewById(R.id.id_addspeaker_sex_dropdownlist);
	        
	        
	        
	        // enable home button
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	        getActionBar().setHomeButtonEnabled(true);
	    }
		

		@Override
	    protected void onStart()
		{
			super.onStart();
		}
		
		@Override
	    protected void onRestart()
	    {
			super.onRestart();
		}
		
		@Override
	    protected void onResume()
	    {
			super.onResume();
		}
		
		@Override
	    protected void onPause()
	    {
			super.onPause();
		}
		
		@Override
	    protected void onStop()
	    {
			super.onStop();
		}
		
		@Override
	    protected void onDestroy()
	    {
			super.onDestroy();
		}
		
		@Override
	    protected void onPostCreate(Bundle savedInstanceState) 
		{
	        super.onPostCreate(savedInstanceState);
	    }
		
	    @Override
	    public void onConfigurationChanged(Configuration newConfig) 
	    {
	        super.onConfigurationChanged(newConfig);
	    }
	    
		/**
		 * Creates menu items in action bar
		 * 
		 * @param menu
		 * @return
		 */
		@Override
		public boolean onCreateOptionsMenu(Menu menu) 
		{
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.menu_items, menu);
			return true;
		}
		
		/**
		 * Called whenever we call invalidateOptionsMenu()
		 * Updates the menu items in action bar when the "drawer items" is closed
		 * 
		 * @param menu
		 * @return
		 */
		@Override
	    public boolean onPrepareOptionsMenu(Menu menu) 
		{
			menu.setGroupVisible(R.id.bgroup_addspeaker, true);
	        return super.onPrepareOptionsMenu(menu);
	    }
		
		/**
		 * Handles click events on app icon and menu items in actionbar and overflow
		 */
		@Override
	    public boolean onOptionsItemSelected(MenuItem item) 
		{
	       
	        // Handles other action bar items
	        switch(item.getItemId())
	        {
	        	// actionbar buttons
	        	case R.id.button_add_session:
	        		Utils.toastText(getApplicationContext(), "clicked start recording");
	        		break;
	        	
	        	default:
	        		break;
	        }
	        
	        return super.onOptionsItemSelected(item);
	    }
		
		@Override
		protected void onSaveInstanceState(Bundle savedInstanceState) 
		{
			
		    super.onSaveInstanceState(savedInstanceState);
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
