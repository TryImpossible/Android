package com.barry.fragment.oldVersion;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.barry.fragment.R;

public class FragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        showFragment01();
    }

    private void showFragment01() {
        //1.創建fragment對象
        Fragment01 fragment01 = new Fragment01();
        //2.獲取fragment管理器
        FragmentManager fm = getFragmentManager();
        //3.開啟事務
        FragmentTransaction ft = fm.beginTransaction();
        //4.顯示fragment
        //arg0:設置把fragment顯示在哪個容器中
        ft.replace(R.id.fl, fragment01);
        //5.提交
        ft.commit();
    }

    public void click1(View v) {
        showFragment01();
    }

    public void click2(View v) {
        //1.创建fragment对象
        Fragment02 fragment02 = new Fragment02();
        //2.获取fragment管理器
        FragmentManager fm = getFragmentManager();
        //3.开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //4.显示fragment
        //arg0:设置把fragment显示在哪个容器中
        ft.replace(R.id.fl, fragment02);
        //5.提交
        ft.commit();
    }

    public void click3(View v) {
        //1.创建fragment对象
        Fragment03 fragment03 = new Fragment03();
        //2.获取fragment管理器
        FragmentManager fm = getFragmentManager();
        //3.开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //4.显示fragment
        //arg0:设置把fragment显示在哪个容器中
        ft.replace(R.id.fl, fragment03);
        //5.提交
        ft.commit();
    }
}
