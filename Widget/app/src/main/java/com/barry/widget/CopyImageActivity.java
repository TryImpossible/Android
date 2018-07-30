package com.barry.widget;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

public class CopyImageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_image);

        //加載原圖
        Bitmap bmSrc = BitmapFactory.decodeResource(getResources(), R.drawable.photo3);

        //創建副本
        //1.創建與原圖一模一樣大小的Bitmap對象，譔對象中目前是沒有內容的，可以比喻為創建了和原圖一樣大小的白紙
        Bitmap bmCopy = Bitmap.createBitmap(bmSrc.getWidth(), bmSrc.getHeight(), bmSrc.getConfig());
        //2.創建畫筆的對象
        Paint paint = new Paint();
        //3.創建畫板，把白紙鋪到畫板上
        Canvas canvas  = new Canvas(bmCopy);

        Matrix mt = new Matrix();
        //平移效果，指定平移距離
//        mt.setTranslate(20, 10);
        //縮放效果，指定縮放比例
//        mt.setScale(2, 0.5f);
//        mt.setScale(2, 0.5f, bmCopy.getWidth() / 2, bmCopy.getHeight() / 2);
        //旋轉效果
//        mt.setRotate(45);
//        mt.setRotate(45, bmCopy.getWidth() / 2, bmCopy.getHeight() / 2);
        //鏡面效果
//        mt.setScale(-1, 1);
//        mt.postTranslate(bmCopy.getWidth(), 0);

        //倒影效果
        mt.setScale(1, -1);
        mt.postTranslate(0, bmCopy.getHeight());

        //4.開始作畫
        canvas.drawBitmap(bmSrc, mt, paint);

        ImageView iv_src = (ImageView) findViewById(R.id.iv_src);
        iv_src.setImageBitmap(bmSrc);

        ImageView iv_copy = (ImageView) findViewById(R.id.iv_copy);
        iv_copy.setImageBitmap(bmCopy);

    }
}
