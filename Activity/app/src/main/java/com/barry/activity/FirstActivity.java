package com.barry.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class FirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    //隱式跳轉至打電話Activity
    public void click1(View v) {
        //创建意图，这个是隐式意图
        Intent intent = new Intent();
        //设置动作
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:110"));
        //启动Activity
        startActivity(intent);
    }

    //顯示啟動SecondActivity
    public void click2(View v) {
        Intent intent = new Intent();
        intent.setClass(this, SecondActivity.class);
//        intent.putExtra("name", "張三");
//        intent.putExtra("age", 20);
        Bundle bundle = new Bundle();
        bundle.putString("name", "張三");
        bundle.putInt("age", 20);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //顯示啟動撥號器
    public void click3(View v) {
        Intent intent = new Intent();
        intent.setClassName("com.android.dialer", "com.android.dialer.DialtactsActivity");
        intent.setData(Uri.parse("tel:11670"));
        startActivity(intent);
    }

    //隱式啟動撥號順器
    public void click4(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:76115230"));
        startActivity(intent);
    }

    //隱式啟動SecondActivity
    public void click5(View v) {
        Intent intent = new Intent();
        intent.setAction("a.b.c2");
//        intent.setType("text/name");
//        intent.setData(Uri.parse("heima:春眠不覺曉"));
        intent.setDataAndType(Uri.parse("heima:春眠不沈曉"), "text/name");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);
    }

    //顯示啟動瀏覽器
    public void click6(View v) {
        Intent intent = new Intent();
        intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        startActivity(intent);
    }

    //隱式啟動瀏覽器
    public void click7(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http:www.baidu.com"));
        startActivity(intent);
    }

    //跳轉至第三方應用
    public void click8(View v) {
        Intent intent = new Intent();
        intent.setClassName("com.barry.basic", "com.barry.basic.activity.RemoteImageActivity");
        startActivity(intent);
    }

    //处理后退键的情况
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){

            this.finish();	//finish当前activity
            overridePendingTransition(R.anim.back_left_in,
                    R.anim.back_right_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


//    protected void onPause() {
//        // TODO Auto-generated method stub
//        super.onPause();
//        overridePendingTransition(R.anim.back_left_in, R.anim.back_right_out);
//    }
}
