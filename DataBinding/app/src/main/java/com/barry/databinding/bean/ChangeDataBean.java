package com.barry.databinding.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.ObservableField;

import com.barry.databinding.BR;

public class ChangeDataBean extends BaseObservable{

    public final ObservableField<String> username = new ObservableField<>();

    private String content;

    public ChangeDataBean(String content) {
        this.content = content;
    }

    @Bindable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        notifyPropertyChanged(BR.content);
    }
}
