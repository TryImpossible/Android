package com.barry.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v) {
        Intent intent = new Intent(this, LoadBigPicActivity.class);
        startActivity(intent);
    }

    public void click2(View v) {
        Intent intent = new Intent(this, CopyImageActivity.class);
        startActivity(intent);
    }

    public void click3(View v) {
        Intent intent = new Intent(this, PaletteActivity.class);
        startActivity(intent);
    }

    public void click4(View v) {
        Intent intent = new Intent(this, ClothesActivity.class);
        startActivity(intent);
    }

    public void click5(View v) {
        Intent intent = new Intent(this, MusicPlayerActivity.class);
        startActivity(intent);
    }

    public void click6(View v) {
        Intent intent = new Intent(this, VideoPlayerActivity.class);
        startActivity(intent);
    }

    public void click7(View v) {
        Intent intent = new Intent(this, VideoViewActivity.class);
        startActivity(intent);
    }

    public void click8(View v) {
        Intent intent = new Intent(this, PhotographyActivity.class);
        startActivity(intent);
    }

    public void click9(View v) {
        Intent intent = new Intent(this, CustomPhotographyActivity.class);
        startActivity(intent);
    }
}
