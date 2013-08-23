/**
 * 
 */
package com.srandroid.activitiesScript;

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
import com.srandroid.database.TableScripts;
import com.srandroid.database.SrmContentProvider.SrmUriMatcher;
import com.srandroid.database.TableScripts.ScriptItem;
import com.srandroid.database.TableSessions;
import com.srandroid.util.Utils;

/**
 *
 */
public class ActivityScriptDetails extends Activity
{
	// state
		public static final String ITEM_URI = "ITEM_URI";
		private String itemId = null;
		
		
		private CharSequence activity_title = null;
		
		private ScriptItem scriptItem =  new ScriptItem();
		
		
		TextView scriptid = null;
	    TextView scriptdesc = null;
	    TextView sessions = null;
	    TextView speakers = null;
	
	/**
	 * 
	 */
	public ActivityScriptDetails() {
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
	        
	        Log.w(ActivityScriptDetails.class.getName(), "start creating, get itemId=" + itemId);
	        
	        
	        // query from db
			String[] selectColumns = {
					TableScripts.COLUMN_DESCRIPTION,
					TableSessions.COLUMN_SCRIPT_ID,
					TableSessions.COLUMN_SPEAKER_ID
			};
			
			String wherePart = "script_key_id=" + itemId;
			
			Cursor cursor = getContentResolver().query(SrmUriMatcher.CONTENT_URI_TABLE_SCRIPTS_LOJ_SESSIONS, 
					selectColumns, wherePart, null, null);
			
			
			
	        if (cursor != null && cursor.getCount()!=0) 
			{
				
				Log.w(ActivityScriptDetails.class.getName(), " will create view of this activity.");
				
				setContentView(R.layout.linearlayout_activity_scriptdetails);
				
		        
		        scriptid = (TextView) findViewById(R.id.activity_scriptdetails_scriptid_textvalue);
		        scriptdesc = (TextView) findViewById(R.id.activity_scriptdetails_desc_textvalue);
		        sessions = (TextView) findViewById(R.id.activity_scriptdetails_sessions_textvalue);
		        speakers = (TextView) findViewById(R.id.activity_scriptdetails_speakers_textvalue);
		        
	        	
				cursor.moveToFirst();
				
				String idText = cursor.getString(cursor.getColumnIndexOrThrow("script_key_id"));
				scriptid.setText("Script #" + idText);
				setTitle("Script #" + idText);
				
				scriptdesc.setText(cursor.getString(cursor.getColumnIndexOrThrow(TableScripts.COLUMN_DESCRIPTION)));
				
				List<String> sessionlist = new ArrayList<String>();
				List<String> speakerlist = new ArrayList<String>();
				
				while(!cursor.isAfterLast())
				{
					String s1 = cursor.getString(cursor.getColumnIndexOrThrow("session_key_id"));
					if(!sessionlist.contains(s1)) sessionlist.add(s1);
					
					String s2 = cursor.getString(cursor.getColumnIndexOrThrow(TableSessions.COLUMN_SPEAKER_ID));
					if(!speakerlist.contains(s2)) speakerlist.add(s2);
					
					cursor.moveToNext();
				}
				if(!(sessionlist.toString().contains("null"))) sessions.setText(TextUtils.join(",", sessionlist));
				if(!(speakerlist.toString().contains("null"))) speakers.setText(TextUtils.join(",", speakerlist));
				
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
			menu.setGroupVisible(R.id.bgroup_scriptdetails, true);
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
		        		
		        		// send identiy to next activity
		        		// Intent newI = new Intent(this.getClass().getName(), ActivityStartRecording.class);
		        		// newI.putExtra("ACTIVITY_NAME", this.getClass().getName()); 
		        		// newI.putExtra("ITEM_ID", itemId);
		        		// (ActivityScriptDetails.this.startActivity(newI);
		        		
		        		
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
