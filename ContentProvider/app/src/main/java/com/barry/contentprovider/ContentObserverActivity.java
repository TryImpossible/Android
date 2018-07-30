package com.barry.contentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

public class ContentObserverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_observer);

        //註冊內容觀察者，觀察者就生效了，可以接受內容提供者發出的通知
        ContentResolver resolver = getContentResolver();
        //arg0:指定接收哪個內容提供者發出的通知
        resolver.registerContentObserver(Uri.parse("content://sms"),
                true, //如果為true，以這個uri作為開頭的uri上的數據改變了，該內容觀察者都會收到通知
                new MyObserver(new Handler()));
    }

    class MyObserver extends ContentObserver {

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MyObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            System.out.println("短信數據庫發生改變");
        }
    }

    public void aaa() {
        getContentResolver().registerContentObserver(Uri.parse("content://com.barry.people"),
                true, new ContentObserver(new Handler()) {
                    @Override
                    public void onChange(boolean selfChange) {
                        super.onChange(selfChange);
                        System.out.println("人數據庫改變");
                    }
                });
    }
}
