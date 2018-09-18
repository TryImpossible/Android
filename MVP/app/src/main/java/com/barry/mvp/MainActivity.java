package com.barry.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.barry.mvp.base.BaseActivity;

public class MainActivity extends BaseActivity implements MVPView {

    private TextView text;
    private MVPPresenter presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);

        //初始化Presenter
        presenter = new MVPPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    /**
     * button 点击事件调用方法
     * @param view
     */
    public void getData(View view) {
        presenter.getData("normal");
    }

    /**
     * button 点击事件调用方法
     * @param view
     */
    public void getDataForFailure(View view) {
        presenter.getData("failure");
    }

    /**
     * button 点击事件调用方法
     * @param view
     */
    public void getDataForError(View view) {
        presenter.getData("error");
    }

    @Override
    public void showData(String data) {
        text.setText(data);
    }

}
