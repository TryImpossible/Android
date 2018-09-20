package com.barry.databinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.view.View;

import com.barry.databinding.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        final List<ItemBean> lists = initData();
        // 设置adapter
        recyclerView.setAdapter(new NormalAdapter(this, lists));

        // 设置分隔线
//        recyclerView.addItemDecoration();

        // 设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        recyclerView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
////                lists.get(0).setTitle("啦拉啦");
//                lists.get(0).content.set("显示内容");
//                return false;
//            }
//        });
    }

    private List<ItemBean> initData() {
        List<ItemBean> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(new ItemBean("ItemBean --- " + i, "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4138850978,2612460506&fm=200&gp=0.jpg"));
        }
        return data;
    }
}
