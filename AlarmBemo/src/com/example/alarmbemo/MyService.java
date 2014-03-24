package com.example.alarmbemo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;

public class MyService extends Service {
	
	AudioManager mode;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public void onStart(Intent intent, int startId)
	{
       super.onStart(intent, startId);
	 
       mode = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
       mode.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }

}
