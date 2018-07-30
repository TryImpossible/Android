package com.barry.fragment;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class ContentProviderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
    }

    public void insert(View v) {
        //通過內容提供者把數據插入01數據庫
        //1.獲取contentResolver
        ContentResolver resolver = getContentResolver();
        //2.訪問內容提供者，插入數據
        ContentValues values = new ContentValues();
        values.put("name", "流氓潤");
        values.put("phone", 138992);
        values.put("money", 14000);
        //arg0:指定內容提供者的主機名
        resolver.insert(Uri.parse("content://com.barry.people/person"), values);

        values.clear();
        values.put("name", "侃哥");
        values.put("phoen", 15999);
        resolver.insert(Uri.parse("content://com.barry.people/handsome"), values);
    }

    public void delete(View v) {
        ContentResolver resolver = getContentResolver();
        int i = resolver.delete(Uri.parse("content://com.barry.people/person"), "money = ?", new String[]{"100000"});
        System.out.println(i);
    }

    public void update(View v) {
        ContentResolver resolver = getContentResolver();
        ContentValues values = new ContentValues();
        values.put("name", "流氓潤潤氓流");
        values.put("phone", "138992299831");
        int i = resolver.update(Uri.parse("content://com.barry.people/person"), values, "name = ?", new String[]{"流氓潤"} );
        System.out.println(i);
    }

    public void query(View v) {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(Uri.parse("content://com.barry.people/person"), null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            int money = cursor.getInt(cursor.getColumnIndex("money"));
            System.out.println(name + ";" + phone + ";" + money);
        }
    }

    public void queryOne(View v) {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(Uri.parse("content://com.barry.people/person/2"), null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            int money = cursor.getInt(cursor.getColumnIndex("money"));
            System.out.println(name + ";" + phone + ";" + money);
        }
    }

}
