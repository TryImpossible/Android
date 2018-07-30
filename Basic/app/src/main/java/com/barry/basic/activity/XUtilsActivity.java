package com.barry.basic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.barry.basic.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.File;

public class XUtilsActivity extends Activity {

    private ProgressBar pb;
    private TextView tv_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xutils);

        pb = (ProgressBar) findViewById(R.id.pb);
        tv_progress = (TextView) findViewById(R.id.tv_progress);
    }

    public void click(View v) {
        String path = "http://192.168.16.42:8080/android-basic/shadowsocks.apk";
        HttpUtils utils = new HttpUtils();
        utils.download(path, "sdcard/shadowsocks.apk", true, true, new RequestCallBack<File>() {
            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                TextView tv_success = (TextView) findViewById(R.id.tv_success);
                tv_success.setText(responseInfo.result.getPath());
            }

            @Override
            public void onFailure(HttpException e, String s) {
                TextView tv_failure = (TextView) findViewById(R.id.tv_failure);
                tv_failure.setText(s);
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                pb.setMax((int)total);
                pb.setProgress((int)current);
                tv_progress.setText(current * 100 / total + "%");
            }
        });
    }
}
