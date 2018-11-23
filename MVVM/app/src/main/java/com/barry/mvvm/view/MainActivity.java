package com.barry.mvvm.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.barry.mvvm.R;
import com.barry.mvvm.adapter.NewsAdapter;
import com.barry.mvvm.databinding.ActivityMainBinding;
import com.barry.mvvm.http.DialogHelper;
import com.barry.mvvm.util.ToastUtil;
import com.barry.mvvm.viewmodel.NewsVM;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import static com.barry.mvvm.constant.MainConstant.LoadData.FIRST_LOAD;

public class MainActivity extends AppCompatActivity implements INewsView, XRecyclerView.LoadingListener{

    private Context mContext;
    private ActivityMainBinding binding;
    private NewsAdapter newsAdapter;
    private NewsVM newsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initRecyclerView();
        newsVM = new NewsVM(this, newsAdapter);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        binding.newsRv.setRefreshProgressStyle(ProgressStyle.BallClipRotate); // 设置下拉刷新样式
        binding.newsRv.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); // 设置上拉加载更多样式
        binding.newsRv.setArrowImageView(R.mipmap.pull_down_arrow);
        binding.newsRv.setLoadingListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.newsRv.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(this);
        binding.newsRv.setAdapter(newsAdapter);
    }

    @Override
    public void onRefresh() {
        //下拉刷新
        newsVM.loadRefrshData();
    }

    @Override
    public void onLoadMore() {
        // 上拉加载更多
        newsVM.loadMoreData();
    }

    @Override
    public void loadStart(int loadType) {
        if (loadType == FIRST_LOAD) {
            DialogHelper.getInstance().show(mContext, "加载中...");
        }
    }

    @Override
    public void loadComplete() {
        DialogHelper.getInstance().close();
        binding.newsRv.loadMoreComplete(); // 结束加载
        binding.newsRv.refreshComplete(); // 结束刷新
    }

    @Override
    public void loadFailure(String message) {
        DialogHelper.getInstance().close();
        binding.newsRv.loadMoreComplete(); //结束加载
        binding.newsRv.refreshComplete(); //结束刷新
        ToastUtil.show(mContext, message);
    }
}
