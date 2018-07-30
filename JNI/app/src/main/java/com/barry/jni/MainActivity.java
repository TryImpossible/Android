package com.barry.jni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getStrFromC(View v) {
        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(JNIUtil.helloFromC());
        Toast.makeText(this, JNIUtil.helloFromC(), Toast.LENGTH_SHORT).show();
    }

    public void add(View v) {
        Toast.makeText(this, "3+5等于" + JNIUtil.add(3, 5), Toast.LENGTH_SHORT).show();
    }

    public void pwdEncrypt(View v) {
        Intent intent = new Intent(this, PwdEncryptActivity.class);
        startActivity(intent);
    }

    public void strEncrypt(View v) {
        Intent intent = new Intent(this, StrEncryptActivity.class);
        startActivity(intent);
    }

    public void transmitArray(View v) {
        Intent intent = new Intent(this, TransmitActivity.class);
        startActivity(intent);
    }

    public void call(View v) {
        Intent intent = new Intent(this, CallJavaActivity.class);
        startActivity(intent);
    }
}
