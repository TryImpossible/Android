package com.barry.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends Activity implements View.OnClickListener {

    private Button btn_add, btn_del;
    private RecyclerView recyclerView;
    private List<String> datas;
    private MyAdatper myAdatper;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view);

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_del.setOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        recyclerView.addItemDecoration(new MyDecorationTwo(this));

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        datas = initData();
        recyclerView.setAdapter(new NormalAdapter(this, datas));
//        recyclerView.setAdapter(new QuickAdapter<String>(this, datas) {
//            @Override
//            public int getLayoutId(int viewType) {
//                return R.layout.item_1;
//            }
//
//            @Override
//            public void convert(VH holder, String data, int position) {
//                holder.setText(R.id.title, data);
//            }
//        });

//        myAdatper = new MyAdatper(this, datas);
//        recyclerView.setAdapter(myAdatper);

    }

    private List<String> initData() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("item - " + i);
        }
        return datas;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                myAdatper.addNewItem();
                linearLayoutManager.scrollToPosition(0);
                break;
            case R.id.btn_del:
                myAdatper.deleteItem();
                linearLayoutManager.scrollToPosition(0);
                break;
        }
    }

    class MyAdatper extends QuickAdapter<String> {


        public MyAdatper(Context context, List<String> mDatas) {
            super(context, mDatas);
        }

        @Override
        public int getLayoutId(int viewType) {
            switch (viewType){
                case 0:
                    return R.layout.item_1;
                case 1:
                    return R.layout.item_2;
            }
            return R.layout.item_1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position % 2 == 0) {
                return 0;
            } else {
                return 1;
            }
        }

        @Override
        public void convert(QuickAdapter.VH holder, String data, int position) {
            int type = getItemViewType(position);
            switch (type) {
                case 0:
                    holder.setText(R.id.title, data);
                    break;
                case 1:
                    holder.setText(R.id.title, data);
                    break;
            }
        }

        public void addNewItem() {
            if (mDatas == null) {
                mDatas = new ArrayList<>();
            }
            mDatas.add(0, "new Item");
            notifyItemInserted(0);
        }

        public void deleteItem() {
            if (mDatas == null || mDatas.isEmpty()) {
                return;
            }
            mDatas.remove(0);
            notifyItemRemoved(0);
        }
    }

}
