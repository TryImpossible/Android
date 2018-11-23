package com.barry.mvvm.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.barry.mvvm.BR;
import com.barry.mvvm.R;
import com.barry.mvvm.base.BaseAdapter;
import com.barry.mvvm.base.BaseViewHolder;
import com.barry.mvvm.bean.SimpleNewsBean;
import com.barry.mvvm.util.ToastUtil;

public class NewsAdapter extends BaseAdapter<SimpleNewsBean, BaseViewHolder> {

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_news, parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        ViewDataBinding binding = baseViewHolder.getBinding();
        binding.setVariable(BR.simpleNewsBean, mList.get(position));
        binding.setVariable(BR.position, position);
        binding.setVariable(BR.adapter, this);
        binding.executePendingBindings(); //防止闪烁
    }

    public void clickDianzan(SimpleNewsBean simpleNewsBean, int position) {
        if (simpleNewsBean.isGood.get()) {
            simpleNewsBean.isGood.set(false);
            ToastUtil.show(mContext, "取消点赞 position=" + position);
        } else {
            simpleNewsBean.isGood.set(true);
            ToastUtil.show(mContext, "点赞成功 position=" + position);
        }
    }

}
