package com.barry.widget;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class VideoPlayerActivity extends Activity {

    private MediaPlayer player;
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        SurfaceView sv = (SurfaceView) findViewById(R.id.sv);
        SurfaceHolder holder = sv.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            //surfaceview創建時會調用
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (player == null) {
                    player = new MediaPlayer();
                    player.reset();

                    try {
                        AssetFileDescriptor descriptor = getAssets().openFd("2.3gp");
                        player.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                        descriptor.close();

                        player.setDisplay(holder);
                        player.prepare();
                        player.seekTo(progress);
                        player.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            //surfaceview結構發生改變時會調用
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            //surfaceview銷燬時會調用
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (player != null) {
                    progress = player.getCurrentPosition();
                    player.stop();
                    player.release();
                    player = null;
                }
            }
        });
    }
}
