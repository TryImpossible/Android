package com.barry.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LauncherMode1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_mode1);
    }

    public void click1(View v) {
        Intent intent = new Intent(this, LauncherMode1Activity.class);
        startActivity(intent);
    }

    public void click2(View v) {
        Intent intent = new Intent(this, LauncherMode2Activity.class);
        startActivity(intent);
    }
}
