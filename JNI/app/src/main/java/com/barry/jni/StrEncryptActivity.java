package com.barry.jni;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StrEncryptActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_str_encrypt);
    }

    public void encode(View v) {
        EditText et = (EditText) findViewById(R.id.et);
        String pass = et.getText().toString();
        String newPass = JNIUtil.encodeStr(pass, pass.length());
        et.setText(newPass);
    }

    public void decode(View v) {
        EditText et = (EditText) findViewById(R.id.et);
        String pass = et.getText().toString();
        String newPass = JNIUtil.decodeStr(pass, pass.length());
        et.setText(newPass);
    }
}
