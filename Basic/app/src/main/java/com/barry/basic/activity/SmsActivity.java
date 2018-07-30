package com.barry.basic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.barry.basic.R;

import java.util.ArrayList;

public class SmsActivity extends Activity {

    private EditText et_phone;
    private EditText et_sms;
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        et_phone = (EditText) findViewById(R.id.et_phone);
        et_sms = (EditText) findViewById(R.id.et_sms);

        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager sms = SmsManager.getDefault();
                String phone = et_phone.getText().toString().trim();
                String smsContent = et_sms.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(SmsActivity.this, "请输入手机号码！", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(smsContent)) {
                    Toast.makeText(SmsActivity.this, "请输入短信内容！", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayList<String> smsList = sms.divideMessage(smsContent);
                    for (String str : smsList) {
                        sms.sendTextMessage(phone, null, str ,null, null);
                    }
//                    sms.sendMultipartTextMessage(phone, null, smsList, null, null);
                }
            }
        });
    }
}
