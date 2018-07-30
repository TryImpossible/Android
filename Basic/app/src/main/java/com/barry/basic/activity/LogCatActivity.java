package com.barry.basic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.barry.basic.R;

public class LogCatActivity extends Activity {

    private static final String TAG = "LogCatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_cat);

        Log.d(TAG, "Debug-调试");
        Log.e(TAG, "Error-错误");
        Log.i(TAG, "info-信息");
        Log.v(TAG, "Verbose-详细");
        Log.w(TAG, "Warning-警告");
    }
}
