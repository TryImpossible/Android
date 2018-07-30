package com.barry.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CallbackDataActivity extends Activity {

    public static final int CONTACT = 10;
    public static final int SMS = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callback_data);
    }

    public void click1(View v) {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivityForResult(intent, CONTACT);
    }

    public void click2(View v) {
        Intent intent = new Intent(this, SmsActivity.class);
        startActivityForResult(intent, SMS);
    }

    public void click3(View v) {
        Toast.makeText(this, "發送成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("requestCode:" + requestCode);
        System.out.println("resultCode:" + resultCode);
        System.out.println("data:" + data.toString());
        if (requestCode == CONTACT) {
            String name = data.getStringExtra("name");
            EditText et_name = (EditText) findViewById(R.id.et_name);
            et_name.setText(name);
        } else if (requestCode == SMS) {
            String sms = data.getStringExtra("sms");
            EditText et_sms = (EditText) findViewById(R.id.et_content);
            et_sms.setText(sms);
        }
    }
}
