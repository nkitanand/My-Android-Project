package com.example.alarmbemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		
		Intent service1 = new Intent(arg0, MyService.class);
		arg0.startService(service1);
	}

}
