package com.barry.basic.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.barry.basic.R;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btn_call;
    private Button btn_sms;
    private Button btn_calc;
    private Button btn_relativeLayout;
    private Button btn_frameLayout;
    private Button btn_tableLayout;
    private Button btn_absoluteLayout;
    private Button btn_logcat;
    private Button btn_internalStorage;
    private Button btn_externalStorage;
    private Button btn_remainingStorage;
    private Button btn_filePermission;
    private Button btn_sharepreference;
    private Button btn_xml;
    private Button btn_sqlite;
    private Button btn_listview;
    private Button btn_remoteImage;
    private Button btn_htmlText;
    private Button btn_news;
    private Button btn_httpClient;
    private Button btn_asyncHttpClient;
    private Button btn_threadDowload;
    private Button btn_xutils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_call = (Button) this.findViewById(R.id.call);
        btn_call.setOnClickListener(this);

        btn_sms = (Button) findViewById(R.id.sms);
        btn_sms.setOnClickListener(this);

        btn_calc = (Button) findViewById(R.id.calc);
        btn_calc.setOnClickListener(this);

        btn_relativeLayout = (Button) findViewById(R.id.relativeLayout);
        btn_relativeLayout.setOnClickListener(this);

        btn_frameLayout = (Button) findViewById(R.id.frameLayout);
        btn_frameLayout.setOnClickListener(this);

        btn_tableLayout = (Button) findViewById(R.id.tableLayout);
        btn_tableLayout.setOnClickListener(this);

        btn_absoluteLayout = (Button) findViewById(R.id.absoluteLayout);
        btn_absoluteLayout.setOnClickListener(this);

        btn_logcat = (Button) findViewById(R.id.logcat);
        btn_logcat.setOnClickListener(this);

        btn_internalStorage = (Button) findViewById(R.id.internalStorage);
        btn_internalStorage.setOnClickListener(this);

        btn_externalStorage = (Button) findViewById(R.id.externalStorage);
        btn_externalStorage.setOnClickListener(this);

        btn_remainingStorage = (Button) findViewById(R.id.remainingStorage);
        btn_remainingStorage.setOnClickListener(this);

        btn_filePermission = (Button) findViewById(R.id.filePermission);
        btn_filePermission.setOnClickListener(this);

        btn_sharepreference = (Button) findViewById(R.id.sharepreference);
        btn_sharepreference.setOnClickListener(this);

        btn_xml = (Button) findViewById(R.id.xml);
        btn_xml.setOnClickListener(this);

        btn_sqlite = (Button) findViewById(R.id.sqlite);
        btn_sqlite.setOnClickListener(this);

        btn_listview = (Button) findViewById(R.id.listview);
        btn_listview.setOnClickListener(this);

        btn_remoteImage = (Button) findViewById(R.id.remoteImage);
        btn_remoteImage.setOnClickListener(this);

        btn_htmlText = (Button) findViewById(R.id.htmlText);
        btn_htmlText.setOnClickListener(this);

        btn_news = (Button) findViewById(R.id.news);
        btn_news.setOnClickListener(this);

        btn_httpClient = (Button) findViewById(R.id.httpClient);
        btn_httpClient.setOnClickListener(this);

        btn_asyncHttpClient = (Button) findViewById(R.id.asyncHttpClient);
        btn_asyncHttpClient.setOnClickListener(this);

        btn_threadDowload = (Button) findViewById(R.id.threadDownload);
        btn_threadDowload.setOnClickListener(this);

        btn_xutils = (Button) findViewById(R.id.xutils);
        btn_xutils.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.call:
                startActivity(CallActivity.class);
                break;
            case R.id.sms:
                startActivity(SmsActivity.class);
                break;
            case R.id.calc:
                startActivity(CalculatorActivity.class);
                break;
            case R.id.relativeLayout:
                startActivity(RelativeLayoutActivity.class);
                break;
            case R.id.frameLayout:
                startActivity(FrameLayoutActivity.class);
                break;
            case R.id.tableLayout:
                startActivity(TableLayoutActivity.class);
                break;
            case R.id.absoluteLayout:
                startActivity(AbsoluteLayoutActivity.class);
                break;
            case R.id.logcat:
                startActivity(LogCatActivity.class);
                break;
            case R.id.internalStorage:
                startActivity(InternalStroageActivity.class);
                break;
            case R.id.externalStorage:
                startActivity(ExternalStorageActivity.class);
                break;
            case R.id.remainingStorage:
                startActivity(RemainingStorageActivity.class);
                break;
            case R.id.filePermission:
                startActivity(FilePermissionActivity.class);
                break;
            case R.id.sharepreference:
                startActivity(SharepreferenceActivity.class);
                break;
            case R.id.xml:
                startActivity(XmlActivity.class);
                break;
            case R.id.sqlite:
                startActivity(SQLiteActivity.class);
                break;
            case R.id.listview:
                startActivity(ListViewActivity.class);
                break;
            case R.id.remoteImage:
                startActivity(RemoteImageActivity.class);
                break;
            case R.id.htmlText:
                startActivity(HtmlTextActivity.class);
                break;
            case R.id.news:
                startActivity(NewsListActivity.class);
                break;
            case R.id.httpClient:
                startActivity(HttpClientActivity.class);
                break;
            case R.id.asyncHttpClient:
                startActivity(AsyncHttpClientActivity.class);
                break;
            case R.id.threadDownload:
                startActivity(ThreadDowloadActivity.class);
                break;
            case R.id.xutils:
                startActivity(XUtilsActivity.class);
                break;
        }
    }

    public void startActivity(Class clazz) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivity(intent);
    }
}
