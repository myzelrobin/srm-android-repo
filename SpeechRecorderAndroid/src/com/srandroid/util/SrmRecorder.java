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
	
	// fields for MediaRecorder
	private MediaRecorder recorder;
	private int audioSource = 0;
	private int outputFormat = 0;
	private int audioEncoder = 0;
	
	// fields for audio file
	private File audiofile = null;
	private String dirPath = null;
	private String fileName = null;
	private String audioFilePath = null;

	/**
	 * Constructor
	 */
	public SrmRecorder(String dirPath, String fileName) {
		// TODO Auto-generated constructor stub
		
		// set default values to the fields
		this.dirPath = dirPath;
		this.fileName = fileName;
		
		this.audioSource = MediaRecorder.AudioSource.MIC;
		this.outputFormat = MediaRecorder.OutputFormat.THREE_GPP;
		this.audioEncoder = MediaRecorder.AudioEncoder.AMR_NB;
		
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
		recorder.setAudioSource(audioSource);
		recorder.setOutputFormat(outputFormat);
		recorder.setAudioEncoder(audioEncoder);
	   
	   
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
	   
	   this.setAudioFilePath(audiofile.getAbsolutePath());
	   
	   recorder.setOutputFile(audioFilePath);
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



	public String getAudioFilePath() {
		return audioFilePath;
	}



	public void setAudioFilePath(String audioFilePath) {
		this.audioFilePath = audioFilePath;
	}



	public MediaRecorder getRecorder() {
		return recorder;
	}



	public void setRecorder(MediaRecorder recorder) {
		this.recorder = recorder;
	}



	public File getAudiofile() {
		return audiofile;
	}



	public void setAudiofile(File audiofile) {
		this.audiofile = audiofile;
	}



	public int getAudioSource() {
		return audioSource;
	}



	public void setAudioSource(int audioSource) {
		this.audioSource = audioSource;
	}



	public int getOutputFormat() {
		return outputFormat;
	}



	public void setOutputFormat(int outputFormat) {
		this.outputFormat = outputFormat;
	}



	public int getAudioEncoder() {
		return audioEncoder;
	}



	public void setAudioEncoder(int audioEncoder) {
		this.audioEncoder = audioEncoder;
	}
}
