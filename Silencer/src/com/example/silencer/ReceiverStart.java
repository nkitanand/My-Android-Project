package com.example.silencer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
 
public class ReceiverStart extends BroadcastReceiver
{
      
    @Override
    public void onReceive(Context context, Intent intent)
    {
       Intent service1 = new Intent(context, SetSilentMode.class);
       context.startService(service1);
        
    }   
}
