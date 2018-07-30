package com.barry.broadcatreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        System.out.println("拉收到了短信廣播");
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] objects = (Object[]) bundle.get("pdus");
            for (Object object : objects) {
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) object);
                String address = sms.getOriginatingAddress();
                String body = sms.getMessageBody();
                System.out.println(address + ";" + body);

            }
        }
    }
}
