package com.barry.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.barry.databinding.bean.UserBean;
import com.barry.databinding.databinding.ActivityFirstAttemptBinding;

public class FirstAttemptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_attempt);

        ActivityFirstAttemptBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_first_attempt);
        UserBean userBean = new UserBean("张三", 30);
        binding.setUser(userBean);
    }
}
