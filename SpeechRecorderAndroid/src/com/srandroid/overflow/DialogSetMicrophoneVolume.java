package com.srandroid.overflow;

import java.io.File;
import java.io.IOException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

import com.srandroid.R;
import com.srandroid.util.SrmRecorder;
import com.srandroid.util.Utils;

	public class DialogSetMicrophoneVolume extends DialogPreference implements OnClickListener
	{
		private Button bCancel, bTestmic, bTestrecord;
		private int isBTestmicClicked = 0;
		private int isBTestrecordClicked = 0;
		private ProgressBar progressBar;
		
		private String volume_value = "-1"; 
		
		private SrmRecorder recorderForMic, recorderForRecording;
		
		/**
		 * @param context
		 * @param attrs
		 */
		public DialogSetMicrophoneVolume(Context context, AttributeSet attrs) 
		{
			super(context, attrs);
			// TODO Auto-generated constructor stub
			setDialogLayoutResource(R.layout.dialog_settings_microphone);
			
			 
		}
		/**
		 * initiate dialog
		 */
		@Override
		protected void onPrepareDialogBuilder(AlertDialog.Builder builder) 
		{
			 builder.setTitle(R.string.settings_microphone);
			 builder.setPositiveButton(null, null);
			 builder.setNegativeButton(null, null);
			 super.onPrepareDialogBuilder(builder);  
		}
		/**
		 * bind clicklistener to buttons
		 */
		 @Override
		 public void onBindDialogView(View view)
		 {
			 
			 // button CANCEL
			 bCancel = (Button) view.findViewById(R.id.button_cancel_in_dialog_mic);
			 bCancel.setOnClickListener(this);
			 // button TESTMIC
			 bTestmic = (Button) view.findViewById(R.id.button_testmic_in_dialog_mic);
			 bTestmic.setOnClickListener(this);
			 // button TESTREC
			 bTestrecord = (Button) view.findViewById(R.id.button_testrecord_in_dialog_mic);
			 bTestrecord.setOnClickListener(this);
			 
			 
			 setProgressBar((ProgressBar) view.findViewById(R.id.progressBarInDialogSetMic)); 
			 
			 super.onBindDialogView(view);
		 }
		 /**
		  * handles click events on buttons
		  */
		 @Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 int id = v.getId();
			 
			 switch (id) 
			 {
			 	// button CANCEL
				case R.id.button_cancel_in_dialog_mic:
					getDialog().dismiss();
					break;
				
				// button MIC
				case R.id.button_testmic_in_dialog_mic:
					
					if(isBTestmicClicked == 1)
					{
						Utils.toastText(v.getContext(), "settings: dialog: finish testing MIC");
						 volume_value = "999";
						 
						 recorderForMic.stopRecording();
						 Log.w(this.getClass().getName(), SrmRecorder.TAG_TESTMIC 
								 + ": test mic audio file is saved: " 
								 + recorderForMic.getAudioFile());
						 
						 // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
						 // here needs a method to set the volume_value
						 onDialogClosed(true);
						 
						 isBTestmicClicked = 0;
						 getDialog().dismiss();
						break;
					}
					
					Utils.toastText(v.getContext(), "settings: dialog: start testing MIC");
					bTestrecord.setEnabled(false);
					
					recorderForMic = new SrmRecorder(Utils.REC_TEST_DIR_EXT_PATH, "test_mic", this);
					Log.w(this.getClass().getName(), SrmRecorder.TAG_TESTMIC 
							+ ": AudioRecord recorderForMic is created: " 
							+ "\nsampleRateHz=" + SrmRecorder.getSampleRateHz()
							+ "\nchannelConfig=" + SrmRecorder.getChannelConfig()
							+ "\nchannels=" + SrmRecorder.getChannels()
							+ "\nminBufferSize=" + recorderForMic.getMinBufferSize());
					try {
						recorderForMic.startTestMicrophone();;
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					isBTestmicClicked = 1;
					bTestmic.setText("STOP");
					
					break;
					
					// button RECORD
				case R.id.button_testrecord_in_dialog_mic:
					
					if(isBTestrecordClicked == 1)
					{
						Utils.toastText(v.getContext(), "settings: dialog: finish testing RECORD");
						// STOP 
						recorderForRecording.stopRecording();
						 Log.w(this.getClass().getName(), SrmRecorder.TAG_TESTREC 
								 + ": test record audio file is saved: " 
								 + recorderForRecording.getAudioFile());
						 
						 isBTestrecordClicked = 0;
						getDialog().dismiss();
						break;
					}
					
					 Utils.toastText(v.getContext(), "settings: dialog: start testing RECORD");
					 bTestmic.setEnabled(false);
					 
					 recorderForRecording = new SrmRecorder(Utils.REC_TEST_DIR_EXT_PATH, "test_record");
					 Log.w(this.getClass().getName(), SrmRecorder.TAG_TESTREC 
								+ ": AudioRecord recorderForRecording is created: " 
								+ "\nsampleRateHz=" + SrmRecorder.getSampleRateHz()
								+ "\nchannelConfig=" + SrmRecorder.getChannelConfig()
								+ "\nchannels=" + SrmRecorder.getChannels()
								+ "\nminBufferSize=" + recorderForRecording.getMinBufferSize());
					try {
						recorderForRecording.startRecording();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
					 isBTestrecordClicked = 1;
					 bTestrecord.setText("STOP");
					 break;
				
				default:
					break;
			}
			
		}
		 /**
		  * Saves new value for this preference key from xml into the SharedPreference
		  */
		 @Override
		 protected void onDialogClosed(boolean positiveResult) {
		     // When the user selects "OK", persist the new value
		     if (positiveResult) {
		         persistString(volume_value);
		     }
		 }
		 /**
		  * Saves the default value  for this preference key in the SharedPreferences.
		  */
		 @Override
		 protected Object onGetDefaultValue(TypedArray a, int index) 
		 {
		     return a.getString(index);
		 }
		 /**
		  * Initialize the default value
		  */
		 @Override
		 protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
		     if (restorePersistedValue) {
		         // Restore existing state
		         volume_value = this.getPersistedString(PrefActivitySettings.KEY_MICVOL_DEF);
		     } else {
		         // Set default state from the XML attribute
		    	 volume_value = (String) defaultValue;
		         persistString(volume_value);
		     }
		 }
		/**
		 * @return the progressBar
		 */
		public ProgressBar getProgressBar() {
			return progressBar;
		}
		/**
		 * @param progressBar the progressBar to set
		 */
		public void setProgressBar(ProgressBar progressBar) {
			this.progressBar = progressBar;
		}
		
		 
	}
