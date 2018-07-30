package com.barry.broadcatreceiver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v) {
        Intent intent = new Intent();
        intent.setAction("a.b.c");
        sendBroadcast(intent);
    }

    public void click2(View v) {
        Intent intent = new Intent();
        intent.setAction("com.center.fdm");
        sendOrderedBroadcast(intent, null, new MyReceiver(), null, 0, "每人發100斤大米", null);
    }

    class MyReceiver extends BootReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String order = getResultData();
            System.out.println("反貪局收到文件：" + order);
        }
    }

    public void click3(View v) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void click4(View v) {
        Intent intent = new Intent(this, RecorderService.class);
        startService(intent);
    }
}
