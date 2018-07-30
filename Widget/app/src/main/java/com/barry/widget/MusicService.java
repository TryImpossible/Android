package com.barry.widget;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MusicService extends Service {
    public MusicService() {
    }

    public final static String TAG = "MusicService";

    MediaPlayer player;
    private Timer timer;

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "銷燬player");
        //銷燬player
        player.stop();
        player.release();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return new MusicController();
    }

    class MusicController extends Binder implements ControllerInterface {

        @Override
        public void play() {
            MusicService.this.play();
        }

        @Override
        public void pasue() {
            MusicService.this.pause();
        }

        @Override
        public void continuePlay() {
            MusicService.this.continuePlay();
        }

        @Override
        public void seekTo(int progress) {
            MusicService.this.seekTo(progress);
        }
    }

    private void play() {

//        Log.i(TAG, "音樂文件路徑：" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath());
        player.reset();
        AssetFileDescriptor descriptor = null;
        try {
//            player.setDataSource("http://192.168.16.89:8080/android-basic/xingzhihuo.mp3");

            descriptor = getAssets().openFd("xingzhihuo.mp3");
            player.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            AudioAttributes.Builder builder = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                builder = new AudioAttributes.Builder();
                builder.setLegacyStreamType(AudioManager.STREAM_MUSIC);
                player.setAudioAttributes(builder.build());
            } else {
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            }
            //同步準備
//            player.prepare();
            //異步準備
            player.prepareAsync();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start();
                    addTimer();
                }
            });
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player.reset();
                    player.release();
                }
            });
            player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    player.reset();
                    return false;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pause() {
        player.pause();
    }

    private void continuePlay() {
        player.start();
    }

    private void seekTo(int progress) {
        player.seekTo(progress);
    }

    private void addTimer() {
        if (timer == null) {
            timer = new Timer();
            //設置計時任務
            timer.schedule(new TimerTask() {
                //這個run方法也是在子純種招待
                @Override
                public void run() {
                    //獲取播放總時長
                    int duration = player.getDuration();
                    int currentPosition = player.getCurrentPosition();
//                    Log.i(TAG, "當前位置：" + currentPosition);
                    Message msg = MusicPlayerActivity.handler.obtainMessage();
                    Bundle data = new Bundle();
                    data.putInt("duration", duration);
                    data.putInt("currentDuration", currentPosition);
                    msg.setData(data);
                    MusicPlayerActivity.handler.sendMessage(msg);
                }
            }, 5, 500);
        }
    }
}
