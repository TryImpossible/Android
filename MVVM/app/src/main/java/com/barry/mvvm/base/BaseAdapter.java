package com.barry.mvvm.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter <T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    public Context mContext;
    public List<T> mList;
    public LayoutInflater inflater;

    public BaseAdapter(Context context) {
        this.mContext = context;
        this.mList = new ArrayList<>();
        inflater = (LayoutInflater) mContext.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return onCreateVH(viewGroup, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int position) {
        onBindVH(vh, position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /***
     * 创建 ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    public abstract VH onCreateVH(ViewGroup parent, int viewType);

    /**
     * 绑定 ViewHolder
     * @param vh
     * @param position
     */
    public abstract void onBindVH(VH vh, int position);

    /**
     * 刷新数据
     * @param data 数据源
     */
    public void refreshData(List<T> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 加载更多
     * @param data 新的数据源
     */
    public void loadMoreData(List<T> data) {
        mList.addAll(data);
        notifyDataSetChanged();
    }
}
