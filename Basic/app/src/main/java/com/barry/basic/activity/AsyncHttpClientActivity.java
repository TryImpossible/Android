package com.barry.basic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.barry.basic.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

public class AsyncHttpClientActivity extends Activity {

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if ((int)msg.obj == 1) {
                Toast.makeText(AsyncHttpClientActivity.this, (String)msg.obj, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_http_client);


    }

    public void click1(View v) {
        EditText et_name = (EditText) findViewById(R.id.et_name);
        EditText et_pass = (EditText) findViewById(R.id.et_pass);

        String name = et_name.getText().toString();
        String pass = et_pass.getText().toString();

        String path = "http://169.254.244.136/Web2/servlet/Login";

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("name", name);
        rp.put("pass", pass);
        client.get(path, rp, new MyHandler());
    }

    public void click2(View v) {
        EditText et_name = (EditText) findViewById(R.id.et_name);
        EditText et_pass = (EditText) findViewById(R.id.et_pass);

        String name = et_name.getText().toString();
        String pass = et_pass.getText().toString();

        String path = "http://169.254.244.136/Web2/servlet/Login";

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("name", name);
        rp.put("pass", pass);
        client.post(path, rp, new MyHandler());

    }

    class MyHandler extends AsyncHttpResponseHandler {

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            Toast.makeText(AsyncHttpClientActivity.this, new String(responseBody), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Toast.makeText(AsyncHttpClientActivity.this, "請求失敗", Toast.LENGTH_SHORT).show();
        }
    }
}
