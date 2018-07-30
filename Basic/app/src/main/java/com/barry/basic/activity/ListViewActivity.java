package com.barry.basic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.barry.basic.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListViewActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

//        String[] objects = new String[] {"a", "b", "c"};
//
//        listView = (ListView) findViewById(R.id.lv);
//        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.item_listview2, R.id.tv_name, objects));

        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", "小狗一");
        map1.put("photo", R.mipmap.photo1);
        data.add(map1);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", "小狗二");
        map2.put("photo", R.mipmap.photo2);
        data.add(map2);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("name", "小狗三");
        map3.put("photo", R.mipmap.photo3);
        data.add(map3);

        listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(new SimpleAdapter(this, data, R.layout.item_listview2, new String[]{"name", "photo"}, new int[]{R.id.tv, R.id.iv}));
    }
}
