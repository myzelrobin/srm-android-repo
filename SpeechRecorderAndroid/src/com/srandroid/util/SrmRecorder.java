/**
 * 
 */
package com.srandroid.util;

import java.io.File;
import java.io.IOException;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

/**
 * 
 *
 */
public class SrmRecorder {
	
	private static final String TAG="SoundRecordingTest";
	
	private MediaRecorder recorder;	
	private File audiofile = null;
	private String dirPath = null;
	private String fileName = null;

	/**
	 * Constructor
	 */
	public SrmRecorder(String dirPath, String fileName) {
		// TODO Auto-generated constructor stub
		this.dirPath = dirPath;
		this.fileName = fileName;
		
		if(dirPath == null)
		{
		   Log.w(this.getClass().getName(), 
				   "Folder to save audio file doese NOT exist, save audio file to " + Utils.REC_TEST_DIR_EXT_PATH);
		   dirPath = Utils.REC_TEST_DIR_EXT_PATH;
		}
		if(fileName == null)
		{
		   Log.w(this.getClass().getName(), 
				   "fileName of this audio file is invalid, save audio file as test_audio");
		   fileName = "test_audio";
		}
		
	}
	

	
	public void startRecording() throws IOException 
	{
		recorder = new MediaRecorder();
		// set the recorder
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
	   
	   
	   if (audiofile == null) 
	   {
		   Log.w(this.getClass().getName(), 
				   "audio file does not exist, create a new audio file");
		   File dir = new File(dirPath);
		   //File dir = Environment.getExternalStorageDirectory();
		   
	       try 
	       { 
	    	   audiofile = File.createTempFile(fileName, ".3gp", dir);
	       }
	       catch (IOException e) 
	       {
	           Log.e(SrmRecorder.class.getName() + "#" + TAG + "#","sdcard access error");
	           return;
	       }
	   }
	   recorder.setOutputFile(audiofile.getAbsolutePath());
	   recorder.prepare();
	   recorder.start();
	}
	
	public void stopRecording() 
	{
	   recorder.stop();
	   recorder.release();
	   //processaudiofile();
	}
	
	public void processaudiofile() 
	{
	   ContentValues values = new ContentValues(3);
	   long current = System.currentTimeMillis();
	   values.put(MediaStore.Audio.Media.TITLE, "audio" + audiofile.getName());
	   values.put(MediaStore.Audio.Media.DATE_ADDED, (int) (current / 1000));
	   values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/3gpp");
	   values.put(MediaStore.Audio.Media.DATA, audiofile.getAbsolutePath());
	   //ContentResolver contentResolver = getContentResolver();
	   
	   //Uri base = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	   //Uri newUri = contentResolver.insert(base, values);
	   
	   //sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, newUri));
	
	}
}
