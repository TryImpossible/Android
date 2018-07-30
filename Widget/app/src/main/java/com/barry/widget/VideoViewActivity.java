package com.barry.widget;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

public class VideoViewActivity extends Activity {

    public final static String TAG = "VideoViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

//        String path = getExternalFilesDir("Movies").getAbsolutePath() + "/2.3gp";
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Movies/2.3gp";
        String path = getFilesDir().getAbsolutePath() + "/2.3gp";
//        Log.i(TAG, getExternalFilesDir(null).getAbsolutePath());

        VideoView vv = (VideoView) findViewById(R.id.vv);
//        vv.setVideoPath("http://192.168.1.126:8080/android-basic/2.3gp");
        Log.i(TAG, path);
        vv.setVideoPath(path);
        vv.start();
    }
}
