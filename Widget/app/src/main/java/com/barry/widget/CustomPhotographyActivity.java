package com.barry.widget;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CustomPhotographyActivity extends Activity {

    private Camera mCamera;
    private CameraPreview mPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_photography);
        if (checkCameraHardware(this)) {
            mCamera = getCameraInstance();
        } else {
            return;
        }
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);

        Button captureButton = (Button) findViewById(R.id.button_capture);
        captureButton.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //自动聚焦
                        mCamera.autoFocus(new Camera.AutoFocusCallback() {

                            //聚焦完成调用
                            @Override
                            public void onAutoFocus(boolean success, Camera camera) {
                                // 拍照
                                mCamera.takePicture(null, null, mPicture);

                            }
                        });

                    }
                }
        );
    }

    //檢測手機是否安裝有攝像頭應用
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    //一個獲取攝像頭實例的安全途徑
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            //獲取第一個後置攝像頭的實例
            int cametacount = Camera.getNumberOfCameras();
            c = Camera.open(cametacount-1);
//            c = Camera.open(0);
        } catch (Exception e) {

        }
        return c;
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            try {
                File pictureFile = new File(getExternalFilesDir("Pictures").getAbsolutePath() + "/mrplus.jpg");
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                camera.startPreview();
            }
        }
    };
}
