package com.bynn.integration.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.bynn.integration.R;
import com.bynn.integration.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class EBThridActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG = "EBThridActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ebthrid);

        Button btn1 = (Button) findViewById(R.id.btn_send1);
        btn1.setOnClickListener(this);
        Button btn2 = (Button) findViewById(R.id.btn_send2);
        btn2.setOnClickListener(this);
        Button btn3 = (Button) findViewById(R.id.btn_send3);
        btn3.setOnClickListener(this);
        Button btn4 = (Button) findViewById(R.id.btn_register);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_send1:
                EventBus.getDefault().postSticky(new MessageEvent("粘性事件1"));
                break;
            case R.id.btn_send2:
                EventBus.getDefault().postSticky(new MessageEvent("粘性事件2"));
                break;
            case R.id.btn_send3:
                EventBus.getDefault().postSticky(new MessageEvent("粘性事件3"));
                break;
            case R.id.btn_register:
                EventBus.getDefault().register(this);
                break;
            default:
                break;
        }
    }

    @Subscribe(sticky = true)
    public void onEvent(MessageEvent event) {
        Log.i(TAG, "接受到了来自EventBus的事件：" + event.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
