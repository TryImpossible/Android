package com.barry.basic.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.barry.basic.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RemoteImageActivity extends Activity {

    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    ImageView iv = (ImageView) findViewById(R.id.iv);
                    iv.setImageBitmap((Bitmap) msg.obj);
                    break;
                case 2:
                    Toast.makeText(RemoteImageActivity.this, "請求失敗了", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_image);

    }

    public void dowload(View v) {
        final String path = "http://127.0.0.1:8080/android-basic/head.jpg";
        final File file = new File(getCacheDir() + "/" + getNameFromPath(path));
        System.out.println(file.getAbsolutePath());
        if (file.exists()) {
            System.out.println("從緩存獲取");
            Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());

            Message msg = new Message();
            msg.what = 1;
            msg.obj = bm;
            handler.sendMessage(msg);
        } else {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("從網絡獲取");
                    try {
                        //1.使用网址构造一个URL对象
                        URL url = new URL(path);
                        //2.获取连接对象
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        //3.设置一些属性
                        //设置请求方式，注意大写
                        conn.setRequestMethod("GET");
                        //设置请求超时
                        conn.setConnectTimeout(8000);
                        //设置读取超时
                        conn.setReadTimeout(8000);
                        //4.发送请求，建立连接
                        conn.connect();
                        //5.判断请求是否成功
                        if (conn.getResponseCode() == 200) {
                            //获取服务器返回的流，流里就是客户端请求的数据
                            InputStream is = conn.getInputStream();

                            byte[] b = new byte[1024];
                            int len;
                            FileOutputStream fos = new FileOutputStream(file);
                            while((len = is.read(b)) != -1) {
                                fos.write(b, 0, len);
                            }
                            fos.close();


//						因为缓存时已经把流里数据读取完了，此时流里没有任何数据
//						Bitmap bm = BitmapFactory.decodeStream(is);
                            Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());

                            //当子线程需要刷新ui时，只需发送一条消息至消息队列
                            Message msg = new Message();
                            //消息对象本身是可以携带数据的
                            msg.obj = bm;
                            //使用what标注消息是什么类型的
                            msg.what = 1;
                            handler.sendMessage(msg);
                        } else {
                            handler.sendEmptyMessage(2);
                        }
                    } catch (Exception e) {
//                        如果消息池有消息，取出消息池第一条消息，返回，如果没有，就new一个消息返回
                        Message msg = handler.obtainMessage();
                        msg.what = 2;
                        handler.sendMessage(msg);

                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
    }

    public String getNameFromPath(String path) {
        int index = path.lastIndexOf("/");
        String name = path.substring(index + 1, path.length());
        System.out.println(name);
        return name;
    }
}
