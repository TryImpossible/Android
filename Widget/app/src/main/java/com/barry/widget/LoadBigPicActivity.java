package com.barry.widget;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

public class LoadBigPicActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_big_pic);

//        System.out.println("路徑：" + Environment.getExternalStorageDirectory());
//        System.out.println("路徑：" + Environment.getDataDirectory());
//        System.out.println("路徑：" + Environment.getDownloadCacheDirectory());
//        System.out.println("路徑：" + Environment.getRootDirectory());
//        System.out.println("路徑：" + getCacheDir());
//        System.out.println("路徑：" + getFilesDir());
//        System.out.println("路徑：" + getExternalCacheDir().getAbsolutePath());
    }

    public void click(View v) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        //只請求圖片寬高，不解析圖片像素
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(getFilesDir() + "/dog.jpg", opts);
        //獲取圖片寬高
        int imageWidth = opts.outWidth;
        int imageHeight = opts.outHeight;

        //獲取屏幕寬高
        Display dp = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        dp.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

        //計算綻放比例
        int scale = 1;
        int scaleWidth = imageWidth / screenWidth;
        int scaleHeight = imageHeight / screenHeight;

        //判斷取哪個比例
        if (scaleWidth >= scaleHeight && scaleWidth > 1) {
            scale = scaleWidth;
        } else if (scaleWidth < scaleHeight && scaleHeight > 1) {
            scale = scaleHeight;
        }

        //設置縮小比例
        opts.inSampleSize = scale;
        opts.inJustDecodeBounds = false;

        Bitmap bm = BitmapFactory.decodeFile(getFilesDir() + "/dog.jpg", opts);

        ImageView iv = (ImageView) findViewById(R.id.iv);
        iv.setImageBitmap(bm);
    }
}
