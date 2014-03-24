package com.example.alarmdemo;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.Button;

public class AlarmReceiverNormal extends Activity{
	AudioManager mode;
	//Button bstop;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_alarm_receiver);
		setContentView(R.layout.activity_alarm_manager);
		
		mode = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		//bstop = (Button) findViewById(R.id.btnstop);
		
		mode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		/*bstop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				finish();
			}
			
		});/**/
		
	}

}
