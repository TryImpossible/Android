package com.barry.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

public class PayService extends Service {
    public PayService() {
    }

    private Binder myBinder = new IPayInterface.Stub() {

        @Override
        public void pay() throws RemoteException {
            PayService.this.pay();
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return myBinder;
    }

    private void pay() {
        System.out.println("检查支付环境");
        System.out.println("加密账号密码");
        System.out.println("上传账号密码");
        System.out.println("完成支付");
    }
}
