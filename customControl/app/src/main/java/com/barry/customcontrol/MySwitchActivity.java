package com.barry.customcontrol;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MySwitchActivity extends Activity {

    private MySwitch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_switch);

        mSwitch = (MySwitch) findViewById(R.id.ms_switch);
        mSwitch.setOnCheckChangeListener(new MySwitch.OnCheckChangeListener() {
            @Override
            public void onCheckChanged(View view, boolean isChecked) {
//                Toast.makeText(getApplicationContext(), "当前状态:" + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
