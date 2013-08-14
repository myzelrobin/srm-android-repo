package com.srandroid.util;

import com.srandroid.R;

import android.app.Activity;
import android.content.Intent;

/**
 * Functions to change theme
 * 				change locale(language)
 * 
 */
public class Utils
{
	// fields for themes
     private static int sTheme;

     public final static int THEME_LIGHT = 0;
     public final static int THEME_DARK = 1;
     public final static int THEME_TEST = 2;
     public final static int THEME_DEFAULT = 3;
     
     

     /**
      * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
      */
     public static void changeToTheme(Activity activity, int theme)
     {
          sTheme = theme;
          
          activity.finish();

          activity.startActivity(new Intent(activity, activity.getClass()));

     }

     /**
      *  Set the theme of the activity, according to the configuration. 
      */
     public static void onActivityCreateSetTheme(Activity activity)
     {
          switch (sTheme)
          {
          default:
        	  break;
          case THEME_DEFAULT:
              //activity.setTheme(R.style.?);
              break;
          case THEME_LIGHT:
              activity.setTheme(R.style.theme_light);
              break;
          case THEME_DARK:
              activity.setTheme(R.style.theme_dark);
              break;
          case THEME_TEST:
        	  activity.setTheme(R.style.theme_test);
              break;
          }
     }
}