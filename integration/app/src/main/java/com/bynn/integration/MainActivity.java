package com.bynn.integration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bynn.integration.activity.BaseActivity;
import com.bynn.integration.activity.EBFirstActivity;
import com.bynn.integration.activity.PhotoViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv) TextView tv;
    @BindView(R.id.btn_photo_view) Button btn_photo_view;
    @BindView(R.id.btn_eventbus) Button btn_eventbus;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
//        tv.setText("buildType:" + Constants.ENVIRONMENT + "\nAPI:" + Constants.API_URL);
//        tv.setText("第一張圖片");
    }

    @OnClick({ R.id.btn_photo_view, R.id.btn_eventbus })
    public void jumpTo(View view) {
        switch (view.getId()) {
            case R.id.btn_photo_view:
                startActivity(new Intent(MainActivity.this, PhotoViewActivity.class));
                break;
            case R.id.btn_eventbus:
                startActivity(new Intent(MainActivity.this, EBFirstActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
