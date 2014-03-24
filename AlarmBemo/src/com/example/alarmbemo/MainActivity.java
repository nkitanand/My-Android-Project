/*
 * Alarm Manager Example using Broadcast 
 * (It's working!)
 * NOTE - You need to register receiver and sender for this application in manifest.xml
 * Author - Ankit Anand
 * */

package com.example.alarmbemo;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent startIntent = new Intent(MainActivity.this, BcastReceiver.class);
	    PendingIntent pendingIntentStart = PendingIntent.getBroadcast(MainActivity.this, 0, startIntent,0);
	    AlarmManager alarmManagerStart = (AlarmManager)getSystemService(ALARM_SERVICE);
	    alarmManagerStart.set(AlarmManager.RTC, System.currentTimeMillis() + 5*1000, pendingIntentStart);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
