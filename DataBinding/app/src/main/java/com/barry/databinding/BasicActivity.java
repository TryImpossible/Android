package com.barry.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.barry.databinding.bean.UserBean;
import com.barry.databinding.databinding.ActivityBasicBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BasicActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityBasicBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_basic);
        binding.setClickListener(this);
        binding.setHandler(new OnClickHandler());

        UserBean userBean = new UserBean("张三", 24);
        binding.setUser(userBean);

        UserBean userBean2 = new UserBean("李四", 30);
        binding.setUser2(userBean2);

        binding.setStr("我是字符串");
        binding.setAge(20);

        List<String> list = new ArrayList<>();
        list.add("list1");
        list.add("list2");
        binding.setList(list);

        HashMap<String, Object> map = new HashMap<>();
        map.put("key0", "map_value0");
        map.put("key1", "map_value1");
        binding.setMap(map);

        String[] arrays = {"字符串1", "字符串2"};
        binding.setArray(arrays);


    }

    @Override
    public void onClick(View v) {
        Log.e("TAG", "点击了");
        switch (v.getId()) {
            case R.id.click_btn:
                Toast.makeText(this, "点击了1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.click2_btn:
                Toast.makeText(this, "点击了2", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
