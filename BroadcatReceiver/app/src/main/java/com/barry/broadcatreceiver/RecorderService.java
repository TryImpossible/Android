package com.barry.broadcatreceiver;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import java.io.IOException;

public class RecorderService extends Service {

    private MediaRecorder recorder;

    public RecorderService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        tm.listen(new MyListener(), PhoneStateListener.LISTEN_CALL_STATE);
    }

    class MyListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            System.out.println("電話狀態：" + state);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE: //無任何狀態
                    if (recorder != null) {
                        System.out.println("停止錄音");
                        recorder.stop();


                        recorder.release();
                        recorder = null;
                    }
                    break;
                case TelephonyManager.CALL_STATE_RINGING: //電話鈴響
                    if (recorder == null) {
                        recorder = new MediaRecorder();
                        //設置音頻來源
                        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        //設置輸出格式
                        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        recorder.setOutputFile(Environment.getExternalStorageDirectory() + "/" + "voice.3gp");
                        //設置音頻編碼
                        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

                        try {
                            System.out.println("準備好");
                            recorder.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK: //接聽電話
                    if (recorder != null) {
                        System.out.print("開始錄音");
                        recorder.start();
                    }
                    break;
            }
        }
    }
}
