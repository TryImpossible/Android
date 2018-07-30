package com.barry.broadcatreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SDReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");

        String action = intent.getAction();
        if (Intent.ACTION_MEDIA_MOUNTED.equals(action)) {
            Toast.makeText(context, "sd卡就緒", Toast.LENGTH_SHORT).show();
        } else if (Intent.ACTION_MEDIA_REMOVED.equals(action)) {
            Toast.makeText(context, "sd卡被拔出了", Toast.LENGTH_SHORT).show();
        } else if (Intent.ACTION_MEDIA_UNMOUNTED.equals(action)) {
            Toast.makeText(context, "sd卡被卸載了", Toast.LENGTH_SHORT).show();
        }

    }
}
