package com.srandroid.main;

import java.util.Arrays;

import com.srandroid.R;
import com.srandroid.database.DBAccessor;
import com.srandroid.database.TableSpeakers;
import com.srandroid.database.SrmContentProvider.SrmUriMatcher;
import com.srandroid.overflow.PrefActivitySettings;
import com.srandroid.util.Utils;

import android.os.Bundle;
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
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
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
        
        
        // in the first onCreate(), select Sessions
        if(selectedItemIndex == -1)
        {
        	
        	int sessionsIndex = Arrays.asList(array_drawer_items).indexOf("Sessions");
        	// Set the list's click listener
            listview_drawer_items.setOnItemClickListener(new DrawerItemClickListener(sessionsIndex));
            selectedItemIndex = sessionsIndex;
        }
        
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
			case 0:
				menu.setGroupVisible(R.id.bgroup_sessions, true);
				break;
			case 1:
				menu.setGroupVisible(R.id.bgroup_scripts, true);
				break;
			case 2:
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
	protected void onSaveInstanceState(Bundle savedInstanceState) {
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
    	implements LoaderManager.LoaderCallbacks<Cursor>
    {
        public static final String ARG_FRAGMENT_NUMBER = "fragment_number";
        
        // state for savedInstance
        
        
        // view
        View fragmentView = null;
    	GridView gridView = null;
    	LayoutInflater gridInflater = null;
        
        
        // position of drawer item
        private int type = -1;
        
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
        		type = savedInstanceState.getInt("newType");
            	
            	fillFragment();
            	setTitle();
        	}
        	
        }

        @Override
        public View onCreateView(LayoutInflater inflater, 
        							ViewGroup container, 
        							Bundle savedInstanceState) 
        {
        	type = getArguments().getInt(ARG_FRAGMENT_NUMBER);
        	
        	
        	
        	
        	switch(type)
        	{
        		default: break;
        		case Utils.ConstantVars.POS_SESSIONS: // Sessions
        			Log.w(FragmentInActivityMain.class.getName(), 
        					"onCreateView() will update Fragment with arg=" + type);
        			
        			gridInflater = 
        					(LayoutInflater) getActivity().getSystemService(
        							Context.LAYOUT_INFLATER_SERVICE);
        			fragmentView = gridInflater.inflate(
        					R.layout.gridview_in_fragment_in_activitymain, null);
        			
        			gridView = 
        					(GridView) fragmentView.findViewById(
        							R.id.gridview_in_fragment_in_activitymain);
        			
        			fillFragment();
        			
                	
        			break;
        		case Utils.ConstantVars.POS_SCRIPTS: // Scripts
        			Log.w(FragmentInActivityMain.class.getName(), 
        					"onCreateView() will update Fragment with arg=" + type);
        			
        			gridInflater = 
        					(LayoutInflater) getActivity().getSystemService(
        							Context.LAYOUT_INFLATER_SERVICE);
        			fragmentView = gridInflater.inflate(
        					R.layout.gridview_in_fragment_in_activitymain, null);
        			
        			gridView = 
        					(GridView) fragmentView.findViewById(
        							R.id.gridview_in_fragment_in_activitymain);
        			
        			fillFragment();
        			
        			break;
        		case Utils.ConstantVars.POS_SPEAKERS: // Speakers
        			Log.w(FragmentInActivityMain.class.getName(), 
        					"onCreateView() will update Fragment with arg=" + type);
        			
        			fillFragment();
        			
        			break;
        			
        	}
        	fragmentTitle = getResources().getStringArray(R.array.array_drawer_items)[type];
			getActivity().setTitle(fragmentTitle);
            
        	return fragmentView;
        }
        
        private void fillFragment() 
        {
			switch (type) {
			case Utils.ConstantVars.POS_SESSIONS:
//				// Fields from the database (projection)
//				// Must include the _id column for the adapter to work
//				String[] from = new String[] { TodoTable.COLUMN_SUMMARY };
//				// Fields on the UI to which we map
//				int[] to = new int[] { R.id.label };
//
//				getLoaderManager().initLoader(0, null, this);
//				adapter = new SimpleCursorAdapter(this, R.layout.todo_row, null, from, to, 0);
//				setListAdapter(adapter);
				break;
				
			case Utils.ConstantVars.POS_SCRIPTS:
//				// Fields from the database (projection)
//				// Must include the _id column for the adapter to work
//				String[] from = new String[] { TodoTable.COLUMN_SUMMARY };
//				// Fields on the UI to which we map
//				int[] to = new int[] { R.id.label };
//
//				getLoaderManager().initLoader(0, null, this);
//				adapter = new SimpleCursorAdapter(this, R.layout.todo_row, null, from, to, 0);
//				setListAdapter(adapter);
				break;
				
			case Utils.ConstantVars.POS_SPEAKERS:
				gridInflater = 
				(LayoutInflater) getActivity().getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);
				fragmentView = gridInflater.inflate(
						R.layout.gridview_in_fragment_in_activitymain, null);
		
				gridView = 
						(GridView) fragmentView.findViewById(
								R.id.gridview_in_fragment_in_activitymain);
				
				// Fields from the database (selectColumns)
				String[] from = new String[] { TableSpeakers.COLUMN_FIRSTNAME,
												TableSpeakers.COLUMN_SURNAME,
												TableSpeakers.COLUMN_SEX,
												TableSpeakers.COLUMN_ACCENT,
												TableSpeakers.COLUMN_BIRTHDAY};
				// Fields on the UI to which CursorAdapter maps
				int[] to = new int[] { R.id.firstnameInItemSpeaker,
										R.id.surnameInItemSpeaker,
										R.id.sexInItemSpeaker,
										R.id.accentInItemSpeaker,
										R.id.birthdayInItemSpeaker};
				

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
        
        private void setTitle()
        {
        	fragmentTitle = getResources().getStringArray(R.array.array_drawer_items)[type];
			getActivity().setTitle(fragmentTitle);
        }

		@Override
        public void onActivityCreated(Bundle savedInstanceState)
        {
        	super.onActivityCreated(savedInstanceState);
        }
        
		@Override
		public void onSaveInstanceState(Bundle savedInstanceState) {
		    savedInstanceState.putInt("newType", type);
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
		public Loader<Cursor> onCreateLoader(int id, Bundle args) {
			// Fields from the database (selectColumns)
			// Must include the _id column for the adapter to work
			String[] projection = { TableSpeakers.COLUMN_ID, 
									TableSpeakers.COLUMN_FIRSTNAME,
									TableSpeakers.COLUMN_SURNAME,
									TableSpeakers.COLUMN_SEX,
									TableSpeakers.COLUMN_ACCENT,
									TableSpeakers.COLUMN_BIRTHDAY};
			CursorLoader cursorLoader = 
					new CursorLoader(this.getActivity().getApplicationContext(), 
							SrmUriMatcher.CONTENT_URI_TABLE_SPEAKERS, 
							projection, null, null, null);
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

        
    }

}

