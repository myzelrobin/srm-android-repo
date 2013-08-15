package com.srandroid.util;

import java.io.File;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;



/**
 * Functions to change theme
 * 				change locale(language)
 * 
 */
public class Utils
{
	public static final boolean canToastText = true;
	public static String APP_DIR_PATH;
	public static String APP_FILES_DIR_PATH;
	public static String REC_FILES_DIR_PATH;
	public static String REC_TEST_DIR_PATH;
	
	public static void initializeApp(Context context)
	{
		// get application folder path (/data/data/APP_PACKAGE/)
		try {
			APP_DIR_PATH = getAppDir(context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.w(Utils.class.getName(), "APP_DIR=" + APP_DIR_PATH);
		
		// get files folder path (/data/data/APP_PACKAGE/files)
		try {
			APP_FILES_DIR_PATH = getAppFilesDir(context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.w(Utils.class.getName(), "APP_FILES_DIR=" + APP_FILES_DIR_PATH);
		
		REC_FILES_DIR_PATH = makeDir(APP_FILES_DIR_PATH, "recorders");
		REC_TEST_DIR_PATH = makeDir(REC_FILES_DIR_PATH, "test");
		
	}
	
	// Toast some text for debugging
	public static void toastText(Context context, String s)
	{
		if(canToastText) Toast.makeText(context, s, 2 * Toast.LENGTH_LONG).show();
	}
	
	
	public static String getAppDir(Context context) throws Exception 
	{
	    return context.getPackageManager()
	            .getPackageInfo(context.getPackageName(), 0)
	            .applicationInfo.dataDir;
	}
	
	public static String getAppFilesDir(Context context) throws Exception 
	{
	    return context.getFilesDir().getAbsolutePath();
	}
	
	public static String makeDir(String parentFolder, String folderName)
	{
		if(parentFolder != null)
		{
			File dir_files = new File(parentFolder + File.separator+folderName);
			dir_files.mkdir();
			return dir_files.getAbsolutePath();
		}
		else
		{
			Log.w(Utils.class.getName(), "Can NOT make directory, parent folder path is null!");
		}
		
		return null;
	}
}



// example codes for changing theme in runtime, unused
//	// fields for themes
//     private static int sTheme;
//
//     public final static int THEME_LIGHT = 0;
//     public final static int THEME_DARK = 1;
//     public final static int THEME_TEST = 2;
//     public final static int THEME_DEFAULT = 3;
//     
//     
//
//     /**
//      * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
//      */
//     public static void changeToTheme(Activity activity, int theme)
//     {
//          sTheme = theme;
//          
//          activity.finish();
//
//          activity.startActivity(new Intent(activity, activity.getClass()));
//
//     }
//
//     /**
//      *  Set the theme of the activity, according to the configuration. 
//      */
//     public static void onActivityCreateSetTheme(Activity activity)
//     {
//          switch (sTheme)
//          {
//          default:
//        	  break;
//          case THEME_DEFAULT:
//              //activity.setTheme(R.style.?);
//              break;
//          case THEME_LIGHT:
//              activity.setTheme(R.style.theme_light);
//              break;
//          case THEME_DARK:
//              activity.setTheme(R.style.theme_dark);
//              break;
//          case THEME_TEST:
//        	  activity.setTheme(R.style.theme_test);
//              break;
//          }
//     }

