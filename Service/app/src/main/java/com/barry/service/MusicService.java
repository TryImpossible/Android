package com.barry.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {
    public MusicService() {
    }

    class MyBinder extends Binder implements ControllerInterface {

        @Override
        public void play() {
            MusicService.this.play();
        }

        @Override
        public void pause() {
            MusicService.this.pause();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }

    public void play() {
        System.out.print("播放音樂");
    }

    public void pause() {
        System.out.print("停止音樂");
    }

}
