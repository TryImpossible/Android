package com.barry.contentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class SystemSmsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_sms);
    }

    public void query(View v) {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(Uri.parse("content://sms"), new String[]{"address", "date", "type", "body"}, null, null, null);
        while(cursor.moveToNext()) {
            String address = cursor.getString(0);
            long date = cursor.getLong(1);
            int type = cursor.getInt(2);
            String body = cursor.getString(3);

            System.out.println(address + ";" + date + ";" + type + ";" + body);
        }
    }

    public void insert(View v) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ContentResolver resolver = getContentResolver();
                ContentValues values = new ContentValues();
                values.put("address", 95555);
                values.put("date", System.currentTimeMillis());
                values.put("type", 1);
                values.put("body", "您尾号为XXXX的招行储蓄卡收到转账1,000,000");
                resolver.insert(Uri.parse("content://sms"), values);
            }
        });
        t.start();

    }
}
