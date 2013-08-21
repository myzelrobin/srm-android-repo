/**
 * 
 */
package com.srandroid.activitiesSpeaker;

import java.util.Arrays;

import com.srandroid.R;
import com.srandroid.database.SpeakerItem;
import com.srandroid.database.SrmContentProvider;
import com.srandroid.database.TableSpeakers;
import com.srandroid.main.ActivityMain;
import com.srandroid.overflow.PrefActivitySettings;
import com.srandroid.util.Utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.NavUtils;
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
	private Uri speakerItemUri = null;
	
	EditText firstnameInput = null;
    EditText surnameInput = null;
    EditText accentInput = null;
    Spinner sexDropdownlist = null;
    EditText yearInput = null;
    EditText monthInput = null;
    EditText dayInput = null;
	
	
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
	        
	        firstnameInput = (EditText) findViewById(R.id.id_addspeaker_firstname_textinput);
	        surnameInput = (EditText) findViewById(R.id.id_addspeaker_surname_textinput);
	        accentInput = (EditText) findViewById(R.id.id_addspeaker_accent_textinput);
	        sexDropdownlist = (Spinner) findViewById(R.id.id_addspeaker_sex_dropdownlist);
	        yearInput = (EditText) findViewById(R.id.id_addspeaker_year_textinput);
	        monthInput = (EditText) findViewById(R.id.id_addspeaker_month_textinput);
	        dayInput = (EditText) findViewById(R.id.id_addspeaker_day_textinput);
	        
	        
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
	       
	        switch (item.getItemId()) {
			    // Respond to the action bar's Up/Home button
			    case android.R.id.home:
			        NavUtils.navigateUpFromSameTask(this);
			        return true;
			        
			     // actionbar buttons
	        	case R.id.activity_addspeaker_button_start:
	        		Utils.toastText(getApplicationContext(), "clicked start recording");
	        		if(!firstnameInput.getText().toString().matches("") 
	        				&& !surnameInput.getText().toString().matches("")
	        				&& !accentInput.getText().toString().matches("") 
	        				&& !sexDropdownlist.isSelected())
	        		{
	        			saveDataToSpeakerItem(speaker);
		        		Uri speakerItemUri = saveSpeakerItemToDB(speaker);
		        		Log.w(ActivityAddSpeaker.class.getName(), 
		        				"saveSpeakerItemToDB() inserted a speaker into db with id=" + speakerItemUri);
		        		
		        		/*Intent newI = new Intent(ActivityMain.this, ActivityAddSpeaker.class);
		        		// newI.putExtra("key", value); //Optional parameters
		        		ActivityMain.this.startActivity(newI);*/
		        		
		        		// send speakerItemUri to next activity
	        			
	        			break;
	        		}
	        		Utils.toastTextToUser(this, "Please input name, accent and choose sex to create a speaker!");
	        		break;
	        	default:
	        		break;
		    }
		    return super.onOptionsItemSelected(item);
	    }
		

		private void saveDataToSpeakerItem(SpeakerItem speaker) 
		{
			speaker.setFirstname(firstnameInput.getText().toString());
			speaker.setSurname(surnameInput.getText().toString());
			speaker.setAccent(accentInput.getText().toString());
			speaker.setSex(String.valueOf(sexDropdownlist.getSelectedItem()));
			String birthday = yearInput.getText().toString() + "-" 
						+ monthInput.getText().toString() + "-"
					+ dayInput.getText().toString();
			speaker.setBirthday(birthday);
			
			
		}
		

		private Uri saveSpeakerItemToDB(SpeakerItem speaker) 
		{
			ContentValues values = new ContentValues();
			values.put(TableSpeakers.COLUMN_FIRSTNAME, speaker.getFirstname());
			values.put(TableSpeakers.COLUMN_SURNAME, speaker.getSurname());
			values.put(TableSpeakers.COLUMN_ACCENT, speaker.getAccent());
			values.put(TableSpeakers.COLUMN_SEX, speaker.getSex());
			values.put(TableSpeakers.COLUMN_BIRTHDAY, speaker.getBirthday());

			
			speakerItemUri = 
					getContentResolver().insert(SrmContentProvider.SrmUriMatcher.CONTENT_URI_TABLE_SPEAKERS, values);
			return speakerItemUri;
		}



		@Override
		protected void onSaveInstanceState(Bundle savedInstanceState) 
		{
			savedInstanceState.putParcelable("NEW_SPEAKER_ITEM_URI", speakerItemUri);
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
