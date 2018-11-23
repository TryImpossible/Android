package com.barry.mvvm.retrofitinterface;

import com.barry.mvvm.bean.NewsBean;
import com.barry.mvvm.constant.URLConstant;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetrofitInterface {

    //获取“分类中搜索商品”的数据
    @GET(URLConstant.URL_PATH)
    Observable<NewsBean> getNewsData();
}
