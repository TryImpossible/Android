package com.barry.customcontrol;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class DropdownBoxActivity extends Activity {

    private ListView lvList;
    private EditText etContent;
    private ArrayList<String> mList;
    private PopupWindow mPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dropdown_box);

        ImageView ivDrop = (ImageView) findViewById(R.id.iv_drop);
        etContent = (EditText) findViewById(R.id.et_content);
        ivDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDropDown();
            }
        });

        lvList = new ListView(this);

        mList = new ArrayList<String>();
        for (int i = 0; i < 200; i++) {
            mList.add("abc" + i);
        }
        lvList.setAdapter(new MyAdapter());
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                etContent.setText(mList.get(position));
                mPopup.dismiss();
            }
        });
    }

    private void showDropDown() {
        if (mPopup == null) {
            mPopup = new PopupWindow(lvList, etContent.getWidth(), 200, true);
            mPopup.setBackgroundDrawable(new ColorDrawable());
        }
        mPopup.showAsDropDown(etContent);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList != null ? mList.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(DropdownBoxActivity.this, R.layout.list_item, null);
                holder = new ViewHolder();
                holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
                holder.ivDelete = (ImageView) convertView.findViewById(R.id.iv_delete);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvContent.setText(mList.get(position));
            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.remove(position);
                    MyAdapter.this.notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }

    static class ViewHolder {
        public TextView tvContent;
        public ImageView ivDelete;
    }
}
