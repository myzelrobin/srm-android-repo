package com.srandroid;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ActivityMain extends Activity {
	
	// fields for Drawer
	private CharSequence activity_title, drawer_items_title;
	private String[] array_drawer_items;
    private DrawerLayout drawer_layout_activity_main;
    private ListView listview_drawer_items;
    private ActionBarDrawerToggle drawer_items_toggle;

    
    // Creates View of this activity
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_activity_main);
		
		activity_title = drawer_items_title = getTitle();
		
		array_drawer_items = getResources().getStringArray(R.array.drawer_items);
        listview_drawer_items = (ListView) findViewById(R.id.listview_drawer_items);
        drawer_layout_activity_main = (DrawerLayout) findViewById(R.id.drawer_layout_activity_main);

        // Set the adapter for the list view
        listview_drawer_items.setAdapter(new ArrayAdapter<String>(this,
                R.layout.layout_drawer_items, array_drawer_items));
        // Set the list's click listener
        listview_drawer_items.setOnItemClickListener(new DrawerItemClickListener());
        
        // Set the open&close listener in actionbar(swipe and click app icon)
        drawer_items_toggle = new ActionBarDrawerToggle(this,                         /* host Activity */
    													drawer_layout_activity_main,   /* DrawerLayout object */
    													R.drawable.ic_drawer,          /* new drawer icon to replace 'Up' caret */
    													R.string.drawer_items_open,    /* "open drawer" description */
    													R.string.drawer_items_close)   /* "close drawer" description */
        {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) 
            {
                getActionBar().setTitle(activity_title);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) 
            {
                getActionBar().setTitle(drawer_items_title);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawer_layout_activity_main.setDrawerListener(drawer_items_toggle);
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
	}
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) 
	{
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawer_items_toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) 
    {
        super.onConfigurationChanged(newConfig);
        drawer_items_toggle.onConfigurationChanged(newConfig);
    }
    
	/**
	 * Handles click events on drawer items
	 * 
	 */
	private class DrawerItemClickListener implements ListView.OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) 
		{
			// TODO Auto-generated method stub
			selectItem(position);
		}

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
        boolean drawerOpen = drawer_layout_activity_main.isDrawerOpen(listview_drawer_items);
        
        // here needs a method to show different buttons for different fragments
        //menu.findItem(R.id.button_search).setEnabled(!drawerOpen);
        menu.setGroupVisible(R.id.bgroup_overflow, !drawerOpen);
        menu.setGroupVisible(R.id.bgroup_sessions, !drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

	/**
	 * Handles click events on app icon and menu items in action bar
	 */
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawer_items_toggle.onOptionsItemSelected(item)) {
          return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
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
	                   .replace(R.id.content_frame, fragment)
	                   .commit();
	
	    // Highlight the selected item, update the title, and close the drawer
	    listview_drawer_items.setItemChecked(position, true);
	    setTitle(array_drawer_items[position]);
	    drawer_layout_activity_main.closeDrawer(listview_drawer_items);
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
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class FragmentInActivityMain extends Fragment {
        public static final String ARG_FRAGMENT_NUMBER = "fragment_number";

        public FragmentInActivityMain() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, 
        							ViewGroup container, 
        							Bundle savedInstanceState) 
        {
            View rootView = inflater.inflate(R.layout.layout_fragement_in_activitymain, container, false);
            int i = getArguments().getInt(ARG_FRAGMENT_NUMBER);
            String fragment_title = getResources().getStringArray(R.array.drawer_items)[i];
            TextView textview_in_fragment = (TextView) rootView.findViewById(R.id.textview_in_fragment);
            textview_in_fragment.setText(fragment_title);
            getActivity().setTitle(fragment_title);
            
            String fragment_title2 = getResources().getStringArray(R.array.drawer_items)[i+1];
            TextView textview_in_fragment2 = (TextView) rootView.findViewById(R.id.textview_in_fragment2);
            textview_in_fragment2.setText(fragment_title2);
            return rootView;
        }
    }

}

