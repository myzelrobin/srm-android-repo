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

	/**
	 * Constructor
	 */
	public SrmRecorder() {
		// TODO Auto-generated constructor stub
	}
	

	
	public void startRecording() throws IOException 
	{
		
	   recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	   recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	   recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
	   if (audiofile == null) 
	   {
		   // File rec_test_dir = new File(Utils.REC_TEST_DIR_EXT_PATH);
		   File rec_test_dir = Environment.getExternalStorageDirectory();
		   
	       try 
	       { 
	    	   audiofile = File.createTempFile("test_microphone", ".3gp", rec_test_dir);
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
