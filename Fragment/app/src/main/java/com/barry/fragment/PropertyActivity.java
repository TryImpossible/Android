package com.barry.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class PropertyActivity extends Activity {

    private ImageView iv;
    private ObjectAnimator oa1;
    private ObjectAnimator oa2;
    private ObjectAnimator oa3;
    private ObjectAnimator oa4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        iv = (ImageView) findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PropertyActivity.this, "點我上miss", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void translate(View v) {
//        TranslateAnimation ta = new TranslateAnimation(-100, 100, 0, 0);
//        ta.setDuration(2000);
//        ta.setFillAfter(true);
//        iv.startAnimation(ta);

        //創建屬性動畫
        //arg0: 要操作的對象
        //arg1: 要修改的屬性的名字

        oa1 = ObjectAnimator.ofFloat(iv, "translationX", 0, 70, 30, 100);
        oa1.setDuration(2000);
        oa1.setRepeatCount(1);
        oa1.setRepeatMode(ValueAnimator.REVERSE);
        oa1.start();
    }

    public void scale(View v) {
        oa2 = ObjectAnimator.ofFloat(iv, "scaleX", 0.2f, 2, 1, 2.5f);
        oa2.setDuration(2000);
        oa2.setRepeatCount(1);
        oa2.setRepeatMode(ObjectAnimator.REVERSE);
        oa2.start();
    }

    public void alpha(View v) {
        oa3 = ObjectAnimator.ofFloat(iv, "alpha", 0.2f, 1);
        oa3.setDuration(2000);
        oa3.setRepeatCount(1);
        oa3.setRepeatMode(ValueAnimator.REVERSE);
        oa3.start();
    }

    public void rotate(View v) {
        oa4 = ObjectAnimator.ofFloat(iv, "rotation", 0, 360, 180, 270);
        oa4.setDuration(2000);
        oa4.setRepeatCount(1);
        oa4.setRepeatMode(ValueAnimator.REVERSE);
        oa4.start();
    }

    public void fly(View v) {
        //創建動畫集合
        AnimatorSet set = new AnimatorSet();
        //排隊飛
//        set.playSequentially(oa1, oa2, oa3, oa4);
        //一起飛
        set.playTogether(oa1, oa2, oa3, oa4);
        set.setTarget(iv);
        set.start();
    }

    public void xml(View v) {
        //使用動畫填充器把xml資源文件填充成屬性動畫對象
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.property_animation);
        animator.setTarget(iv);
        animator.start();
    }
}
