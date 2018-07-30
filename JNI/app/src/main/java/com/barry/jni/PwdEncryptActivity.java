package com.barry.jni;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PwdEncryptActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd_encrypt);
    }

    public void click(View v) {
        EditText et = (EditText) findViewById(R.id.et);
        int pwd = Integer.parseInt(et.getText().toString());
        int newPwd = JNIUtil.encode(pwd);
        Toast.makeText(this, "加密后：" + newPwd, Toast.LENGTH_SHORT).show();
    }
}
