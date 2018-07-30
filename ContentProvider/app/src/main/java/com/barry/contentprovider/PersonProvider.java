package com.barry.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonProvider extends ContentProvider {
    public PersonProvider() {
    }

    private SQLiteDatabase db;
    //創建uri匹配
    UriMatcher um = new UriMatcher(UriMatcher.NO_MATCH);
    {
        um.addURI("com.barry.people", "person", 1); //content://com.barry.people/person
        um.addURI("com.barry.people", "handsome", 2); //content://com.barry.people/handsome
        um.addURI("com.barry.people", "person/#", 3); //content://com.barry.people/person/10
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        MyOpenHelper oh = new MyOpenHelper(getContext());
        db = oh.getWritableDatabase();
        return false;
    }

    //返回通過指定uri獲取的數據的mimetype
    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
//        throw new UnsupportedOperationException("Not yet implemented");
        if (um.match(uri) == 1) {
            return "vnd.android.cursor.dir/person";
        } else if (um.match(uri) == 2) {
            return "vnd.android.cursor.dir/handsome";
        } else if (um.match(uri) == 3) {
            return "vnd.android.cursor.item/person";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
//        throw new UnsupportedOperationException("Not yet implemented");
        if (um.match(uri) == 1) {
            db.insert("person", null, values);

            //數據庫改變了，內容提供者發出通知
            //arg0:通知發到哪個uri上，註冊在這個uri上的內容觀察者都可以收到通知
            getContext().getContentResolver().notifyChange(uri, null);
        } else if (um.match(uri) == 2) {
            db.insert("handsome", null, values);

            getContext().getContentResolver().notifyChange(uri, null);
        } else {
            throw new IllegalArgumentException("uri傳錯啦");
        }
        return uri;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
//        throw new UnsupportedOperationException("Not yet implemented");
        Cursor cursor = null;
        if (um.match(uri) == 1) {
            cursor = db.query("person", projection, selection, selectionArgs, null, null, sortOrder, null);
        } else if (um.match(uri) == 2) {
            cursor = db.query("handsome", projection, selection, selectionArgs, null, null, sortOrder, null);
        } else if (um.match(uri) == 3) {
            //取出uri末尾攜帶的數字
            long id = ContentUris.parseId(uri);
            cursor = db.query("person", projection, "_id=?", new String[]{"" + id}, null, null, sortOrder, null);
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
//        throw new UnsupportedOperationException("Not yet implemented");
        int i = db.update("person", values, selection, selectionArgs);
        return i;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
//        throw new UnsupportedOperationException("Not yet implemented");
        int i = 0;
        if (um.match(uri) == 1) {
            i = db.delete("person", selection, selectionArgs);
        } else if (um.match(uri) == 2) {
            i = db.delete("handsome", selection, selectionArgs);
        } else {
            throw new IllegalArgumentException("uri又傳錯啦");
        }
        return i;
    }
}
