package com.barry.contentprovider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v) {
        
    }

    public void click2(View v) {
        Intent intent = new Intent(this, SystemSmsActivity.class);
        startActivity(intent);
    }

    public void click3(View v) {
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    public void click4(View v) {
        Intent intent = new Intent(this, ContentObserverActivity.class);
        startActivity(intent);
    }

}
