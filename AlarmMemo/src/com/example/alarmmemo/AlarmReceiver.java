package com.example.alarmmemo;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AlarmReceiver extends Activity{
	
	AudioManager mode;
	Button bstop;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_receiver);
		
		mode = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		bstop = (Button) findViewById(R.id.bstop);
		
		mode.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		bstop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				finish();
			}
			
		});
		
	}

}
