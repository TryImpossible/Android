package com.barry.widget;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class MusicPlayerActivity extends Activity {

    private ControllerInterface controller;
    private static SeekBar sb;

    public static Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            int duration = data.getInt("duration");
            int currentDuration = data.getInt("currentDuration");

            //設置進度條顯示進度
            sb.setMax(duration);
            sb.setProgress(currentDuration);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        sb = (SeekBar) findViewById(R.id.sb);
        //設置滑動監聽
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //seekBar： 觸發譔方法執行的seekBar對象
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                controller.seekTo(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                controller = (ControllerInterface) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }

    public void play(View v) {
        if (controller != null) {
            controller.play();
        } else {
            Toast.makeText(this, "播放失敗", Toast.LENGTH_SHORT).show();
        }
    }

    public void pause(View v) {
        if (controller != null) {
            controller.pasue();
        } else {
            Toast.makeText(this, "暫停失敗", Toast.LENGTH_SHORT).show();
        }
    }

    public void continuePlay(View v) {
        if (controller != null) {
            controller.continuePlay();
        } else {
            Toast.makeText(this, "繼續播放失敗", Toast.LENGTH_SHORT).show();
        }
    }

}
