package com.barry.basic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.barry.basic.R;
import com.barry.basic.tool.Tools;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class HttpClientActivity extends Activity {

    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Toast.makeText(HttpClientActivity.this, (String)msg.obj, Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_client);

    }

    public void click1(View v) {
        //获取用户输入的账号密码
        EditText et_name = (EditText) findViewById(R.id.et_name);
        EditText et_pass = (EditText) findViewById(R.id.et_pass);

        String name = et_name.getText().toString();
        String pass = et_pass.getText().toString();

        final String path = "http://169.254.244.136/Web2/servlet/Login?name=" + URLEncoder.encode(name) + "&pass=" + pass;

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //android6.0後，谷歌移除了HttpClient
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet get = new HttpGet(path);
                try {
                    HttpResponse response = httpClient.execute(get);
                    StatusLine line = response.getStatusLine();
                    int code = line.getStatusCode();
                    if (code == 200) {
                        HttpEntity entity = response.getEntity();
                        InputStream is = entity.getContent();
                        String text = Tools.getTextFromStream(is);

                        Message msg = handler.obtainMessage();
                        msg.what = 1;
                        msg.obj = text;
                        handler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public void click2(View v) {
        EditText et_name = (EditText) findViewById(R.id.et_name);
        EditText et_pass = (EditText) findViewById(R.id.et_pass);

        final String name = et_name.getText().toString();
        final String pass = et_pass.getText().toString();

        final String path = "http://169.254.244.136/Web2/servlet/Login";

        Thread t = new Thread(){
            @Override
            public void run() {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost post = new HttpPost(path);
                List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
                parameters.add(new BasicNameValuePair("name", name));
                parameters.add(new BasicNameValuePair("pass", pass));
                UrlEncodedFormEntity entity = null;
                try {
                    entity = new UrlEncodedFormEntity(parameters, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                post.setEntity(entity);

                try {
                    HttpResponse response = httpClient.execute(post);
                    if (response.getStatusLine().getStatusCode() == 200) {
                        InputStream is = response.getEntity().getContent();
                        String text = Tools.getTextFromStream(is);

                        Message msg = handler.obtainMessage();
                        msg.what = 1;
                        msg.obj = text;
                        handler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}
