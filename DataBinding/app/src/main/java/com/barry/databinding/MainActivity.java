package com.barry.databinding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jump(View v) {
        switch (v.getId()) {
            case R.id.btn_first_attempt:
                startActivity(new Intent(this, FirstAttemptActivity.class));
                break;
            case R.id.btn_basic:
                startActivity(new Intent(this, BasicActivity.class));
                break;
            case R.id.btn_recycler_view:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case R.id.btn_change_data:
                startActivity(new Intent(this, changeDataActivity.class));
                break;

        }
    }
}
