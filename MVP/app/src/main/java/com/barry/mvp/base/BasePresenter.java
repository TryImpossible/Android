package com.barry.mvp.base;

public class BasePresenter<V extends BaseView> {

    /**
     * 绑定的view
     */
    private V mvpView;

    /**
     * 绑定View，一般在初始化中调用该方法
     * @param mvpView
     */
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    /**
     * 断开View，一般在onDestroy中调用
     */
    public void detachView() {
        this.mvpView = null;
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     * @return
     */
    public boolean isViewAttached() {
        return this.mvpView != null;
    }

    /**
     * 获取连接的View
     * @return
     */
    public V getView() {
        return mvpView;
    }
}
