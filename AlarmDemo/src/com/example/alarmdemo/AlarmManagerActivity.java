/*
 * Alarm Manager Example using getActivity
 * (It's Working!)
 * Note - Need to register activity(fired when alarm gets triggered) in menifest.xml
 * Author - Ankit Anand
 * */

package com.example.alarmdemo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class AlarmManagerActivity extends Activity {
	Button bset;
	Button bcancel;
	TimePicker tpStart, tpEnd;
	Toast mToast;
	AlarmManager am, am2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_manager);
		
		bset = (Button) findViewById(R.id.bset);
		bcancel = (Button) findViewById(R.id.bcancel);
		tpStart = (TimePicker) findViewById(R.id.tpStart);
		tpEnd = (TimePicker) findViewById(R.id.tpEnd);
		
		bset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					// Set silent mode when timer set
					//int i = Integer.parseInt(etinput.getText().toString());
					Calendar calNow = Calendar.getInstance();
					Calendar cal = (Calendar) calNow.clone();
					
					cal.set(Calendar.HOUR_OF_DAY, tpStart.getCurrentHour());
					cal.set(Calendar.MINUTE, tpStart.getCurrentMinute());
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					
					/*if(cal.compareTo(calNow) <= 0) {
						cal.add(Calendar.DATE, 1);
					}/**/
					
					Intent intent = new Intent(AlarmManagerActivity.this, AlarmReceiver.class);
					PendingIntent pendingIntent = PendingIntent.getActivity(AlarmManagerActivity.this, 2, intent, 0);
					am = (AlarmManager) getSystemService(ALARM_SERVICE);
					//am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (i*1000), pendingIntent);
					am.set(AlarmManager.RTC, cal.getTimeInMillis(), pendingIntent);
					
					//Putting back to normal mode
					Calendar cal2 = (Calendar) calNow.clone();
					cal2.set(Calendar.HOUR_OF_DAY, tpEnd.getCurrentHour());
					cal2.set(Calendar.MINUTE, tpEnd.getCurrentMinute());
					cal2.set(Calendar.SECOND, 10);
					cal2.set(Calendar.MILLISECOND, 0);
					
					Intent i = new Intent(AlarmManagerActivity.this, AlarmReceiverNormal.class);
					PendingIntent pi = PendingIntent.getActivity(AlarmManagerActivity.this, 3, i, 0);
					am2 = (AlarmManager) getSystemService(ALARM_SERVICE);
					am2.set(AlarmManager.RTC, cal2.getTimeInMillis(), pi);
					
				} catch(NumberFormatException e) {
					if(mToast != null) {
						mToast.cancel();
					}
					mToast = Toast.makeText(AlarmManagerActivity.this, "Please enter number in the text field and try again!", Toast.LENGTH_LONG);
					mToast.show();
				}
			}
			
		});
		
		bcancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(AlarmManagerActivity.this, AlarmReceiver.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(AlarmManagerActivity.this, 2, intent, 0);
				am = (AlarmManager) getSystemService(ALARM_SERVICE);
				am.cancel(pendingIntent);
			
				if(mToast != null) {
					mToast.cancel();
				}
				mToast = Toast.makeText(AlarmManagerActivity.this, "Alarm Cancelled!", Toast.LENGTH_LONG);
				mToast.show();
			}
		});
		
	}
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alarm_manager, menu);
		return true;
	}/**/
}
