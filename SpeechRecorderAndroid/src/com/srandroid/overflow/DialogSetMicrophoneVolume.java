package com.srandroid.overflow;

import java.io.File;
import java.io.IOException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.srandroid.R;
import com.srandroid.util.SrmRecorder;
import com.srandroid.util.Utils;

	public class DialogSetMicrophoneVolume extends DialogPreference implements OnClickListener
	{
		private Button bCancel, bStart, bFinish;
		private String volume_value = "-1"; 
		
		private SrmRecorder media_lib;
		
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
			 // button START
			 bStart = (Button) view.findViewById(R.id.button_start_in_dialog_mic);
			 bStart.setOnClickListener(this);
			 // button FINISH
			 bFinish = (Button) view.findViewById(R.id.button_finish_in_dialog_mic);
			 bFinish.setOnClickListener(this);
			 bFinish.setEnabled(false);
			 
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
				case R.id.button_cancel_in_dialog_mic:
					getDialog().dismiss();
					break;
				case R.id.button_start_in_dialog_mic:
					Utils.toastText(v.getContext(), "settings: start testing microphone");
					try {
						media_lib.startRecording();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					bStart.setEnabled(false);
					bFinish.setEnabled(true);
					break;
				case R.id.button_finish_in_dialog_mic:
					 Utils.toastText(v.getContext(), "settings: finish testing microphone");
					 volume_value = "999";
					 
					 media_lib.stopRecording();
					 
					 onDialogClosed(true);
					 getDialog().dismiss();
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
		
		 
	}
