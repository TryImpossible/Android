package com.barry.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class TweenAnimationActivity extends Activity {

    private ImageView iv;
    private TranslateAnimation ta;
    private ScaleAnimation sa;
    private AlphaAnimation aa;
    private RotateAnimation ra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_animation);

        iv = (ImageView) findViewById(R.id.iv);
    }

    public void translate(View v) {
        //定義位移補間動畫
//        ta = new TranslateAnimation(-100, 100, -60, 60);
        ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -1.5f, Animation.RELATIVE_TO_SELF, 1.5f, Animation.RELATIVE_TO_SELF, -2, Animation.RELATIVE_TO_SELF, 2);
        //定義動畫持續時間
        ta.setDuration(2000);
        //設置重複次數
        ta.setRepeatCount(1);
        //設置重複模式
        ta.setRepeatMode(Animation.REVERSE);
        //在結束位置上填充動畫
        ta.setFillAfter(true);
        //播放動畫
        iv.startAnimation(ta);
    }

    public void scale(View v) {
        //定義縮放補間動畫
//        sa = new ScaleAnimation(0.2f, 2, 0.2f, 2);
//        sa = new ScaleAnimation(0.2f, 2, 0.2f, 2, iv.getWidth(), iv.getHeight());
        sa = new ScaleAnimation(0.3f, 2, 0.2f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //定義動畫持續時間
        sa.setDuration(2000);
        //設置重複次數
        sa.setRepeatCount(1);
        //設置重複模式
        sa.setRepeatMode(Animation.REVERSE);
        //在結束位置上填充動畫
        sa.setFillAfter(true);
        //播放動畫
        iv.startAnimation(sa);
    }

    public void alpha(View v) {
        //定義透明補間動畫
        aa = new AlphaAnimation(1, 0.2f);
        //定義動畫持續時間
        aa.setDuration(2000);
        //設置重複次數
        aa.setRepeatCount(1);
        //設置重複模式
        aa.setRepeatMode(Animation.REVERSE);
        //在結束位置填充動畫
        aa.setFillAfter(true);
        //播放動畫
        iv.startAnimation(aa);
    }

    public void rotate(View v) {
        //定義旋轉補間動畫
        ra = new RotateAnimation(0, -720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //定義動畫持續時長
        ra.setDuration(2000);
        //設置重複次數
        ra.setRepeatCount(1);
        //設置重複模式
        ra.setRepeatMode(Animation.REVERSE);
        //在結束位置填充動畫
        ra.setFillAfter(true);
        //播放動畫
        iv.startAnimation(ra);
    }

    public void fly(View v) {
        //創建動畫集合
        AnimationSet set = new AnimationSet(false);
        //把動畫添加至集合
        set.addAnimation(ta);
        set.addAnimation(sa);
        set.addAnimation(aa);
        set.addAnimation(ra);

        //開始播放集合
        iv.startAnimation(set);
    }

}
