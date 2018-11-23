package com.barry.mvvm.viewmodel;

import com.barry.mvvm.adapter.NewsAdapter;
import com.barry.mvvm.base.BaseLoadListener;
import com.barry.mvvm.bean.SimpleNewsBean;
import com.barry.mvvm.constant.MainConstant;
import com.barry.mvvm.model.INewsModel;
import com.barry.mvvm.model.NewsModelImpl;
import com.barry.mvvm.view.INewsView;

import java.util.List;

public class NewsVM implements BaseLoadListener<SimpleNewsBean> {

    private static final String TAG = "NewsVM";
    private INewsModel mNewsModel;
    private INewsView mNewsView;
    private NewsAdapter mAdapter;
    private int currentPage = 1; // 当前页数
    private int loadType; // 加载数据的类型

    public NewsVM(INewsView mNewsView, NewsAdapter mAdapter) {
        this.mNewsView = mNewsView;
        this.mAdapter = mAdapter;
        mNewsModel = new NewsModelImpl();
        getNewsData();
    }

    /**
     * 第一次获取新闻数据
     */
    private void getNewsData() {
        loadType = MainConstant.LoadData.FIRST_LOAD;
        mNewsModel.loadNewsData(currentPage, this);
    }

    /**
     * 获取下拉刷新的数据
     */
    public void loadRefrshData() {
        loadType = MainConstant.LoadData.REFRESH;
        currentPage = 1;
        mNewsModel.loadNewsData(currentPage, this);
    }

    /**
     * 获取上拉加载更多的数据
     */
    public void loadMoreData() {
        loadType = MainConstant.LoadData.LOAD_MORE;
        currentPage++;
        mNewsModel.loadNewsData(currentPage, this);
    }

    @Override
    public void loadSuccess(List<SimpleNewsBean> list) {
        if (currentPage > 1) {
            //上拉加载的数据
            mAdapter.loadMoreData(list);
        } else {
            //第一次加载或者下拉刷新的数据
            mAdapter.refreshData(list);
        }
    }

    @Override
    public void loadFailure(String message) {
        //加载失败后的提示
        if (currentPage > 1) {
            currentPage--;
        }
        mNewsView.loadFailure(message);
    }

    @Override
    public void loadStart() {
        mNewsView.loadStart(loadType);
    }

    @Override
    public void loadComplete() {
        mNewsView.loadComplete();
    }
}
