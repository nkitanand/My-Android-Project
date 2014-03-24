package com.example.alarmmemo;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button bset;
	Button bcancel;
	EditText etinput;
	Toast mToast;
	AlarmManager am;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bset = (Button) findViewById(R.id.bset);
		bcancel = (Button) findViewById(R.id.bcancel);
		etinput = (EditText) findViewById(R.id.etinput);
		
		bset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
				
					int i = Integer.parseInt(etinput.getText().toString());
					Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
					PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 2, intent, 0);
					am = (AlarmManager) getSystemService(ALARM_SERVICE);
					am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (i*1000), pendingIntent);
				
				} catch(NumberFormatException e) {
					if(mToast != null) {
						mToast.cancel();
					}
					mToast = Toast.makeText(MainActivity.this, "Please enter number in the text field and try again!", Toast.LENGTH_LONG);
					mToast.show();
				}
			}
			
		});
		
		bcancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 2, intent, 0);
				am = (AlarmManager) getSystemService(ALARM_SERVICE);
				am.cancel(pendingIntent);
			
				if(mToast != null) {
					mToast.cancel();
				}
				mToast = Toast.makeText(MainActivity.this, "Alarm Cancelled!", Toast.LENGTH_LONG);
				mToast.show();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
