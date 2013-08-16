/**
 * 
 */
package com.srandroid.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.srandroid.overflow.PrefActivitySettings;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.media.AudioRecord;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.TextUtils.StringSplitter;
import android.util.Log;

/**
 * 
 *
 */
public class SrmRecorder {
	
	private static final String TAG_TESTMIC="TestMicrophone";
	private static final String TAG_RECORDING="Recording";
	
	
	
	
	//fields for audio record
	// AudioRecord(int audioSource, int sampleRateInHz, int channelConfig, int audioFormat, int bufferSizeInBytes)
	private AudioRecord audioRecorder = null;
	
	private static int audioSource = 0;
	private static int sampleRateHz = 0;
	private static int channelConfig = 0;
	private static int channels = 0;
	private static int audioFormat = 0;
	private static int bitsPerSample = 0;
	
	private int bufferSizeInBytes = 0;
	
	// fields for audio file
	private String audioFileName = null;
	private String dirPath = null;
	private String fileName = null;
	private static final String SUFFIX = ".wav";
	private static final String AUDIO_RECORDER_TEMP_FILE = "record_temp.raw";
	
	// fields for recording 
	private Thread recordingThread = null;
	private boolean isRecording = false;

	/**
	 * Constructor
	 */
	public SrmRecorder(String dirPath, String fileName) 
	{
		initializeSrmRecorder();
		
		// set default values to the fields
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
				   "file name of this audio file is invalid, save audio file as unnamed_audio");
		   fileName = "unnamed_audio";
		}
		
	}
	

	
	private void initializeSrmRecorder() 
	{
		audioSource = MediaRecorder.AudioSource.MIC;
		sampleRateHz = Integer.parseInt(PrefActivitySettings.SAMPLE_RATE);
		channelConfig = AudioFormat.CHANNEL_IN_MONO;
		channels = Integer.parseInt(PrefActivitySettings.CHANNELS);
		audioFormat = AudioFormat.ENCODING_PCM_16BIT;
		bitsPerSample = 16;
		
		bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRateHz, 
								android.media.AudioFormat.CHANNEL_IN_MONO, 
								android.media.AudioFormat.ENCODING_PCM_16BIT);
		if(bufferSizeInBytes < 0) 
		{
			Log.w(this.getClass().getName(), "bufferSize < 0, can not create AudioRecord object!");
			return;
		}
		
		audioRecorder = new AudioRecord(audioSource, sampleRateHz, channelConfig, audioFormat, bufferSizeInBytes);
	}



	public void startRecording()
	{
		audioRecorder.startRecording();
		
		isRecording = true;
		
		recordingThread = new Thread(new Runnable() 
			{	
				@Override
				public void run() {
					writeAudioDataToFile();
				}
			}, "AudioRecorder Thread");
		
		recordingThread.start();
	}
	
	

	public void stopRecording() 
	{
		if(null != audioRecorder)
		{
			isRecording = false;
			
			audioRecorder.stop();
			audioRecorder.release();
			
			audioRecorder = null;
			recordingThread = null;
		}
		
		copyWaveFile(getRawFileName(), getFileName());
		deleteTempFile();
		setAudioFileName(getFileName());
	}
	
	private void writeAudioDataToFile()
	{
		String rawFileName = getRawFileName();
		FileOutputStream os = null;
		byte data[] = new byte[bufferSizeInBytes];
		
		try {
			os = new FileOutputStream(rawFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int read = 0;
		
		if(null != os){
			while(isRecording){
				read = audioRecorder.read(data, 0, bufferSizeInBytes);
				
				if(AudioRecord.ERROR_INVALID_OPERATION != read){
					try {
						os.write(data);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String getFileName()
	{
		File file = new File(dirPath);
		
		if(!file.exists())
		{
			file.mkdirs();
		}
		
		return (file.getAbsolutePath() + File.separator + fileName + System.currentTimeMillis() + SUFFIX);
	}
	
	private String getRawFileName() 
	{
		File file = new File(dirPath);
		
		if(!file.exists()) file.mkdirs();
		
		File tempFile = new File(dirPath, AUDIO_RECORDER_TEMP_FILE);
		
		if(tempFile.exists()) tempFile.delete();
		
		return (file.getAbsolutePath() + File.separator + AUDIO_RECORDER_TEMP_FILE);
	}
	
	private void copyWaveFile(String inFileName, String outFileName)
	{
		FileInputStream in = null;
		FileOutputStream out = null;
		long totalAudioLen = 0;
		long totalDataLen = totalAudioLen + 36;
		long byteRate = bitsPerSample * sampleRateHz * channels/8;
		
		byte[] data = new byte[bufferSizeInBytes];
                
		try {
			in = new FileInputStream(inFileName);
			out = new FileOutputStream(outFileName);
			totalAudioLen = in.getChannel().size();
			totalDataLen = totalAudioLen + 36;
			
			WriteWaveFileHeader(out, totalAudioLen, totalDataLen, sampleRateHz, channels, byteRate);
			
			while(in.read(data) != -1)
			{
				out.write(data);
			}
			
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteTempFile() 
	{
		File file = new File(getRawFileName());
		
		file.delete();
	}
	
	private void WriteWaveFileHeader(FileOutputStream out, 
											long totalAudioLen,
											long totalDataLen, 
											long longSampleRate, 
											int channels,
											long byteRate) throws IOException 
	{
		byte[] header = new byte[44];
		
		header[0] = 'R';  // RIFF/WAVE header
		header[1] = 'I';
		header[2] = 'F';
		header[3] = 'F';
		header[4] = (byte) (totalDataLen & 0xff);
		header[5] = (byte) ((totalDataLen >> 8) & 0xff);
		header[6] = (byte) ((totalDataLen >> 16) & 0xff);
		header[7] = (byte) ((totalDataLen >> 24) & 0xff);
		header[8] = 'W';
		header[9] = 'A';
		header[10] = 'V';
		header[11] = 'E';
		header[12] = 'f';  // 'fmt ' chunk
		header[13] = 'm';
		header[14] = 't';
		header[15] = ' ';
		header[16] = 16;  // 4 bytes: size of 'fmt ' chunk
		header[17] = 0;
		header[18] = 0;
		header[19] = 0;
		header[20] = 1;  // format = 1
		header[21] = 0;
		header[22] = (byte) channels;
		header[23] = 0;
		header[24] = (byte) (longSampleRate & 0xff);
		header[25] = (byte) ((longSampleRate >> 8) & 0xff);
		header[26] = (byte) ((longSampleRate >> 16) & 0xff);
		header[27] = (byte) ((longSampleRate >> 24) & 0xff);
		header[28] = (byte) (byteRate & 0xff);
		header[29] = (byte) ((byteRate >> 8) & 0xff);
		header[30] = (byte) ((byteRate >> 16) & 0xff);
		header[31] = (byte) ((byteRate >> 24) & 0xff);
		header[32] = (byte) (2 * 16 / 8);  // block align
		header[33] = 0;
		header[34] = 16;  // bits per sample
		//header[34] = (byte) bitsPerSample;  // bits per sample
		header[35] = 0;
		header[36] = 'd';
		header[37] = 'a';
		header[38] = 't';
		header[39] = 'a';
		header[40] = (byte) (totalAudioLen & 0xff);
		header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
		header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
		header[43] = (byte) ((totalAudioLen >> 24) & 0xff);

		out.write(header, 0, 44);
	}



	/**
	 * @return the audioRecorder
	 */
	public AudioRecord getAudioRecorder() {
		return audioRecorder;
	}



	/**
	 * @param audioRecorder the audioRecorder to set
	 */
	public void setAudioRecorder(AudioRecord audioRecorder) {
		this.audioRecorder = audioRecorder;
	}



	/**
	 * @return the audioSource
	 */
	public static int getAudioSource() {
		return audioSource;
	}



	/**
	 * @param audioSource the audioSource to set
	 */
	public static void setAudioSource(int audioSource) {
		SrmRecorder.audioSource = audioSource;
	}



	/**
	 * @return the sampleRateHz
	 */
	public static int getSampleRateHz() {
		return sampleRateHz;
	}



	/**
	 * @param sampleRateHz the sampleRateHz to set
	 */
	public static void setSampleRateHz(int sampleRateHz) {
		SrmRecorder.sampleRateHz = sampleRateHz;
	}



	/**
	 * @return the channelConfig
	 */
	public static int getChannelConfig() {
		return channelConfig;
	}



	/**
	 * @param channelConfig the channelConfig to set
	 */
	public static void setChannelConfig(int channelConfig) {
		SrmRecorder.channelConfig = channelConfig;
	}



	/**
	 * @return the channels
	 */
	public static int getChannels() {
		return channels;
	}



	/**
	 * @param channels the channels to set
	 */
	public static void setChannels(int channels) {
		SrmRecorder.channels = channels;
	}



	/**
	 * @return the audioFormat
	 */
	public static int getAudioFormat() {
		return audioFormat;
	}



	/**
	 * @param audioFormat the audioFormat to set
	 */
	public static void setAudioFormat(int audioFormat) {
		SrmRecorder.audioFormat = audioFormat;
	}



	/**
	 * @return the bitsPerSample
	 */
	public static int getBitsPerSample() {
		return bitsPerSample;
	}



	/**
	 * @param bitsPerSample the bitsPerSample to set
	 */
	public static void setBitsPerSample(int bitsPerSample) {
		SrmRecorder.bitsPerSample = bitsPerSample;
	}



	/**
	 * @return the suffix
	 */
	public static String getSuffix() {
		return SUFFIX;
	}



	/**
	 * @return the audioFileName
	 */
	public String getAudioFileName() {
		return audioFileName;
	}



	/**
	 * @param audioFileName the audioFileName to set
	 */
	public void setAudioFileName(String audioFileName) {
		this.audioFileName = audioFileName;
	}
	
	
	
	
	
	
	
	
//	// process file after recording is end
//	public void processaudiofile() 
//	{
//	   ContentValues values = new ContentValues(3);
//	   long current = System.currentTimeMillis();
//	   values.put(MediaStore.Audio.Media.TITLE, "audio" + audiofile.getName());
//	   values.put(MediaStore.Audio.Media.DATE_ADDED, (int) (current / 1000));
//	   values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/3gpp");
//	   values.put(MediaStore.Audio.Media.DATA, audiofile.getAbsolutePath());
//	   ContentResolver contentResolver = getContentResolver();
//	   
//	   Uri base = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//	   Uri newUri = contentResolver.insert(base, values);
//	   
//	   sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, newUri));
//	
//	}




}
