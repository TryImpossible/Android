package com.barry.basic.other;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bynn on 2018/1/19.
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "people.db";

    public static final int DATABASE_VERSION = 1000;

    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person(_id integer primary key autoincrement, name char(10), phone char(20), salary integer(10))");
        final int FIRST_DATABASE_VERSION = 1000;
        onUpgrade(db, FIRST_DATABASE_VERSION, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("更新了");
        // 使用for实现跨版本升级数据库
        for (int i = oldVersion; i < newVersion; i++) {
            switch (i) {
                case 1000:
                    upgradeToVersion1001(db);
                    break;
                default:
                    break;
            }
        }
    }

    private void upgradeToVersion1001(SQLiteDatabase db){
        // favorite表新增1个字段
        String sql1 = "ALTER TABLE person ADD COLUMN age integer(3)";
        db.execSQL(sql1);
    }
}
