package com.barry.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_jump = (Button) findViewById(R.id.btn_jump);
        btn_jump.setOnClickListener(this);

        Button btn_liefcycle = (Button) findViewById(R.id.btn_liefcycle);
        btn_liefcycle.setOnClickListener(this);

        Button btn_launcherMode = (Button) findViewById(R.id.btn_launcherMode);
        btn_launcherMode.setOnClickListener(this);

        Button btn_callback = (Button) findViewById(R.id.btn_callback);
        btn_callback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jump:
                startActivity(FirstActivity.class);
                break;
            case R.id.btn_liefcycle:
                startActivity(LiefcyleActivity.class);
                break;
            case R.id.btn_launcherMode:
                startActivity(LauncherMode1Activity.class);
                break;
            case R.id.btn_callback:
                startActivity(CallbackDataActivity.class);
                break;
        }
    }

    public void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
