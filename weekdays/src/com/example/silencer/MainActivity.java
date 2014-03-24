/*
 * This project puts device on silent mode in the interval and days specified by the users.
 * 
 * Author: Ankit Anand
 * */

package com.example.silencer;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	
	Context ctx;
	TimePicker tpStart;
	TimePicker tpEnd;
	CheckBox cbMon, cbTue, cbWed, cbThu, cbFri, cbSat, cbSun;
	Calendar calNow;
	Calendar cstart;
	Calendar cstop;
	Button bDone;
	Button bCancel;
	Intent startIntent;
	Intent stopIntent;
	PendingIntent pendingIntentStart, pi1, pi2, pi3, pi4, pi5, pi6, pi7;
	PendingIntent pendingIntentStop, pstop1, pstop2, pstop3, pstop4, pstop5, pstop6, pstop7;
	AlarmManager alarmManagerStart;
	AlarmManager alarmManagerStop;
	private Toast toast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		ctx = this;
		tpStart = (TimePicker) findViewById(R.id.tpStart);
		tpEnd   = (TimePicker) findViewById(R.id.tpEnd);
		cbMon   = (CheckBox) findViewById(R.id.cbMon);
		cbTue   = (CheckBox) findViewById(R.id.cbTue);
		cbWed   = (CheckBox) findViewById(R.id.cbWed);
		cbThu   = (CheckBox) findViewById(R.id.cbThu);
		cbFri   = (CheckBox) findViewById(R.id.cbFri);
		cbSat   = (CheckBox) findViewById(R.id.cbSat);
		cbSun   = (CheckBox) findViewById(R.id.cbSun);
		bDone   = (Button) findViewById(R.id.bDone);
		bCancel = (Button) findViewById(R.id.bCancel);
		
		alarmManagerStart = (AlarmManager)getSystemService(ALARM_SERVICE);
		alarmManagerStop = (AlarmManager)getSystemService(ALARM_SERVICE);
		
		startIntent = new Intent(MainActivity.this, ReceiverStart.class);
		stopIntent = new Intent(MainActivity.this, ReceiverStop.class);
		
		bDone.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				calNow = Calendar.getInstance();
				setTimeForSilent();
			    setTimeForNormal();
			    
			    if(noDayIsSet()) {
			    	if(toast!=null)
				    	toast.cancel();
					toast = Toast.makeText(getBaseContext(), "No days are selected", Toast.LENGTH_SHORT);
					toast.show();
			    } else {
			    	if(toast!=null)
				    	toast.cancel();
					toast = Toast.makeText(getBaseContext(), "Silencer is activated", Toast.LENGTH_SHORT);
					toast.show();
			    }
			}
		});
		
		bCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(noDayIsSet())
				{
					if(toast!=null)
				    	toast.cancel();
					toast = Toast.makeText(getBaseContext(), "No silencer was activated", Toast.LENGTH_SHORT);
					toast.show();
				} else{ 
					alarmManagerStart.cancel(pi1);
					alarmManagerStart.cancel(pi2);
					alarmManagerStart.cancel(pi3);
					alarmManagerStart.cancel(pi4);
					alarmManagerStart.cancel(pi5);
					alarmManagerStart.cancel(pi6);
					alarmManagerStart.cancel(pi7);
					pi1=null; pi2=null; pi3=null; pi4=null; pi5=null; pi6=null; pi7=null;
					
					alarmManagerStop.cancel(pstop1);
					alarmManagerStop.cancel(pstop2);
					alarmManagerStop.cancel(pstop3);
					alarmManagerStop.cancel(pstop4);
					alarmManagerStop.cancel(pstop5);
					alarmManagerStop.cancel(pstop6);
					alarmManagerStop.cancel(pstop7);
					pstop1=null; pstop2=null; pstop3=null; pstop4=null; pstop5=null; pstop6=null; pstop7=null;
					
					if(toast!=null)
						toast.cancel();
					toast=Toast.makeText(getBaseContext(), "Silencer is deactivated", Toast.LENGTH_SHORT);
					toast.show();
				}
				/**/
				//Toast.makeText(getBaseContext(), "Silencer is deactivated", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	public void forDayStart(int week, PendingIntent p) {
		
		cstart = (Calendar) calNow.clone();
		
		cstart.set(Calendar.DAY_OF_WEEK, week);
		cstart.set(Calendar.HOUR_OF_DAY, tpStart.getCurrentHour());
		cstart.set(Calendar.MINUTE, tpStart.getCurrentMinute());
		cstart.set(Calendar.SECOND, 0);
		cstart.set(Calendar.MILLISECOND, 0);
		
		alarmManagerStart.setRepeating(AlarmManager.RTC,cstart.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7, p); 
		
	}
	
	protected void setTimeForSilent() {
		
		if(cbSun.isChecked()) {
			pi1 = PendingIntent.getBroadcast(MainActivity.this, 1, startIntent, 0);
			forDayStart(Calendar.SUNDAY, pi1);
		} else {
			AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(ALARM_SERVICE);

            alarmManager.cancel(pi1);
		}
		
		if(cbMon.isChecked()) {
			pi2 = PendingIntent.getBroadcast(MainActivity.this, 2, startIntent, 0);
			forDayStart(Calendar.MONDAY, pi2);
		} else {
			AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(ALARM_SERVICE);

            alarmManager.cancel(pi2);
		}
		
		if(cbTue.isChecked()) {
			pi3 = PendingIntent.getBroadcast(MainActivity.this, 3, startIntent, 0);
			forDayStart(Calendar.TUESDAY, pi3);
		} else {
			AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(ALARM_SERVICE);

            alarmManager.cancel(pi3);
		}
		
		if(cbWed.isChecked()) {
			pi4 = PendingIntent.getBroadcast(MainActivity.this, 4, startIntent, 0);
			forDayStart(Calendar.WEDNESDAY, pi4);
		} else {
			AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(ALARM_SERVICE);

            alarmManager.cancel(pi4);
		}
		
		if(cbThu.isChecked()) {
			pi5 = PendingIntent.getBroadcast(MainActivity.this, 5, startIntent, 0);
			forDayStart(Calendar.THURSDAY, pi5);
		} else {
			AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(ALARM_SERVICE);

            alarmManager.cancel(pi5);
		}
		
		if(cbFri.isChecked()) {
			pi6 = PendingIntent.getBroadcast(MainActivity.this, 6, startIntent, 0);
			forDayStart(Calendar.SUNDAY, pi6);
		} else {
			AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(ALARM_SERVICE);

            alarmManager.cancel(pi6);
		}
		
		if(cbSat.isChecked()) {
			pi7 = PendingIntent.getBroadcast(MainActivity.this, 7, startIntent, 0);
			forDayStart(Calendar.SATURDAY, pi7);
		} else {
			AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(ALARM_SERVICE);

            alarmManager.cancel(pi7);
		}
		/**/
	}
	
	public void forDayStop(int week, PendingIntent p) {
		
		cstop = (Calendar) calNow.clone();
		
		cstop.set(Calendar.DAY_OF_WEEK, week);
		cstop.set(Calendar.HOUR_OF_DAY, tpEnd.getCurrentHour());
		cstop.set(Calendar.MINUTE, tpEnd.getCurrentMinute());
		cstop.set(Calendar.SECOND, 0);
		cstop.set(Calendar.MILLISECOND, 0);
		
		if(cstop.compareTo(cstart) <= 0) {
			cstop.add(Calendar.DAY_OF_WEEK, 1);
		}/**/
		alarmManagerStop.setRepeating(AlarmManager.RTC,cstop.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7, p); 
		
	}
	
	protected void setTimeForNormal() {
		
		if(cbSun.isChecked()) {
			pstop1 = PendingIntent.getBroadcast(MainActivity.this, 1, stopIntent, 0);
			forDayStop(Calendar.SUNDAY, pstop1);
		} else {
			AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(ALARM_SERVICE);

            alarmManager.cancel(pstop1);
		}
		
		if(cbMon.isChecked()) {
			pstop2 = PendingIntent.getBroadcast(MainActivity.this, 2, stopIntent, 0);
			forDayStop(Calendar.MONDAY, pstop2);
		} else {
			AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(ALARM_SERVICE);

            alarmManager.cancel(pstop2);
		}
		
		if(cbTue.isChecked()) {
			pstop3 = PendingIntent.getBroadcast(MainActivity.this, 3, stopIntent, 0);
			forDayStop(Calendar.TUESDAY, pstop3);
		} else {
			AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(ALARM_SERVICE);

            alarmManager.cancel(pstop3);
		}
		
		if(cbWed.isChecked()) {
			pstop4 = PendingIntent.getBroadcast(MainActivity.this, 4, stopIntent, 0);
			forDayStop(Calendar.WEDNESDAY, pstop4);
		} else {
			AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(ALARM_SERVICE);

            alarmManager.cancel(pstop4);
		}
		
		if(cbThu.isChecked()) {
			pstop5 = PendingIntent.getBroadcast(MainActivity.this, 5, stopIntent, 0);
			forDayStop(Calendar.THURSDAY, pstop5);
		} else {
			AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(ALARM_SERVICE);

            alarmManager.cancel(pstop5);
		}
		
		if(cbFri.isChecked()) {
			pstop6 = PendingIntent.getBroadcast(MainActivity.this, 6, stopIntent, 0);
			forDayStop(Calendar.SUNDAY, pstop6);
		} else {
			AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(ALARM_SERVICE);

            alarmManager.cancel(pstop6);
		}
		
		if(cbSat.isChecked()) {
			pstop7 = PendingIntent.getBroadcast(MainActivity.this, 7, stopIntent, 0);
			forDayStop(Calendar.SATURDAY, pstop7);
		} else {
			AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(ALARM_SERVICE);

            alarmManager.cancel(pstop7);
		}
		/**/
		
	}
	
	protected boolean noDayIsSet() {
		
		if(pi1!=null || pi2!=null || pi3!=null || pi4!=null || pi5!=null || pi6!=null || pi7!=null)
			return false;
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
