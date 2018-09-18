package com.barry.mvp;

public interface Callback<T> {

    /**
     * 数据请求成功
     * @param data
     */
    void onSuccess(T data);

    /**
     * 使用网络API接口请求方式时，虽然已经请求成功但是由于{@code msg}的原因无法正常返回数据
     * @param msg
     */
    void onFailure(String msg);

    /**
     * 请求数据失败，指在请求网络API接口请求方式时，出现无法联网、缺少权限，内存泄露原因导致无法连接到请求数据源
     */
    void onError();

    /**
     * 当请求数据结束时，无论请求结果是成功、失败或是抛出异常都会执行此方法给用户做处理，通常做网络请求时可以在此隐藏"正在加载"的等待控件
     */
    void onComplete();
}
