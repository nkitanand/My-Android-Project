package com.example.counter;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity implements View.OnClickListener {
	Button chkCmd;
	ToggleButton passTog;
	EditText input;
	TextView display;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		
		reference();
		
		passTog.setOnClickListener(this);
		
		chkCmd.setOnClickListener(this);
	}
	
	public void reference() {
		chkCmd = (Button) findViewById(R.id.bResults);
		passTog = (ToggleButton) findViewById(R.id.tbPassword);
		input = (EditText) findViewById(R.id.etCommands);
		display = (TextView) findViewById(R.id.tvResults);
	
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
		case (R.id.bResults):
			// TODO Auto-generated method stub
			String check = input.getText().toString();
			
			if(check.contentEquals("left")) {
				display.setText("left");
				display.setGravity(Gravity.LEFT);
			} else if(check.contentEquals("center")) {
				display.setText("center");
				display.setGravity(Gravity.CENTER);
			} else if(check.contentEquals("right")) {
				display.setText("right");
				display.setGravity(Gravity.RIGHT);
			} else if(check.contentEquals("blue")) {
				display.setText("blue");
				display.setTextColor(Color.BLUE);
			} else if(check.contentEquals("wtf")) {
				Random masti = new Random();
				int grmasti = masti.nextInt(75);
				display.setText("WTF!!!!");
				display.setTextSize(grmasti);
				display.setTextColor(Color.rgb(masti.nextInt(265), masti.nextInt(265), masti.nextInt(265)));
				switch(masti.nextInt(3)) {
				case 0:
						display.setGravity(Gravity.LEFT);
						break;
				case 1:
						display.setGravity(Gravity.RIGHT);
						break;
				case 2:
						display.setGravity(Gravity.CENTER);
						break;
				}
			} else {
				display.setText("Invalid");
				display.setTextColor(Color.BLACK);
				display.setGravity(Gravity.CENTER);
			}
			break;
		case (R.id.tbPassword):
			// TODO Auto-generated method stub
			if (passTog.isChecked()) {
				input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
			} else {
				input.setInputType(InputType.TYPE_CLASS_TEXT);
			}
			break;
		}
	}

}
