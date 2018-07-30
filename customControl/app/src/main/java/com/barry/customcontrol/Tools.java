package com.barry.customcontrol;

import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

/**
 * Created by bynn on 2018/2/13.
 */
public class Tools {

    public static void hideView(ViewGroup view) {
        hideView(view, 0);
    }

    public static void showView(ViewGroup view) {
        showView(view, 0);
    }

    //隱藏動畫
    public static void hideView(ViewGroup view, long delay) {
        RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f);
        animation.setDuration(500);
        animation.setFillAfter(true); //保持動畫後的狀態
        animation.setStartOffset(delay); //延遲多長時間後才運行動畫
        view.startAnimation(animation);

        //禁用所有孩子的點擊事件
        int childCount = view.getChildCount();
        for (int i = 0; i < childCount; i++) {
            view.getChildAt(i).setEnabled(false); //禁用點擊事件
        }
    }

    //顯示動畫
    public static void showView(ViewGroup view, long delay) {
        RotateAnimation animation = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setStartOffset(delay);
        view.startAnimation(animation);

        int childCount = view.getChildCount();
        for (int i = 0; i < childCount; i++) {
            view.getChildAt(i).setEnabled(true); //開啟點擊事件
        }
    }
}
