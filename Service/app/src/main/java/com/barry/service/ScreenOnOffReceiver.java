package com.barry.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenOnOffReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_SCREEN_ON)) {
            System.out.println("屏幕亮了");
        } else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            System.out.println("屏幕滅了");
        }
    }
}
