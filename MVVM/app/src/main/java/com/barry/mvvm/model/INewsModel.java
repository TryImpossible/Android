package com.barry.mvvm.model;

import com.barry.mvvm.base.BaseLoadListener;
import com.barry.mvvm.bean.SimpleNewsBean;

public interface INewsModel {

    /**
     * 获取新闻数据
     * @param page
     * @param loadListener
     */
    void loadNewsData(int page, BaseLoadListener<SimpleNewsBean> loadListener);
}
