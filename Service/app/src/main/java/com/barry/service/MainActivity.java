package com.barry.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends Activity {

    private PublicBusiness pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v) {
        Intent intent = new Intent(this, StartServiceActivity.class);
        startActivity(intent);
    }

    public void click2(View v) {
        Intent intent = new Intent(this, LeaderService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                pb = (PublicBusiness) service;
                pb.qianxian();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }

    public void click3(View v) {
        Intent intent = new Intent(this, MusicActivity.class);
        startActivity(intent);
    }

    public void click4(View v) {
        Intent intent = new Intent(this, ScreenListenerActivity.class);
        startActivity(intent);
    }

}
