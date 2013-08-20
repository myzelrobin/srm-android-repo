package com.srandroid.util;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import com.srandroid.database.DBAccessor;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;



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
		
		// device informations
		public static String DEVICE_ID = "unknow";
		public static String GPS_INFO = "device unavailable";
		
		// SharedPreferece key and default values
		public static final String KEY_PREFSCREEN_RECVALUE = "prefscreen_recvalue";

		public static final String KEY_LANGUAGE = "lang";
		public static final String KEY_LANGUAGE_DEF = "en";
		public static String LANGUAGE = KEY_LANGUAGE_DEF;
		
		public static final String KEY_MICVOL = "mic_vol";
		public static final String KEY_MICVOL_DEF = "-1";
		public static String MICVOL = KEY_MICVOL_DEF;
		
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
		
		
		// invisible, user can not change these preferences
		public static final String KEY_BYTE_ORDER = "byte_order";
		public static final String KEY_BYTE_ORDER_DEF = "Little Endian"; // Little Endian, Big Endian, Unknown
		
		public static final String KEY_ENCODING = "encoding";
		public static final String KEY_ENCODING_DEF = "PCM_16BIT"; // PCM_16BIT, PCM_8BIT
		
		public static final String KEY_SAMPLE_SIZE = "sample_size";
		public static final String KEY_SAMPLE_SIZE_DEF = "2"; // 0, 1, 2, 3, 4
		
		public static final String KEY_RRCORDING_TARGET = "recording_target";
		public static final String KEY_RECORDING_TARGET_DEF = "direct"; // direct, temp_raw_file
		
		public static final String KEY_AUTOPROGRESS = "auto_progress"; 
		public static boolean ALLOW_AUTOPROGRESS = true;
		// boolean, default true, autoprogress to next unrecorded item 
		
		public static final String KEY_RESET_PEAK = "reset_peak";
		public static boolean ALLOW_RESET_PEAK= true;
		// boolean, default true, reset peak at start of recording
		
		public static final String KEY_PRERECDELAY = "prerecdelay";
		public static final String KEY_PRERECDELAY_DEF = "1000"; // float number, millisecond
		
		public static final String KEY_POSTRECDELAY = "postrecdelay";
		public static final String KEY_POSTRECDELAY_DEF = "1000"; // float number, millisecond
		
		public static final String KEY_RECORDS_PATH = "records_path";
		public static final String KEY_RECORDS_PATH_DEF = "unknown";
		
		
		
		
		
		// path variables
		public static String APP_DIR_INT_PATH; // app internal root folder
		public static String APP_FILES_DIR_INT_PATH; // app internal folder "files"
		public static String APP_DIR_EXT_PATH; // app external root foler
		public static String APP_FILES_DIR_EXT_PATH; // app external foler "files"
		public static String REC_FILES_DIR_EXT_PATH; // folder "records" in "files"
		public static String REC_TEST_DIR_EXT_PATH; // folder "test_mic" in "records"
		
		
		
		// layout values
		// item in Fragment in ActivityMain
		public static final int ITEMWIDTH = 460;
		public static final int ITEMHEIGHT = 160;
		public static int screenWidth = 0;
		public static int screenHeight = 0;
		public static int marginItemBGInVerticalMode = 0;
		public static int marginItemBGInHorizontalMode = 0;
		
		
		
		
		// database
		public static final String TESTDB_FOLDER_PATH = 
				"/mnt/sdcard/srandroid_testfolder";
		
		public static DBAccessor dbAccessor;
		
		
		
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
		
		public static void initializeApp(Context context)
		{
			Log.w(Utils.class.getName(), "initializeApp(): will initialize data before app starts");
			
			getDeviceId(context);
			
			
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
			
			getScreenSize(context);
			setMarginItemBGInVerticalMode();
			setMarginItemBGInHorizontalMode();
			
			// device info
			getDeviceId(context);
			
			// gps info
			getGPSInfo(context);
			
			// database
			dbAccessor = new DBAccessor(context);
			dbAccessor.getWritableDatabase();
			
			isPreStartInitialized = true;
			
			Log.w(Utils.class.getName(), "initializeApp(): finished initializing data before app starts");
			
		}
		
	}
	
	
	
	public static void initSharedPreference(SharedPreferences sharedPreferences) 
	{
		SharedPreferences.Editor editor = sharedPreferences.edit();
		
		editor.putString(Utils.ConstantVars.KEY_LANGUAGE, Utils.ConstantVars.KEY_LANGUAGE_DEF);
        editor.commit(); 
		
		editor.putString(Utils.ConstantVars.KEY_MICVOL, Utils.ConstantVars.KEY_MICVOL_DEF);
        editor.commit(); 
		
		editor.putString(Utils.ConstantVars.KEY_SAMPLE_RATE, Utils.ConstantVars.KEY_SAMPLE_RATE_DEF);
        editor.commit(); 
		
		editor.putString(Utils.ConstantVars.KEY_CHANNELS, Utils.ConstantVars.KEY_CHANNELS_DEF);
        editor.commit(); 
		
		editor.putBoolean(Utils.ConstantVars.KEY_OVERWRITE, Utils.ConstantVars.ALLOW_OVERWRITE);
        editor.commit(); 
		
		editor.putBoolean(Utils.ConstantVars.KEY_OVERWRITE_WARNING, Utils.ConstantVars.ALLOW_OVERWRITE_WARNING);
        editor.commit(); 
		
		editor.putString(Utils.ConstantVars.KEY_BYTE_ORDER, Utils.ConstantVars.KEY_BYTE_ORDER_DEF);
        editor.commit(); 
		
		editor.putString(Utils.ConstantVars.KEY_ENCODING, Utils.ConstantVars.KEY_ENCODING_DEF);
        editor.commit(); 
		
		editor.putString(Utils.ConstantVars.KEY_SAMPLE_SIZE, Utils.ConstantVars.KEY_SAMPLE_SIZE_DEF);
        editor.commit(); 
		
		editor.putString(Utils.ConstantVars.KEY_RRCORDING_TARGET, Utils.ConstantVars.KEY_RECORDING_TARGET_DEF);
        editor.commit(); 
		
		editor.putBoolean(Utils.ConstantVars.KEY_AUTOPROGRESS, Utils.ConstantVars.ALLOW_AUTOPROGRESS);
        editor.commit(); 
		
		editor.putBoolean(Utils.ConstantVars.KEY_RESET_PEAK, Utils.ConstantVars.ALLOW_RESET_PEAK);
        editor.commit(); 
        
		editor.putString(Utils.ConstantVars.KEY_PRERECDELAY, Utils.ConstantVars.KEY_PRERECDELAY_DEF);
        editor.commit(); 
		
		editor.putString(Utils.ConstantVars.KEY_POSTRECDELAY, Utils.ConstantVars.KEY_POSTRECDELAY_DEF);
        editor.commit(); 
		
		editor.putString(Utils.ConstantVars.KEY_RECORDS_PATH, Utils.ConstantVars.KEY_RECORDS_PATH_DEF);
        editor.commit(); 
		
	}

	public static void getDeviceId(Context context) 
	{
		Log.w(Utils.class.getName(), "getDeviceId() will get device id");
		Utils.ConstantVars.DEVICE_ID = Secure.getString(context.getContentResolver(),
                Secure.ANDROID_ID);
	}
	
	public static void getGPSInfo(Context context) 
	{
		//Utils.ConstantVars.GPS_INFO = 
		Log.w(Utils.class.getName(), "getGPSInfo() will get GPS info");
		
		LocationManager locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
		LocationListener locationListener = new MyLocationListener(context);  
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
	}

	// Toast some text for debugging
	public static void toastText(Context context, String s)
	{
		if(Utils.ConstantVars.canToastDebugText) 
			Toast.makeText(context, s, 2 * Toast.LENGTH_LONG).show();
	}
	
	public static void toastTextToUser(Context context, String s)
	{
		if(Utils.ConstantVars.canToastTextToUser) 
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
	
	public static void getScreenSize(Context context)
	{
		// get screen size in dp
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density; 
		float screenHeightDp = displayMetrics.heightPixels / displayMetrics.density;
		Log.w(Utils.class.getName(), "getScreenSize() gets the screen size in DP width=" + screenWidthDp
					+ " height=" + screenHeightDp);
		
		// use width and height as in vertical mode, so width < height
		if(screenWidthDp > screenHeightDp)
		{
			float mid = screenWidthDp;
			screenWidthDp = screenHeightDp;
			screenHeightDp = mid; 
		}
		
		int screenWidthInt = (int) screenWidthDp;
		int screenHeightInt = (int) screenHeightDp;
		
		if((screenWidthInt%2) != 0)
		{
			screenWidthInt--;
		}
		if((screenHeightInt%2) != 0)
		{
			screenHeightInt--;
		}
		
		Utils.ConstantVars.screenWidth = screenWidthInt;
		Utils.ConstantVars.screenHeight = screenHeightInt;
		
		Log.w(Utils.class.getName(), "getScreenSize() optimized the screen size in integer width=" 
				+ Utils.ConstantVars.screenWidth + " height=" + Utils.ConstantVars.screenHeight);
				
	}
	
	public static void setMarginItemBGInVerticalMode()
	{
		Utils.ConstantVars.marginItemBGInVerticalMode = 
				(int) ((Utils.ConstantVars.screenWidth - Utils.ConstantVars.ITEMWIDTH) / 2); 
		Log.w(Utils.class.getName(), "setMarginItemBGInVerticalMode() set the item margin " 
				+ "Utils.ConstantVars.marginItemBGInVerticalMode=" 
				+ Utils.ConstantVars.marginItemBGInVerticalMode);
	}
	
	public static void setMarginItemBGInHorizontalMode()
	{
		Utils.ConstantVars.marginItemBGInHorizontalMode = 
				(int) ((Utils.ConstantVars.screenHeight - (Utils.ConstantVars.ITEMWIDTH * 2)) / 4); 
		Log.w(Utils.class.getName(), "setMarginItemBGInHorizontalMode() set the item margin "
				+ "Utils.ConstantVars.marginItemBGInHorizontalMode=" 
				+ Utils.ConstantVars.marginItemBGInHorizontalMode);
	}
	
	
	
	/**
	 * 
	 * Listener class to get coordinates 
	 * 
	 */
	private static class MyLocationListener implements LocationListener 
	{
		private Context context = null;
		
		public MyLocationListener(Context context)
		{
			this.context = context;
		}

	    @Override
	    public void onLocationChanged(Location loc) 
	    {
	    	Log.w(MyLocationListener.class.getName(), 
	    			"Location changed: latitude: " + loc.getLatitude() 
	    			+ " longitude: " + loc.getLongitude());
	    	
	        String longitude = "Longitude: " + loc.getLongitude();
	        Log.w(MyLocationListener.class.getName(), "get new longitude=" + longitude);
	        
	        String latitude = "Latitude: " + loc.getLatitude();
	        Log.w(MyLocationListener.class.getName(), "get new latitude=" + latitude);
	        /*-------to get City-Name from coordinates -------- */
	        String cityName = null;
	        Geocoder gcd = new Geocoder(context, Locale.getDefault());
	        try {
	        	cityName = gcd.getFromLocation(loc.getLatitude(),
	                    loc.getLongitude(), 1).get(0).getLocality();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        String s = "longitude=" + longitude 
	        		+ "\nlatitude=" + latitude 
	        		+ "\n\nCurrent City is: " + cityName;
	        
	        Utils.ConstantVars.GPS_INFO = s;
	        
	        Log.w(MyLocationListener.class.getName(), 
	    			"new location is" + s);
	    }

	    @Override
	    public void onProviderDisabled(String provider) 
	    {
	    	Utils.toastTextToUser(context, "GPS " + provider + " is disabled, check system settings!");
	    	Utils.ConstantVars.GPS_INFO = "device is disabled";
	    	// need an Intent to open location settings
	    }

	    @Override
	    public void onProviderEnabled(String provider) 
	    {
	    	
	    }

	    @Override
	    public void onStatusChanged(String provider, int status, Bundle extras) 
	    {
	    	
	    }

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

