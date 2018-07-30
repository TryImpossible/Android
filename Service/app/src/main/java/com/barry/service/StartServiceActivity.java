package com.barry.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class StartServiceActivity extends Activity {

    private boolean isBind = false;
    ServiceConnection conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);

        conn = new ServiceConnection() {
            //到服務連接被建立了，此方法調用
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            //到服務的連接中斷了，此方法調用
            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }

    public void start(View v) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void stop(View v) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    public void bind(View v) {
        Intent intent = new Intent(this, MyService.class);
        isBind = bindService(intent, conn, BIND_AUTO_CREATE);
    }

    public void unbind(View v) {
        if (isBind) {
            unbindService(conn);
            isBind = false;
        }
    }

    public void jump(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
