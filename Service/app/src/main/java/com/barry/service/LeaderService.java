package com.barry.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LeaderService extends Service {
    public LeaderService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new FengMiShu();
    }

    public void banZheng() {
        System.out.println("成功辦證");
    }

    class FengMiShu extends Binder implements PublicBusiness{

        @Override
        public void qianxian() {
            banZheng();
        }

        public void takeSoap() {

        }
    }
}
