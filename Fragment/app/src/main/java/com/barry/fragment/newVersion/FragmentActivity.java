package com.barry.fragment.newVersion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.barry.fragment.R;

public class FragmentActivity extends android.support.v4.app.FragmentActivity implements MyListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment2);
        defaultFragment();
    }

    private void defaultFragment() {
        Fragment fragment11 = new Fragment11();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl, fragment11);
        ft.commit();
    }

    public void redPage(View v) {
        defaultFragment();
    }

    public void greenPage(View v) {
        Fragment22 fragment22 = new Fragment22();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl, fragment22);
        ft.commit();
    }

    public void bluePage(View v) {
        Fragment33 fragment33 = new Fragment33();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl, fragment33);
        ft.commit();
    }

    public void click(View v) {
        EditText et = (EditText) findViewById(R.id.et_main);

        Fragment fragment11 = new Fragment11();

        Bundle bundle = new Bundle();
        bundle.putString("str", et.getText().toString());
        fragment11.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fl, fragment11).commit();
    }

    public void setText(String str) {
        TextView tv = (TextView) findViewById(R.id.tv_main);
        tv.setText(str);
    }

    @Override
    public void callbak(String str) {
        System.out.println(str);
    }
}
