package com.barry.broadcatreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        String order = getResultData();
        System.out.println("市政府收到文件：" + order);
        setResultData("每人發60斤大米");

    }
}
