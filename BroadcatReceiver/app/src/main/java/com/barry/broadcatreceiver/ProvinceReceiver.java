package com.barry.broadcatreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ProvinceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        String order = getResultData();
        System.out.println("省政府收到文件：" + order);
        setResultData("每人發80斤大米");
    }
}
