package com.barry.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.barry.databinding.bean.ChangeDataBean;
import com.barry.databinding.databinding.ActivityChangeDataBinding;

public class changeDataActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityChangeDataBinding binding;

    private ChangeDataBean bean;
    private boolean flag;

    private ObservableArrayList<String> list = new ObservableArrayList<>();
    private ObservableArrayMap<String, Object> map = new ObservableArrayMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_data);

        bean = new ChangeDataBean("我是原始内容");
        bean.username.set("张三");
        binding.setChangeDataBean(bean);

        list.add("list1");
        list.add("list2");
        binding.setList(list);

        map.put("key0", "key0_value0");
        map.put("key1", "key1_value1");
        binding.setMap(map);

        binding.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_content_btn:
                flag = !flag;
                if (flag) {
                    bean.setContent("我是更新后的内容");
                    bean.username.set("李四");
                } else {
                    bean.setContent("我是原始内容");
                    bean.username.set("张三");
                }
                break;
            case R.id.change_content_btn1:
                list.set(0, "after change list");
                break;
            case R.id.change_content_btn2:
                map.put("key0", "after change key0_value0");
                break;
            case R.id.btn_click:
                binding.btnClick.setText("你真调皮阿！");
                break;
        }
    }
}
