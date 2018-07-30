package com.barry.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.barry.fragment.newVersion.FragmentActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v) {
        Intent intent = new Intent(this, ContentProviderActivity.class);
        startActivity(intent);
    }

    public void click2(View v) {
        Intent intent = new Intent(this, com.barry.fragment.oldVersion.FragmentActivity.class);
        startActivity(intent);
    }

    public void click3(View v) {
        Intent intent = new Intent(this, FragmentActivity.class);
        startActivity(intent);
    }

    public void click4(View v) {
        Intent intent = new Intent(this, FrameAnimationActivity.class);
        startActivity(intent);
    }

    public void click5(View v) {
        Intent intent = new Intent(this, TweenAnimationActivity.class);
        startActivity(intent);
    }

    public void click6(View v) {
        Intent intent = new Intent(this, PropertyActivity.class);
        startActivity(intent);
    }
}
