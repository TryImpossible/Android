package com.barry.widget;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PaletteActivity extends Activity {

    private int startX;
    private int startY;
    private Paint paint;
    private Canvas canvas;
    private ImageView iv;
    private Bitmap bmCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        Bitmap bmSrc = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        //白紙
        bmCopy = Bitmap.createBitmap(bmSrc.getWidth(), bmSrc.getHeight(), bmSrc.getConfig());
        //筆
        paint = new Paint();
        //畫板
        canvas = new Canvas(bmCopy);
        //作畫
        canvas.drawBitmap(bmSrc, new Matrix(), paint);

        iv = (ImageView) findViewById(R.id.iv);
        iv.setImageBitmap(bmCopy);

        iv.setOnTouchListener(new View.OnTouchListener() {
            //用戶手指只要接觸屏幕，就會產生觸摸事件
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //判斷觸摸事件的類型
                switch (event.getAction()) {
                    //手指觸摸
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;

                    //手指滑動
                    case MotionEvent.ACTION_MOVE:
                        int newX = (int) event.getX();
                        int newY = (int) event.getY();
                        canvas.drawLine(startX, startY, newX, newY, paint);
                        iv.setImageBitmap(bmCopy);

                        startX = newX;
                        startY = newY;
                        break;

                    //手指擡起
                    case MotionEvent.ACTION_UP:
                        break;
                }
                //true表示告訴系統，這個觸摸事件由iv處理
                //false表示不處理該觸摸事件，事件往上傳遞
                return true;
            }
        });
    }

    public void red(View v) {
        paint.setColor(Color.RED);
    }

    public void green(View v) {
        paint.setColor(Color.GREEN);
    }

    public void brush(View v) {
        paint.setStrokeWidth(8);
    }

    public void save(View v) {
        File file = new File(getExternalFilesDir("pictrues").getAbsolutePath(), "dazuo.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
            bmCopy.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
        intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
        sendBroadcast(intent);
    }
}
