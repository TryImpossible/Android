package com.barry.performanceanalysis;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, MainFragment.newInstance())
                .commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTipDialog();
            }
        });

    }


    private void showTipDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tip");
        builder.setMessage(getResources().getString(R.string.app_name));
        builder.setNegativeButton("ok", null);
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainApplication.getRefWatcher().watch(this);
    }

}
