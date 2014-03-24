package com.example.silencer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
 
public class ReceiverStop extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
       Intent service2 = new Intent(context, SetRingerMode.class);
       context.startService(service2);
       
    }   
}
