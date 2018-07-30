package com.barry.basic.other;

import android.database.sqlite.SQLiteDatabase;
import android.test.mock.MockContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by bynn on 2018/1/19.
 */
public class MyOpenHelperTest {

    private MyOpenHelper oh;
    private SQLiteDatabase db;

    @Before
    public void setUp() throws Exception {
        oh = new MyOpenHelper(new MockContext());
        db = oh.getWritableDatabase();
        assertNotNull(db);
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void testSomething() {
    }

    @Test
    public void insert() throws Exception {
        db.execSQL("insert into person(name, phone, salary) values (?, ?, ?)", new Object[]{"張三", "10086", 10000});
//        db.execSQL("insert into person(name, phone, salary) values (?, ?, ?)", new Object[]{"李四", "10000", 9000});

    }

}