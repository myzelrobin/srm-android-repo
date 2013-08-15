package com.srandroid.overflow;

import android.app.AlertDialog;
import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.srandroid.R;
import com.srandroid.util.Utils;

	public class DialogSetMicrophoneVolume extends DialogPreference
	{
		private Button bStart, bCancel;
		
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
		 * handles clicks on buttons
		 */
		 @Override
		 public void onBindDialogView(View view){
			 bStart = (Button) view.findViewById(R.id.button_start_in_dialog_mic);
			 bStart.setOnClickListener(new OnClickListener() 
			 {
				 @Override
				 public void onClick(View v) 
				 {
					 if(bStart.getText() == "START")
					 {
						 bStart.setText("STOP");
						 Utils.toastText(v.getContext(), "settings: start testing microphone");
					 }
					 else
					 {
						 bStart.setText("START");
						 Utils.toastText(v.getContext(), "settings: finish testing microphone, change microphone volume level");
					 }
						 
					   
				 }
			 });
			 
			 bCancel = (Button) view.findViewById(R.id.button_cancel_in_dialog_mic);
			 bCancel.setOnClickListener(new OnClickListener() 
			 {
				 @Override
				 public void onClick(View v) 
				 {
					 getDialog().dismiss();
				 }
			 });
			 super.onBindDialogView(view);
		 }
	}