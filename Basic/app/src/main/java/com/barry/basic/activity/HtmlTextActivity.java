package com.barry.basic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.barry.basic.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HtmlTextActivity extends Activity {

    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String text = (String) msg.obj;
                    TextView tv = (TextView) findViewById(R.id.tv);
                    tv.setText(text);
                    break;
                case 2:
                    Toast.makeText(HtmlTextActivity.this, "加載失敗", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_text);
    }

    public void click(View v) {
        final String path = "http://192.168.16.84:8080/android-basic/baidu.html";
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(8000);
                    conn.setConnectTimeout(8000);
                    conn.connect();
                    if (conn.getResponseCode() == 200) {
                        InputStream is = conn.getInputStream();
                        byte[] b = new byte[1024];
                        int len;
                        ByteArrayOutputStream bros = new ByteArrayOutputStream();
                        while ((len = is.read(b)) != -1) {
                            bros.write(b, 0, len);
                        }
                        bros.close();
                        String text = new String(bros.toByteArray());

                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = text;
                        handler.sendMessage(msg);
                    }

                } catch (MalformedURLException e) {
                    handler.sendEmptyMessage(2);
                    e.printStackTrace();
                } catch (IOException e) {
                    handler.sendEmptyMessage(2);
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
