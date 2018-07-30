package com.barry.activity;

import android.app.Activity;
import android.os.Bundle;

public class Liefcyle2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liefcyle2);
        System.out.println("二Create");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("二Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("二Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("二Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("二Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("二Destroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("二Restart");
    }
}
