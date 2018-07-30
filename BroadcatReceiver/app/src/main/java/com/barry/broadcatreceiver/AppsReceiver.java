package com.barry.broadcatreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class AppsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        System.out.println("接收到了");
        String action = intent.getAction();
        Uri uri = intent.getData();
        if (Intent.ACTION_PACKAGE_ADDED.equals(action)) {
            Toast.makeText(context, uri + "被安裝了", Toast.LENGTH_SHORT).show();
            System.out.println(uri + "被安裝了");
        } else if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
            Toast.makeText(context, uri + "被刪除了", Toast.LENGTH_SHORT).show();
            System.out.println(uri + "被刪除了");
        } else if (Intent.ACTION_PACKAGE_REPLACED.equals(action)) {
            Toast.makeText(context, uri + "被更新了", Toast.LENGTH_SHORT).show();
            System.out.println(uri + "被更新了");
        }
    }
}
