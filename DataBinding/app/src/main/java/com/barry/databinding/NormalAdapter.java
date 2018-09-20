package com.barry.databinding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.barry.databinding.bean.ItemBean;
import com.barry.databinding.databinding.ItemRecyclerViewBinding;

import java.util.List;

public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH> {

    public static class VH extends RecyclerView.ViewHolder {

        private ItemRecyclerViewBinding binding;

        public ItemRecyclerViewBinding getBinding() {
            return binding;
        }

        public VH(ItemRecyclerViewBinding biniding) {
            super(biniding.getRoot());
            this.binding = biniding;
        }
    }

    private Context context;
    private List<ItemBean> mDatas;
    public LayoutInflater inflate;

    public NormalAdapter(Context context, List<ItemBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemRecyclerViewBinding itemRecyclerViewBinding = DataBindingUtil.inflate(inflate, R.layout.item_recycler_view, parent, false);
        return new VH(itemRecyclerViewBinding);
    }

    @Override
    public void onBindViewHolder(final VH holder, final int position) {

        holder.getBinding().setItem(mDatas.get(position));
        holder.getBinding().tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "你点击了第" + position + "项", Toast.LENGTH_SHORT).show();
            }
        });
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
