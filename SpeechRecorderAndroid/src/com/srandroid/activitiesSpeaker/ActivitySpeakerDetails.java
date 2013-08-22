/**
 * 
 */
package com.srandroid.activitiesSpeaker;

import android.app.Activity;
import android.content.ContentValues;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
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
		private int itemId = 0;
		
		
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
			    itemId = extras.getInt("itemId");
			}
			
			// orientation changed
	        if(savedInstanceState != null)
	        {
	        	itemId = savedInstanceState.getInt("itemId");
	        }
	        
	        
	        
	        
	        // query from db
			String[] selectColumns = {
					TableSpeakers.COLUMN_FIRSTNAME,
					TableSpeakers.COLUMN_SURNAME,
					TableSpeakers.COLUMN_ACCENT,
					TableSpeakers.COLUMN_SEX,
					TableSpeakers.COLUMN_BIRTHDAY,
					TableSessions.COLUMN_SCRIPT_ID,
					"speakers._id",
					"sessions._id as session_key_id"
			};
			String wherePart = "speakers._id=" + itemId;
			
			Cursor cursor = getContentResolver().query(SrmUriMatcher.CONTENT_URI_TABLE_SPEAKERS_LEFTJOIN_SESSIONS, 
					selectColumns, wherePart, null, null);
			
			
			
	        if (cursor != null && cursor.getCount()!=0) 
			{
				
				Log.w(ActivitySpeakerDetails.class.getName(), " will create view of this activity.");
				
				setContentView(R.layout.linearlayout_activity_speakerdetails);
				
				activity_title = this.getTitle();
						
		        
		        name = (TextView) findViewById(R.id.activity_speakerdetails_name_textvalue);
		        accent = (TextView) findViewById(R.id.activity_speakerdetails_accent_textvalue);
		        sex = (TextView) findViewById(R.id.activity_speakerdetails_sex_textvalue);
		        birthday = (TextView) findViewById(R.id.activity_speakerdetails_birthday_textvalue);
		        sessions = (TextView) findViewById(R.id.activity_speakerdetails_sessions_textvalue);
		        scripts = (TextView) findViewById(R.id.activity_speakerdetails_scripts_textvalue);
		        
	        	
				cursor.moveToFirst();
				
				String fulName = cursor.getString(cursor.getColumnIndex(TableSpeakers.COLUMN_FIRSTNAME)) + " " 
						+ cursor.getString(cursor.getColumnIndexOrThrow(TableSpeakers.COLUMN_SURNAME));
				name.setText(fulName);
				
				setTitle(fulName);
				
				accent.setText(cursor.getString(cursor.getColumnIndexOrThrow(TableSpeakers.COLUMN_ACCENT)));
				
				sex.setText(cursor.getString(cursor.getColumnIndexOrThrow(TableSpeakers.COLUMN_SEX)));
				
				birthday.setText(cursor.getString(cursor.getColumnIndexOrThrow(TableSpeakers.COLUMN_BIRTHDAY)));
				
				StringBuilder sb1 = new StringBuilder(); // sessions
				StringBuilder sb2 = new StringBuilder(); // scripts
				while(cursor.isAfterLast())
				{
					String s1 = cursor.getString(cursor.getColumnIndexOrThrow("session_key_id"));
					if(!sb1.toString().contains(s1)) sb1.append(s1 + ", ");
					
					String s2 = cursor.getString(cursor.getColumnIndexOrThrow(TableSessions.COLUMN_SCRIPT_ID));
					if(!sb2.toString().contains(s2)) sb2.append(s1 + ", ");
				}
				
				sessions.setText(sb1.toString());
				scripts.setText(sb2.toString());
				
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
		        		
		        		/*Intent newI = new Intent(ActivityMain.this, ActivityAddSpeaker.class);
		        		// newI.putExtra("key", value); //Optional parameters
		        		ActivityMain.this.startActivity(newI);*/
		        		
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
			savedInstanceState.putInt("itemId", itemId);
		    super.onSaveInstanceState(savedInstanceState);
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
