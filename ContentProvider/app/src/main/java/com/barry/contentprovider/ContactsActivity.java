package com.barry.contentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import static android.R.attr.id;

public class ContactsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
    }

    public void click1(View v) {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(Uri.parse("content://com.android.contacts/raw_contacts"), new String[]{"contact_id"}, null, null, null);
        while (cursor.moveToNext()) {
            String contactId = cursor.getString(0);
            //使用聯係人id作為where條件去查詢data表，查詢出屬於該聯系人的信息
            Cursor cursorData = resolver.query(Uri.parse("content://com.android.contacts/data"), new String[]{"data1", "mimetype"}, "raw_contact_id = ?",
                    new String[]{contactId}, null);
            Contact contact = new Contact();
            while(cursorData.moveToNext()) {
                String data1 = cursorData.getString(0);
                String mimetype = cursorData.getString(1);
                System.out.println(data1 + ";" + mimetype);
                if ("vnd.android.cursor.item/email_v2".equals(mimetype)) {
                    contact.setEmail(data1);
                } else if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
                    contact.setPhone(data1);
                } else if ("vnd.android.cursor.item/name".equals(mimetype)) {
                    contact.setName(data1);
                }
            }
            System.out.println(contact.toString());
        }
    }

    public void click2(View v) {
        ContentResolver resolver = getContentResolver();
        //先查詢最新的聯系人的主鍵，主鍵+1，就是要插入的聯係人id
        Cursor cursor = resolver.query(Uri.parse("content://com.android.contacts/raw_contacts"), new String[]{"_id"}, null, null, null);
        int _id = 0;
        if (cursor.moveToLast()) {
            _id = cursor.getInt(0);
        }
        _id++;

        //插入聯係人id
        ContentValues values = new ContentValues();
        values.put("contact_id", id);
        resolver.insert(Uri.parse("content://com.android.contacts/raw_contacts"), values);

        //把具體聯係人信息插入data表
        values.clear();
        values.put("data1", "剪刀手愛德華");
        values.put("mimetype", "vnd.android.cursor.item/name");
        values.put("raw_contact_id", _id);
        resolver.insert(Uri.parse("content://com.android.contacts/data"), values);

        values.clear();
        values.put("data1", "8899668");
        values.put("mimetype", "vnd.android.cursor.item/phone_v2");
        values.put("raw_contact_id", _id);
        resolver.insert(Uri.parse("content://com.android.contacts/data"), values);
    }
}
