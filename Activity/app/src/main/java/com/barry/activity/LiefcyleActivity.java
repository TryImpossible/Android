package com.barry.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LiefcyleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liefcyle);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        System.out.println("主Create");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("主Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("主Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("主Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("主Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("主Destroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("主Restart");
    }

    public void click(View v) {
        Intent intent = new Intent(this, Liefcyle2Activity.class);
        startActivity(intent);
    }

    public void click2(View v) {
        finish();
    }
}
