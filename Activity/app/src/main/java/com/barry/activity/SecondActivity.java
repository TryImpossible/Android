package com.barry.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        Uri uri = intent.getData();
        System.out.println(uri);

//        String name = intent.getStringExtra("name");
//        int age = intent.getIntExtra("age", 0);
//        System.out.println("姓名：" + name + "年齡：" + age);

        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        int age = bundle.getInt("age");
        System.out.println("姓名：" + name + "年齡：" + age);
    }
}
