/**
 * 
 */
package com.srandroid.activitiesSpeaker;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Spinner;

import com.srandroid.R;
import com.srandroid.database.SrmContentProvider;
import com.srandroid.database.TableSessions;
import com.srandroid.database.TableSpeakers;
import com.srandroid.database.SrmContentProvider.SrmUriMatcher;
import com.srandroid.database.TableSpeakers.SpeakerItem;
import com.srandroid.util.Utils;

/**
 *
 */
public class ActivitySpeakerDetails extends Activity
{
	// state
		public static final String ITEM_URI = "ITEM_URI";
		private Uri speakerItemUri = null;
		private String itemId = null;
		
		
		private CharSequence activity_title = null;
		
		private SpeakerItem speaker =  new SpeakerItem();
		
		
		TextView name = null;
	    TextView accent = null;
	    TextView sex = null;
	    TextView birthday = null;
	    TextView sessions = null;
	    TextView scripts = null;
	
	/**
	 * 
	 */
	public ActivitySpeakerDetails() {
		// TODO Auto-generated constructor stub
	}
	

	// Creates View of this activity
		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
			
			// start activity from main
			Bundle extras = getIntent().getExtras(); 

			if (extras != null) 
			{
			    itemId = extras.getString("itemId");
			}
			
			// orientation changed
	        if(savedInstanceState != null)
	        {
	        	itemId = savedInstanceState.getString("itemId");
	        }
	        
	        Log.w(ActivitySpeakerDetails.class.getName(), "get itemId=" + itemId);
	        
	        
	        // query from db
			String[] selectColumns = {
					TableSpeakers.COLUMN_FIRSTNAME,
					TableSpeakers.COLUMN_SURNAME,
					TableSpeakers.COLUMN_ACCENT,
					TableSpeakers.COLUMN_SEX,
					TableSpeakers.COLUMN_BIRTHDAY,
					TableSessions.COLUMN_SCRIPT_ID
			};
			
			String wherePart = "speaker_key_id=" + itemId;
			
			Cursor cursor = getContentResolver().query(SrmUriMatcher.CONTENT_URI_TABLE_SPEAKERS_LEFTJOIN_SESSIONS, 
					selectColumns, wherePart, null, null);
			
			
			
	        if (cursor != null && cursor.getCount()!=0) 
			{
				
				Log.w(this.getClass().getName(), " will create view of this activity.");
				
				setContentView(R.layout.linearlayout_activity_speakerdetails);
				
		        
		        name = (TextView) findViewById(R.id.activity_speakerdetails_name_textvalue);
		        accent = (TextView) findViewById(R.id.activity_speakerdetails_accent_textvalue);
		        sex = (TextView) findViewById(R.id.activity_speakerdetails_sex_textvalue);
		        birthday = (TextView) findViewById(R.id.activity_speakerdetails_birthday_textvalue);
		        sessions = (TextView) findViewById(R.id.activity_speakerdetails_sessions_textvalue);
		        scripts = (TextView) findViewById(R.id.activity_speakerdetails_scripts_textvalue);
		        
	        	
				cursor.moveToFirst();
				
				String firstname = cursor.getString(cursor.getColumnIndex(TableSpeakers.COLUMN_FIRSTNAME));
				String surname = cursor.getString(cursor.getColumnIndexOrThrow(TableSpeakers.COLUMN_SURNAME));
				String fullName = firstname + " " + surname;
				name.setText(fullName);
				
				setTitle(fullName);
				
				accent.setText(cursor.getString(cursor.getColumnIndexOrThrow(TableSpeakers.COLUMN_ACCENT)));
				
				sex.setText(cursor.getString(cursor.getColumnIndexOrThrow(TableSpeakers.COLUMN_SEX)));
				
				birthday.setText(cursor.getString(cursor.getColumnIndexOrThrow(TableSpeakers.COLUMN_BIRTHDAY)));
				
				List<String> sessionlist = new ArrayList<String>();
				List<String> scriptlist = new ArrayList<String>();
				
				while(!cursor.isAfterLast())
				{
					String s1 = cursor.getString(cursor.getColumnIndexOrThrow("session_key_id"));
					if(!sessionlist.contains(s1)) sessionlist.add(s1);
					
					String s2 = cursor.getString(cursor.getColumnIndexOrThrow(TableSessions.COLUMN_SCRIPT_ID));
					if(!scriptlist.contains(s2)) scriptlist.add(s2);
					cursor.moveToNext();
				}
				if(!(sessionlist.toString().contains("null"))) sessions.setText(TextUtils.join(",", sessionlist));
				if(!(scriptlist.toString().contains("null")))  scripts.setText(TextUtils.join(",", scriptlist));
				
			}
	        
	        cursor.close();
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
			menu.setGroupVisible(R.id.bgroup_speakerdetails, true);
	        return super.onPrepareOptionsMenu(menu);
	    }
		
		/**
		 * Handles click events on app icon and menu items in actionbar and overflow
		 */
		@Override
	    public boolean onOptionsItemSelected(MenuItem item) 
		{
	       
	        switch (item.getItemId()) 
	        {
			    // Respond to the action bar's Up/Home button
			    case android.R.id.home:
			        NavUtils.navigateUpFromSameTask(this);
			        return true;
			        
			     // actionbar buttons
	        	case R.id.activity_speakerdetails_button_start:
	        		
		        		Utils.toastTextToUser(this, "start recording");
		        		
		        		// Intent newI = new Intent(this.getClass().getName(), ActivityStartRecording.class);
		        		// newI.putExtra("ACTIVITY_NAME", this.getClass().getName());
		        		// newI.putExtra("ITEM_ID", itemId);
		        		// ActivityMain.this.startActivity(newI);
		        		
		        		// send speakerItemUri to next activity
	        		break;
	        	default:
	        		break;
	    	}
		    return super.onOptionsItemSelected(item);
	    }
		
		@Override
		protected void onSaveInstanceState(Bundle savedInstanceState) 
		{
			savedInstanceState.putString("itemId", itemId);
		    super.onSaveInstanceState(savedInstanceState);
		}


		@Override
		public void onRestoreInstanceState(Bundle savedInstanceState) 
		{
		  super.onRestoreInstanceState(savedInstanceState);
		}
		
		/**
		 * 
		 * @param title
		 */
		@Override
		public void setTitle(CharSequence title) 
		{
		    getActionBar().setTitle(title);
		}



}
