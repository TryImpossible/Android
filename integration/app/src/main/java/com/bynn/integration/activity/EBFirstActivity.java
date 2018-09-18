package com.bynn.integration.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bynn.integration.R;
import com.bynn.integration.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EBFirstActivity extends BaseActivity {

    private TextView tv_text;
    private Button btn_ebSecond;
    private Button btn_ebThrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ebfirst);
        tv_text = (TextView) findViewById(R.id.tv_text);
        btn_ebSecond = (Button) findViewById(R.id.btn_ebSecond);
        btn_ebSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EBFirstActivity.this, EBSecondActivity.class));
            }
        });
        btn_ebThrid = (Button) findViewById(R.id.btn_ebThrid);
        btn_ebThrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EBFirstActivity.this, EBThridActivity.class));
            }
        });
    }

    /**
     * 订阅方法，当接收到事件的时候，会调用譔方法
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        tv_text.setText(event.getMessage());
        Toast.makeText(EBFirstActivity.this, event.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
