package com.barry.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

public class RemoteService extends Service {
    public RemoteService() {
    }

    private Binder myBinder = new IPublicBusinessInterface.Stub(){

        @Override
        public void qianxian() throws RemoteException {
            RemoteService.this.remoteBanZheng();
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("綁定");
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return myBinder;
    }

    @Override
    public void onCreate() {
        System.out.println("創建");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        System.out.println("銷燬");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("解綁");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("開始");
        return super.onStartCommand(intent, flags, startId);
    }

    public void remoteBanZheng() {
        System.out.println("苗領導在國外遠程辦證");
    }
}
