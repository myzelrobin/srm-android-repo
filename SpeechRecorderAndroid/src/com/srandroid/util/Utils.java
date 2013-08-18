package com.srandroid.util;

import java.io.File;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;



/**
 * 
 */
public class Utils
{
	// class contains constant variables 
	public static class ConstantVars
	{
		public static final String TESTTEXT = "TEST";
		public static final boolean canToastDebugText = true;
		public static final boolean canToastTextToUser = true;
		
		
		// SharedPreferece key and default values
		public static final String KEY_LANGUAGE = "lang";
		public static final String KEY_LANGUAGE_DEF = "en";
		public static String LANGUAGE = KEY_LANGUAGE_DEF;
		
		public static final String KEY_MICVOL = "mic_vol";
		public static final String KEY_MICVOL_DEF = "-1";
		public static String MICVOL = KEY_MICVOL_DEF;
		
		public static final String KEY_PREFSCREEN_RECVALUE = "prefscreen_recvalue";
		
		public static final String KEY_SAMPLE_RATE = "sample_rate";
		public static final String KEY_SAMPLE_RATE_DEF = "22050";
		public static String SAMPLE_RATE = KEY_SAMPLE_RATE_DEF;
		
		public static final String KEY_CHANNELS = "channels";
		public static final String KEY_CHANNELS_DEF = "2";
		public static String CHANNELS = KEY_CHANNELS_DEF;
		
		public static final String KEY_OVERWRITE = "overwrite"; // boolean value, default true
		public static boolean ALLOW_OVERWRITE = true;
		
		public static final String KEY_OVERWRITE_WARNING = "overwrite_warning"; // boolean value, default true
		public static boolean ALLOW_OVERWRITE_WARNING = true;
		
		public static final String KEY_RECORDS_PATH = "records_path";
		public static final String KEY_RECORDS_PATH_DEF = "unknown";
		
		
		// path variables
		public static String APP_DIR_INT_PATH; // app internal root folder
		public static String APP_FILES_DIR_INT_PATH; // app internal folder "files"
		public static String APP_DIR_EXT_PATH; // app external root foler
		public static String APP_FILES_DIR_EXT_PATH; // app external foler "files"
		public static String REC_FILES_DIR_EXT_PATH; // folder "records" in "files"
		public static String REC_TEST_DIR_EXT_PATH; // folder "test_mic" in "records"
		
		public static boolean isPreStartInitialized = false;
		
		/**
		 * @return the lANGUAGE
		 */
		public static String getLANGUAGE() {
			return LANGUAGE;
		}
		/**
		 * @param lANGUAGE the lANGUAGE to set
		 */
		public static void setLANGUAGE(String lANGUAGE) {
			LANGUAGE = lANGUAGE;
		}
		/**
		 * @return the mICVOL
		 */
		public static String getMICVOL() {
			return MICVOL;
		}
		/**
		 * @param mICVOL the mICVOL to set
		 */
		public static void setMICVOL(String mICVOL) {
			MICVOL = mICVOL;
		}
		/**
		 * @return the sAMPLE_RATE
		 */
		public static String getSAMPLE_RATE() {
			return SAMPLE_RATE;
		}
		/**
		 * @param sAMPLE_RATE the sAMPLE_RATE to set
		 */
		public static void setSAMPLE_RATE(String sAMPLE_RATE) {
			SAMPLE_RATE = sAMPLE_RATE;
		}
		/**
		 * @return the cHANNELS
		 */
		public static String getCHANNELS() {
			return CHANNELS;
		}
		/**
		 * @param cHANNELS the cHANNELS to set
		 */
		public static void setCHANNELS(String cHANNELS) {
			CHANNELS = cHANNELS;
		}
		/**
		 * @return the aLLOW_OVERWRITE
		 */
		public static boolean isALLOW_OVERWRITE() {
			return ALLOW_OVERWRITE;
		}
		/**
		 * @param aLLOW_OVERWRITE the aLLOW_OVERWRITE to set
		 */
		public static void setALLOW_OVERWRITE(boolean aLLOW_OVERWRITE) {
			ALLOW_OVERWRITE = aLLOW_OVERWRITE;
		}
		/**
		 * @return the aLLOW_OVERWRITE_WARNING
		 */
		public static boolean isALLOW_OVERWRITE_WARNING() {
			return ALLOW_OVERWRITE_WARNING;
		}
		/**
		 * @param aLLOW_OVERWRITE_WARNING the aLLOW_OVERWRITE_WARNING to set
		 */
		public static void setALLOW_OVERWRITE_WARNING(boolean aLLOW_OVERWRITE_WARNING) {
			ALLOW_OVERWRITE_WARNING = aLLOW_OVERWRITE_WARNING;
		}
		/**
		 * @return the aPP_DIR_INT_PATH
		 */
		public static String getAPP_DIR_INT_PATH() {
			return APP_DIR_INT_PATH;
		}
		/**
		 * @param aPP_DIR_INT_PATH the aPP_DIR_INT_PATH to set
		 */
		public static void setAPP_DIR_INT_PATH(String aPP_DIR_INT_PATH) {
			APP_DIR_INT_PATH = aPP_DIR_INT_PATH;
		}
		/**
		 * @return the aPP_FILES_DIR_INT_PATH
		 */
		public static String getAPP_FILES_DIR_INT_PATH() {
			return APP_FILES_DIR_INT_PATH;
		}
		/**
		 * @param aPP_FILES_DIR_INT_PATH the aPP_FILES_DIR_INT_PATH to set
		 */
		public static void setAPP_FILES_DIR_INT_PATH(String aPP_FILES_DIR_INT_PATH) {
			APP_FILES_DIR_INT_PATH = aPP_FILES_DIR_INT_PATH;
		}
		/**
		 * @return the aPP_DIR_EXT_PATH
		 */
		public static String getAPP_DIR_EXT_PATH() {
			return APP_DIR_EXT_PATH;
		}
		/**
		 * @param aPP_DIR_EXT_PATH the aPP_DIR_EXT_PATH to set
		 */
		public static void setAPP_DIR_EXT_PATH(String aPP_DIR_EXT_PATH) {
			APP_DIR_EXT_PATH = aPP_DIR_EXT_PATH;
		}
		/**
		 * @return the aPP_FILES_DIR_EXT_PATH
		 */
		public static String getAPP_FILES_DIR_EXT_PATH() {
			return APP_FILES_DIR_EXT_PATH;
		}
		/**
		 * @param aPP_FILES_DIR_EXT_PATH the aPP_FILES_DIR_EXT_PATH to set
		 */
		public static void setAPP_FILES_DIR_EXT_PATH(String aPP_FILES_DIR_EXT_PATH) {
			APP_FILES_DIR_EXT_PATH = aPP_FILES_DIR_EXT_PATH;
		}
		/**
		 * @return the rEC_FILES_DIR_EXT_PATH
		 */
		public static String getREC_FILES_DIR_EXT_PATH() {
			return REC_FILES_DIR_EXT_PATH;
		}
		/**
		 * @param rEC_FILES_DIR_EXT_PATH the rEC_FILES_DIR_EXT_PATH to set
		 */
		public static void setREC_FILES_DIR_EXT_PATH(String rEC_FILES_DIR_EXT_PATH) {
			REC_FILES_DIR_EXT_PATH = rEC_FILES_DIR_EXT_PATH;
		}
		/**
		 * @return the rEC_TEST_DIR_EXT_PATH
		 */
		public static String getREC_TEST_DIR_EXT_PATH() {
			return REC_TEST_DIR_EXT_PATH;
		}
		/**
		 * @param rEC_TEST_DIR_EXT_PATH the rEC_TEST_DIR_EXT_PATH to set
		 */
		public static void setREC_TEST_DIR_EXT_PATH(String rEC_TEST_DIR_EXT_PATH) {
			REC_TEST_DIR_EXT_PATH = rEC_TEST_DIR_EXT_PATH;
		}
		
		/**
		 * @param isPreStartInitialized the isPreStartInitialized to set
		 */
		public static void setPreStartInitialized(boolean isPreStartInitialized) {
			ConstantVars.isPreStartInitialized = isPreStartInitialized;
		}

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
		
	}
	
	
	
	public static void setSharedPreference(SharedPreferences sharedPreferences) 
	{
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Utils.ConstantVars.KEY_RECORDS_PATH, Utils.ConstantVars.REC_TEST_DIR_EXT_PATH);
        editor.commit(); 
        
        Log.w(Utils.class.getName(), "setSharedPreference(): SharedPreference changed records_path(key) to " 
        		+ sharedPreferences.getString(Utils.ConstantVars.KEY_RECORDS_PATH, 
        				Utils.ConstantVars.KEY_RECORDS_PATH_DEF));
	}

	// Toast some text for debugging
	public static void toastText(Context context, String s)
	{
		if(Utils.ConstantVars.canToastDebugText) 
			Toast.makeText(context, s, 2 * Toast.LENGTH_LONG).show();
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
	
	public static void getScreenSizeInDP(Context context)
	{
		
	}
	
	public static void setItemBGSize()
	{
		// get screen size in dp
//		DisplayMetrics displayMetrics = new DisplayMetrics(); 
//		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics); 
//		float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density; 
//		float screenHeightDp = displayMetrics.heightPixels / displayMetrics.density; 
//		textView.setText("this display: Pixels width=" + displayMetrics.widthPixels + " height=" + displayMetrics.heightPixels + "; " + "\nDensity=" + displayMetrics.density + "\nDP width=" + screenWidthDp + "\n height=" + screenHeightDp);
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

