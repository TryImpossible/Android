package com.bynn.integration.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bynn.integration.R;
import com.bynn.integration.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;

public class EBSecondActivity extends BaseActivity{

    private Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ebsecond);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MessageEvent("Hello From EventBus!"));
            }
        });
    }
}
