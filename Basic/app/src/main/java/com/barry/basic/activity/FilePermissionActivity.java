package com.barry.basic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.barry.basic.R;

import java.io.FileOutputStream;

public class FilePermissionActivity extends Activity {

    public static final String TAG = "FilePermissionActivity";

    private TextView tv_private;
    private TextView tv_read;
    private TextView tv_read_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_permission);
        tv_private = (TextView) findViewById(R.id.tv_private);
        tv_read = (TextView) findViewById(R.id.tv_read);
        tv_read_write = (TextView) findViewById(R.id.tv_read_write);
    }

    public void click1(View v) {
        ///data/data/com.barry.basic/files/info1.txt
        try {
            FileOutputStream fos = openFileOutput("info1.txt", MODE_PRIVATE);
            fos.write("hahaha".getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void click2(View v) {
        ///data/data/com.barry.basic/files/info2.txt
        try {
            @SuppressWarnings("deprecation")
            FileOutputStream fos = openFileOutput("info2.txt", MODE_WORLD_READABLE);
            fos.write("lalala".getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void click3(View v) {
        ///data/data/com.barry.basic/files/info3.txt
        try {
            @SuppressWarnings("deprecation")
            FileOutputStream fos = openFileOutput("info3.txt", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
            fos.write("hehehe".getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
