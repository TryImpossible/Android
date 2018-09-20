package com.barry.databinding.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableField;

import com.barry.databinding.BR;

public class ItemBean extends BaseObservable {

    private String title;
    private String picUrl;
    public final ObservableField<String> content = new ObservableField<>();


    public ItemBean(String title, String picUrl) {
        this.title = title;
        this.picUrl = picUrl;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
