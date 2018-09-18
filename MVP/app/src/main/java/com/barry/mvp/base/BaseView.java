package com.barry.mvp.base;

import android.content.Context;

public interface BaseView {

    /**
     * 显示正在加载的View
     */
    void showLoading();

    /**
     * 隐藏正在加载的View
     */
    void hideLoading();

    /**
     * 显示提示
     * @param msg
     */
    void showToast(String msg);

    /**
     * 显示请求错误提示
     */
    void showErr();

    /**
     * 获取上下文
     * @return 上下文
     */
    Context getContext();
}
