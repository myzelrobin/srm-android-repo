package com.srandroid.util;

import java.io.File;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import android.net.Uri;
import android.os.Environment;



/**
 * 
 */
public class Utils
{
	// class contains constant variables 
	public static class ConstantVars
	{
		public static final String TESTTEXT = "TEST";
	}
	
	
	public static boolean isPreStartInitialized = false;
	
	public static final boolean canToastDebugText = true;
	public static final boolean canToastTextToUser = true;

	public static String APP_DIR_INT_PATH;
	public static String APP_FILES_DIR_INT_PATH;
	public static String APP_DIR_EXT_PATH;
	public static String APP_FILES_DIR_EXT_PATH;
	public static String REC_FILES_DIR_EXT_PATH;
	public static String REC_TEST_DIR_EXT_PATH;
	
	
	public static void initializeApp(Context context)
	{
		if(isPreStartInitialized) return;
		
		Log.w(Utils.class.getName(), "initializeApp(): will initialize data before app starts");
		// get application folder path (/data/data/APP_PACKAGE/)
		try {
			APP_DIR_INT_PATH = getAppInternalDir(context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.w(Utils.class.getName(), "APP_DIR_INT=" + APP_DIR_INT_PATH);
		
		// get files folder path (/data/data/APP_PACKAGE/files)
		try {
			APP_FILES_DIR_INT_PATH = getAppInternalFilesDir(context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.w(Utils.class.getName(), "APP_FILES_DIR_INT=" + APP_FILES_DIR_INT_PATH);
		
		// get app folder path in sdcard(/mnt/sdcard/Android/APP_PACKAGE/)
		try {
			APP_DIR_EXT_PATH = getAppExternalDir(context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.w(Utils.class.getName(), "APP_DIR_EXT=" + APP_DIR_EXT_PATH);
		
		// make folder path in sdcard(/mnt/sdcard/Android/APP_PACKAGE/records/)
		REC_FILES_DIR_EXT_PATH = makeDir(APP_DIR_EXT_PATH, "records");
		Log.w(Utils.class.getName(), "REC_FILES_DIR_EXT=" + REC_FILES_DIR_EXT_PATH);
		
		// make folder path in sdcard(/mnt/sdcard/Android/APP_PACKAGE/records/test)
		REC_TEST_DIR_EXT_PATH = makeDir(REC_FILES_DIR_EXT_PATH, "test");
		Log.w(Utils.class.getName(), "REC_TEST_DIR_EXT=" + REC_TEST_DIR_EXT_PATH);
		
		isPreStartInitialized = true;
		Log.w(Utils.class.getName(), "initializeApp(): finished initializing data before app starts");
		
	}
	
	// Toast some text for debugging
	public static void toastText(Context context, String s)
	{
		if(canToastDebugText) Toast.makeText(context, s, 2 * Toast.LENGTH_LONG).show();
	}
	
	
	public static String getAppInternalDir(Context context) throws Exception 
	{
	    return context.getPackageManager()
	            .getPackageInfo(context.getPackageName(), 0)
	            .applicationInfo.dataDir;
	}
	
	public static String getAppInternalFilesDir(Context context) throws Exception 
	{
	    return context.getFilesDir().getAbsolutePath();
	}
	
	public static String getAppExternalDir(Context context) throws Exception 
	{
	    String app_dir_ext_path_temp = Environment.getExternalStorageDirectory().getAbsolutePath()
										+ File.separator + "Android" + File.separator + "data" + File.separator
										+ context.getApplicationContext().getPackageName();
		File dir = new File(app_dir_ext_path_temp);
		if(dir.exists())
		{
			return app_dir_ext_path_temp;
		}
		if(dir.mkdir())
		{
			return app_dir_ext_path_temp;
		}
		Log.w(Utils.class.getName(), "getAppExternalDir(): Can NOT make directory: " + app_dir_ext_path_temp);
		return null;
	}
	
	
	public static String makeDir(String parentFolderPath, String folderName)
	{
		if(parentFolderPath != null)
		{
			String path = parentFolderPath + File.separator + folderName;
			File dir = new File(path);
			if(!dir.exists())
			{
				dir.mkdir();
			}
			return dir.getAbsolutePath();
		}
		Log.w(Utils.class.getName(), "makeDir(): Can NOT make directory, parent folder path is null: " + parentFolderPath);
		return null;
	}
	
	public static void playRecord(Context context, String audioFileName) throws ActivityNotFoundException
	 {
		  
		 Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
         Uri data = Uri.parse("file://" + audioFileName);
         intent.setDataAndType(data, "audio/*");

         
         context.startActivity(intent);
         //Log.w(this.getClass().getName(), "playRecord(): startActivity(intent) throws Exception " + e.getMessage());
        
	}
}



// example codes for changing theme in runtime, not work, unused
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

