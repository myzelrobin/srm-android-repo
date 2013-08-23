package com.srandroid.main;

import java.sql.Savepoint;
import java.util.Arrays;

import com.srandroid.R;
import com.srandroid.activitiesScript.ActivityScriptDetails;
import com.srandroid.activitiesSpeaker.ActivityAddSpeaker;
import com.srandroid.activitiesSpeaker.ActivitySpeakerDetails;
import com.srandroid.database.DBAccessor;
import com.srandroid.database.TableScripts;
import com.srandroid.database.TableSessions;
import com.srandroid.database.TableSpeakers;
import com.srandroid.database.SrmContentProvider.SrmUriMatcher;
import com.srandroid.overflow.PrefActivitySettings;
import com.srandroid.util.Utils;

import android.os.Bundle;
import android.os.Parcel;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.CursorJoiner;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityMain extends Activity {
	
	// fields for Drawer
	private CharSequence activity_title;
	private CharSequence title_drawer_items;
	private String[] array_drawer_items;
    private DrawerLayout drawerlayout_in_activitymain;
    private ListView listview_drawer_items;
    private ActionBarDrawerToggle toggle_drawer_items;
    private int selectedItemIndex = -1;
    
    // STATE for savedInstance
    public static final String SELECTED_ITEM_INDEX = "selectedItemIndex";
    
    // 
    private Thread initAppThread = null;

	/**
	 * 
	 */
	public ActivityMain() {
		super();
	}
    
	
	
	
	
	
	
	
	
	
	
	
	// Creates View of this activity
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		//
		// start activity from main
		Bundle extras = getIntent().getExtras(); 

		if (extras != null) 
		{
			selectedItemIndex = extras.getInt(SELECTED_ITEM_INDEX);
		}
					
		
		if(savedInstanceState != null)
		{
			selectedItemIndex = savedInstanceState.getInt(SELECTED_ITEM_INDEX);
		}
		
		if(selectedItemIndex == -1)
        {
        	// select the first item
        	selectedItemIndex = 0;
        }
		
		
		// initialize some constant values
		if(!Utils.ConstantVars.isPreStartInitialized)
		{
			Utils.ConstantVars.initializeApp(getApplicationContext());
			
			// initialize the default values in SharedPreference
			// PreferenceManager.setDefaultValues(this, R.xml.preference_settings, false);
			Utils.initSharedPreference(PreferenceManager.getDefaultSharedPreferences(this));;
			
		}
		
		
		
		
		
		Log.w(ActivityMain.class.getName(), " will create view of this HOME activity.");
		
		setContentView(R.layout.drawerlayout_in_activitymain);
		
		activity_title = title_drawer_items = getTitle();
				
		// Creates drawer
		array_drawer_items = getResources().getStringArray(R.array.array_drawer_items);
        listview_drawer_items = (ListView) findViewById(R.id.listview_drawer_items);
        drawerlayout_in_activitymain = 
        		(DrawerLayout) findViewById(R.id.drawerlayout_in_activitymain);

        // Set the adapter for the list view
        listview_drawer_items.setAdapter(new ArrayAdapter<String>(this,
                R.layout.layout_textview_drawer_items, array_drawer_items));
          
        // Set the list's click listener, and select one item
        listview_drawer_items.setOnItemClickListener(new DrawerItemClickListener(selectedItemIndex));
        
        
        
        // Set the open&close listener in actionbar(swipe and click app icon)
        toggle_drawer_items = new ActionBarDrawerToggle(
        		this,                         /* host Activity */
    			drawerlayout_in_activitymain,   /* DrawerLayout object */
				R.drawable.ic_drawer,          /* new drawer icon to replace 'Up' caret */
				R.string.drawer_items_open,    /* "open drawer" description */
				R.string.drawer_items_close   /* "close drawer" description */
        ){
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) 
            {
                getActionBar().setTitle(activity_title);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) 
            {
                getActionBar().setTitle(title_drawer_items);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerlayout_in_activitymain.setDrawerListener(toggle_drawer_items);
        
        
        // enable home button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        // Pop up hint at the left side
        toastSwipeHint();
        
        Utils.ConstantVars.dbAccessor.close();
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
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle_drawer_items.syncState();
    }
	
    @Override
    public void onConfigurationChanged(Configuration newConfig) 
    {
        super.onConfigurationChanged(newConfig);
        // Updates the toggle when the device configuration changes while your activity is running
        toggle_drawer_items.onConfigurationChanged(newConfig);
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
        // If the navigation drawer is open, hide action items related to the content view
        boolean isDrawerOpen = drawerlayout_in_activitymain.isDrawerOpen(listview_drawer_items);
        
        // menu.findItem(R.id.button_search).setEnabled(!drawerOpen);
        menu.setGroupVisible(R.id.bgroup_overflow, !isDrawerOpen);
        
        //show different buttons for different fragments
        CharSequence title = getActionBar().getTitle();
        int index = Arrays.asList(array_drawer_items).indexOf(title);
        Log.w(this.getClass().getName(), "onPrepareOptionsMenu: title=" + title + " index=" + index);
		switch(index)
		{
			case Utils.ConstantVars.POS_SESSIONS:
				menu.setGroupVisible(R.id.bgroup_sessions, true);
				break;
			case Utils.ConstantVars.POS_SCRIPTS:
				menu.setGroupVisible(R.id.bgroup_scripts, true);
				break;
			case Utils.ConstantVars.POS_SPEAKERS:
				menu.setGroupVisible(R.id.bgroup_speakers, true);
				break;
			default:
				break;
		}
        
        return super.onPrepareOptionsMenu(menu);
    }
	
	/**
	 * Handles click events on app icon and menu items in actionbar and overflow
	 */
	@Override
    public boolean onOptionsItemSelected(MenuItem item) 
	{
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (toggle_drawer_items.onOptionsItemSelected(item)) 
        {
          return true;
        }
        
        // Handles other action bar items
        switch(item.getItemId())
        {
        	// actionbar buttons
        	case R.id.button_add_session:
        		Utils.toastText(getApplicationContext(), "clicked add session");
        		break;
        	case R.id.button_search_sessions:
        		Utils.toastText(getApplicationContext(), "clicked search sessions");
        		break;
        	case R.id.button_download_script:
        		Utils.toastText(getApplicationContext(), "clicked download script");
        		break;
        	case R.id.button_search_scripts:
        		Utils.toastText(getApplicationContext(), "clicked search scripts");
        		break;
        	case R.id.button_add_speaker:
        		Utils.toastText(getApplicationContext(), "clicked add speaker");
        		Intent newI = new Intent(ActivityMain.this, ActivityAddSpeaker.class);
        		// newI.putExtra("key", value); //Optional parameters
        		ActivityMain.this.startActivity(newI);
        		break;
        	case R.id.button_search_speakers:
        		Utils.toastText(getApplicationContext(), "clicked search speakers");
        		break;
        		
        	// overflow buttons
        	case R.id.button_settings:
        		// Launch Preference activity
        	    Intent i = new Intent(ActivityMain.this, PrefActivitySettings.class);
        	    startActivity(i);
        	    // Some feedback to the user
        	    Utils.toastText(getApplicationContext(), "clicked settings, settings window starts");
        		break;
        	case R.id.button_exit:
        		Utils.toastText(getApplicationContext(), "clicked exit, app exits");
        		break;
        	
        	default:
        		break;
        }
        
        return super.onOptionsItemSelected(item);
    }
	
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) 
	{
		savedInstanceState.putInt(SELECTED_ITEM_INDEX, selectedItemIndex);
	    super.onSaveInstanceState(savedInstanceState);
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	  super.onRestoreInstanceState(savedInstanceState);
	  selectedItemIndex = savedInstanceState.getInt(SELECTED_ITEM_INDEX);
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

	
	/**
	 *  Shows a toast with "swipe from here to right" at the left center of the screen
	 */
	public void toastSwipeHint()
	{
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.layout_toasthint_in_activitymain,
		                               (ViewGroup) findViewById(R.id.linearlayout_toasthint));

		TextView text = (TextView) layout.findViewById(R.id.text_toasthint);
		text.setText(R.string.swipeHintText);

		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG * 3);
		toast.setView(layout);
		toast.show();
	}
	
	
	/**
	 * Listener handles click events on drawer items
	 * 
	 */
	private class DrawerItemClickListener implements ListView.OnItemClickListener
	{
		
		public DrawerItemClickListener(int position) 
		{
			super();
			selectItem(position);
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) 
		{
			// TODO Auto-generated method stub
			selectItem(position);
		}

	}
	
	/**
	 * Swaps fragments in the content frame
	 * @param position
	 */
	private void selectItem(int position) 
	{
	    // Create a new fragment and specify the fragment to show based on position
	    Fragment fragment = new FragmentInActivityMain();
	    Bundle args = new Bundle();
	    args.putInt(FragmentInActivityMain.ARG_FRAGMENT_NUMBER, position);
	    fragment.setArguments(args);
	
	    // Insert the fragment by replacing any existing fragment
	    FragmentManager fragmentManager = getFragmentManager();
	    fragmentManager.beginTransaction()
	                   .replace(R.id.framelayout_in_drawerlayout_activitymain, fragment)
	                   .commit();
	
	    // Highlight the selected item, update the title, and close the drawer
	    listview_drawer_items.setItemChecked(position, true);
	    setTitle(array_drawer_items[position]);
	    drawerlayout_in_activitymain.closeDrawer(listview_drawer_items);
	    selectedItemIndex = position;
	}
	
	/**
	 * @return the array_drawer_items
	 */
	public String[] getArray_drawer_items() {
		return array_drawer_items;
	}


	/**
	 * @param array_drawer_items the array_drawer_items to set
	 */
	public void setArray_drawer_items(String[] array_drawer_items) {
		this.array_drawer_items = array_drawer_items;
	}


	
	
	/**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class FragmentInActivityMain extends Fragment 
    	implements LoaderManager.LoaderCallbacks<Cursor>, OnItemClickListener
    {
        public static final String ARG_FRAGMENT_NUMBER = "fragment_number";
        
        // view
        View fragmentView = null;
    	GridView gridView = null;
    	LayoutInflater gridInflater = null;
        
        // position of drawer item
        private int itemIndex = -1;
        
        // adapter
        private SimpleCursorAdapter adapter;
        
        // title
        private String fragmentTitle = null;
        
        
        public FragmentInActivityMain() 
        {
            // Empty constructor required for fragment subclasses
        }
        
        @Override
        public void onCreate(Bundle savedInstanceState) 
        {
        	super.onCreate(savedInstanceState);
        	if(savedInstanceState != null)
        	{
        		
        	}
        	
        }

        @Override
        public View onCreateView(LayoutInflater inflater, 
        							ViewGroup container, 
        							Bundle savedInstanceState) 
        {
        	itemIndex = getArguments().getInt(ARG_FRAGMENT_NUMBER);
        	
        	gridInflater = 
    				(LayoutInflater) getActivity().getSystemService(
    						Context.LAYOUT_INFLATER_SERVICE);
			fragmentView = gridInflater.inflate(
					R.layout.gridview_in_fragment_in_activitymain, null);
    		
			gridView = (GridView) fragmentView.findViewById(
							R.id.gridview_in_fragment_in_activitymain);
        	gridView.setOnItemClickListener(this);
        	
        	switch(itemIndex)
        	{
        		default: break;
        		case Utils.ConstantVars.POS_SESSIONS: // Sessions
        			Log.w(FragmentInActivityMain.class.getName(), 
        					"onCreateView() will fill Fragment with arg=" + itemIndex);
        			fillFragment();
        			
        			break;
        		case Utils.ConstantVars.POS_SCRIPTS: // Scripts
        			Log.w(FragmentInActivityMain.class.getName(), 
        					"onCreateView() will fill Fragment with arg=" + itemIndex);
        			fillFragment();
        			
        			break;
        		case Utils.ConstantVars.POS_SPEAKERS: // Speakers
        			Log.w(FragmentInActivityMain.class.getName(), 
        					"onCreateView() will fill Fragment with arg=" + itemIndex);
        			fillFragment();
        			
        			break;
        			
        	}
        	fragmentTitle = getResources().getStringArray(R.array.array_drawer_items)[itemIndex];
			getActivity().setTitle(fragmentTitle);
            
        	return fragmentView;
        }
        
        
        
        // retrieve data from database
        @Override
		public Loader<Cursor> onCreateLoader(int id, Bundle args) 
		{
        	CursorLoader cursorLoader = null;
        	
        	switch (itemIndex)
			{
					case Utils.ConstantVars.POS_SESSIONS:
						
						// Sessions left outer join Speakers
						String[] selectColumns = {
								TableSessions.COLUMN_DATE,
								TableSessions.COLUMN_IS_FINISHED,
								TableSessions.COLUMN_SCRIPT_ID,
								TableSessions.COLUMN_SPEAKER_ID,
								TableSpeakers.COLUMN_FIRSTNAME,
								TableSpeakers.COLUMN_SURNAME};
						
						cursorLoader = 
								new CursorLoader(this.getActivity().getApplicationContext(), 
										SrmUriMatcher.CONTENT_URI_TABLE_SESSIONS_LEFTJOIN_SPEAKERS, 
										selectColumns, null, null, null);
						
						break;
						
					case Utils.ConstantVars.POS_SCRIPTS:
						// Fields from the database (selectColumns)
						// Must include the _id column for the adapter to work
						String[] selectColumnsScript = { TableScripts.COLUMN_ID, 
												TableScripts.COLUMN_DESCRIPTION};
						cursorLoader = 
								new CursorLoader(this.getActivity().getApplicationContext(), 
										SrmUriMatcher.CONTENT_URI_TABLE_SCRIPTS, 
										selectColumnsScript, null, null, null);
						break;
						
					case Utils.ConstantVars.POS_SPEAKERS:
						// Fields from the database (selectColumns)
						// Must include the _id column for the adapter to work
						String[] selectColumnsSpeaker = { TableSpeakers.COLUMN_ID, 
												TableSpeakers.COLUMN_FIRSTNAME,
												TableSpeakers.COLUMN_SURNAME,
												TableSpeakers.COLUMN_SEX,
												TableSpeakers.COLUMN_ACCENT,
												TableSpeakers.COLUMN_BIRTHDAY};
						cursorLoader = 
								new CursorLoader(this.getActivity().getApplicationContext(), 
										SrmUriMatcher.CONTENT_URI_TABLE_SPEAKERS, 
										selectColumnsSpeaker, null, null, null);
						break;
					default:
						break;
			}
			
        	
			return cursorLoader;
		}

		@Override
		public void onLoadFinished(Loader<Cursor> loader, Cursor data) 
		{

			adapter.swapCursor(data);
		}

		@Override
		public void onLoaderReset(Loader<Cursor> loader) 
		{
			adapter.swapCursor(null);
		}
		
		
		@Override
        public void onActivityCreated(Bundle savedInstanceState)
        {
        	super.onActivityCreated(savedInstanceState);
        }
        
		@Override
		public void onSaveInstanceState(Bundle savedInstanceState) {
		    super.onSaveInstanceState(savedInstanceState);
		    
		}
        /*
        @Override
        public void onViewStateRestored(Bundle savedInstanceState)
        {
        	super.onViewStateRestored(savedInstanceState);
        }
        /**/
        
        @Override
        public void onStart() 
        {
        	super.onStart();
        }
        
        @Override
        public void onResume() 
        {
        	super.onResume();
        }
        
        @Override
        public void onPause() 
        {
        	super.onPause();
        }
        
        @Override
        public void onStop() 
        {
        	super.onStop();
        }
        
        @Override
        public void onDestroyView() 
        {
        	super.onDestroyView();
        }
        
        @Override
        public void onDestroy()
        {
        	super.onDestroy();
        }
        
        @Override
        public void onDetach() 
        {
        	super.onDetach();
        }

		@Override
		public void onItemClick(AdapterView<?> parent, 
				View itemView, 
				int position,
				long rowId) 
		{
			String test = null;
			switch (itemIndex)
			{
					case Utils.ConstantVars.POS_SESSIONS:
						
						Log.w(this.getActivity().getClass().getName(), 
								"clicked session ttem position=" 
										+ " key_id=" + test + " rowId=" + rowId );
						Utils.toastTextToUser(this.getActivity(), "clicked Session Item position=" 
								+ " key_id=" + test + " rowId=" + rowId );
						// use rowId
						break;
						
					case Utils.ConstantVars.POS_SCRIPTS:
						Log.w(this.getActivity().getClass().getName(), 
								"clicked script item position=" 
										+ " key_id=" + test + " rowId=" + rowId );
						
						Intent i2=new Intent(this.getActivity(), ActivityScriptDetails.class);
						i2.putExtra("itemId", Long.toString(rowId));
						this.getActivity().startActivity(i2);
						
						break;
						
					case Utils.ConstantVars.POS_SPEAKERS:
						Log.w(this.getActivity().getClass().getName(), 
								"clicked speaker item position=" 
										+ " key_id=" + test + " rowId=" + rowId);
						
						Intent i3=new Intent(this.getActivity(), ActivitySpeakerDetails.class);
						i3.putExtra("itemId", Long.toString(rowId));
						this.getActivity().startActivity(i3);
						
						
						break;
	
					default:
						break;
			}
			
			
			
		}

		/**
		 * fills gridview with items
		 */
		private void fillFragment() 
        {
        	String[] from = null;
        	int[] to = null;
        	
        	Cursor cursor = null;
        	
			switch (itemIndex)
			{
					case Utils.ConstantVars.POS_SESSIONS:
						
						
						// Sessions left outer join Speakers, both have _id so use a new column
						// Fields from the database 
						from = new String[] {TableSessions.COLUMN_ID,
												TableSessions.COLUMN_SCRIPT_ID,
												TableSessions.COLUMN_DATE,
												TableSessions.COLUMN_IS_FINISHED,
												TableSpeakers.COLUMN_FIRSTNAME,
												TableSpeakers.COLUMN_SURNAME,};
						// Fields on the UI to which CursorAdapter maps
						to = new int[] { R.id.itemSession_textIdValue,
												R.id.itemSession_textScriptIdValue,
												R.id.itemSession_textCreateDate,
												R.id.itemSession_textIsFinished,
												R.id.itemSession_textSpeakerFirstname,
												R.id.itemSession_textSpeakerSurname};
						
						getLoaderManager().initLoader(0, null, this);		
						adapter = new SimpleCursorAdapter(this.getActivity().getApplicationContext(), 
											R.layout.linearlayout_item_session, 
											null, from, to, 0);
						gridView.setAdapter(adapter);
						break;
						
					case Utils.ConstantVars.POS_SCRIPTS:
						// Fields from the database (selectColumns)
						from = new String[] { TableScripts.COLUMN_ID,
								TableScripts.COLUMN_DESCRIPTION};
						// Fields on the UI to which CursorAdapter maps
						to = new int[] { R.id.itemScript_textIdValue,
										R.id.itemScript_textDesciptionValue};
						
						
						getLoaderManager().initLoader(0, null, this);
						adapter = new SimpleCursorAdapter(this.getActivity().getApplicationContext(), 
									R.layout.linearlayout_item_script, 
									null, from, to, 0);
						gridView.setAdapter(adapter);
						break;
						
					case Utils.ConstantVars.POS_SPEAKERS:
						
						// Fields from the database (selectColumns)
						from = new String[] { TableSpeakers.COLUMN_FIRSTNAME,
														TableSpeakers.COLUMN_SURNAME,
														TableSpeakers.COLUMN_SEX,
														TableSpeakers.COLUMN_ACCENT,
														TableSpeakers.COLUMN_BIRTHDAY};
						// Fields on the UI to which CursorAdapter maps
						to = new int[] { R.id.itemSpeaker_textFirstNameValue,
												R.id.itemSpeaker_textSurnameValue,
												R.id.itemSpeaker_textSexValue,
												R.id.itemSpeaker_textAccentValue,
												R.id.itemSpeaker_textBirthdayValue};
						
						getLoaderManager().initLoader(0, null, this);
						adapter = new SimpleCursorAdapter(this.getActivity().getApplicationContext(), 
											R.layout.linearlayout_item_speaker, 
											null, from, to, 0);
						gridView.setAdapter(adapter);
						break;
	
					default:
						break;
			}
			
		}
		
		private String getTextFromItem(View view, int textViewId)
		{
			TextView textView = (TextView) view.findViewById(textViewId);
			return textView.getText().toString();
		}


        
    }
    
}

