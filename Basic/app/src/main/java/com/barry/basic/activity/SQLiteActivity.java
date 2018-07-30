package com.barry.basic.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.barry.basic.R;
import com.barry.basic.entity.Person;
import com.barry.basic.other.MyOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteActivity extends Activity {

    private List<Person> personList;
    private MyOpenHelper oh;
    private SQLiteDatabase db;

    private ListView lv;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        personList = new ArrayList<Person>();
        oh = new MyOpenHelper(this.getBaseContext());
        db = oh.getWritableDatabase();

        myAdapter = new MyAdapter();
        lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(myAdapter);
    }

    public void insert(View v) {
        this.insertApi();
//        db.execSQL("insert into person(name, phone, salary) values(?, ?, ?)", new Object[]{"張三", "10086", 10000});
//        db.execSQL("insert into person(name, phone, salary) values(?, ?, ?)", new Object[]{"李四", "10000", 9000});
    }

    public void insertApi() {
        ContentValues values = new ContentValues();
        values.put("name", "張三");
        values.put("phone", "10086");
        values.put("salary", 10000);
        long l = db.insert("person", null, values);
        System.out.println(l);
    }

    public void query(View v) {
        this.queryApi();
//        Cursor cursor =  db.rawQuery("select * from person", null);
//        while(cursor.moveToNext()) {
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            String phone = cursor.getString(cursor.getColumnIndex("phone"));
//            int salary = cursor.getInt(cursor.getColumnIndex("salary"));
//            System.out.println(name + ";" + phone + ";" + salary);
//        }
//        cursor.close();
    }

    public void queryApi() {
        Cursor cursor = db.query("person", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            int salary = cursor.getInt(3);
            System.out.println(name + ";" + phone + ";" + salary);

            personList.add(new Person(name, phone, salary));
        }
        cursor.close();
        myAdapter.notifyDataSetChanged();


//        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
//        ll.setOrientation(LinearLayout.VERTICAL);
//        for (Person p : personList) {
//            TextView tv = new TextView(this);
//            tv.setText(p.toString());
//            tv.setTextSize(15);
//            ll.addView(tv);
//        }
    }

    public void update(View v) {
        this.updateApi();
//        db.execSQL("update person set salary=? where name=?", new Object[]{ 12000, "張三"});
    }

    public void updateApi() {
        ContentValues values = new ContentValues();
        values.put("name", "張三三");
        int i = db.update("person", values, "name=?", new String[]{"張三"});
        System.out.println(i);
    }

    public void delete(View v) {
        this.deleteApi();
//        db.execSQL("delete from person;");
//        db.execSQL("delete from person where name=?", new Object[]{"李四"});
    }

    public void deleteApi() {
        int i = db.delete("person", null, null);
        System.out.print(i);
    }

    public void transaction(View v) {
        try {
            db.beginTransaction();
            ContentValues values = new ContentValues();
            values.put("salary", 13999);
            db.update("person", values, "name=?", new String[]{"張三"});

            values.clear();
            values.put("salary", 15000);
            db.update("person", values, "name=?", new String[]{"張三"});
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return personList != null ? personList.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Person p = personList.get(position);

//            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//			LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

//			View view = inflater.inflate(R.layout.item_listview, null);

            View view = null;
            if (convertView == null) {
                view = View.inflate(SQLiteActivity.this, R.layout.item_listview, null);
            } else {
                view = convertView;
            }
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_name.setText(p.getName());

            TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
            tv_phone.setText(p.getPhone());

            TextView tv_salary = (TextView) view.findViewById(R.id.tv_salary);
            tv_salary.setText(p.getSalary() + "");

            return view;
        }
    }
}
