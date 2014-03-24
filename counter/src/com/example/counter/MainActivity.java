package com.example.counter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	int counter;
	Button add;
	Button sub;
	TextView display;
	
	Intro ob; // to pause and play sound while onPause() and onResume()

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		counter = 0;
		add = (Button) findViewById(R.id.badd);
		sub = (Button) findViewById(R.id.bsub);
		display = (TextView) findViewById(R.id.TVdisplay);
		ob = new Intro();
		
		add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				counter++;
				display.setText("Your counter Value is "+ counter);
			}
		});
		
		sub.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				counter--;
				display.setText("Your counter Value is "+ counter);
			}
		});
	}
	
	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//ob.ourSong.pause();
	}

	/*

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//ob.ourSong.start();
	}

	*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
