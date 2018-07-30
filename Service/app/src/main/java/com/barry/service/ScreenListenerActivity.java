package com.barry.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ScreenListenerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_listener);
    }

    public void start(View v) {
        Intent intent = new Intent(this, RegisterService.class);
        startService(intent);
    }

    public void stop(View v) {
        Intent intent = new Intent(this, RegisterService.class);
        stopService(intent);
    }
}
