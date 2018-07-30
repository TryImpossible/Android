package com.barry.basic.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.barry.basic.R;

public class SharepreferenceActivity extends Activity {

    private EditText et_account;
    private EditText et_password;
    private Button btn_login;
    private CheckBox cb_remeberPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharepreference);

        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new loginClickListener());
        cb_remeberPassword = (CheckBox) findViewById(R.id.cb_remeberPassword);

        readAccount();
    }

    public void readAccount() {
        SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
        String name = sp.getString("name", "");
        String password = sp.getString("password", "");
        et_account.setText(name);
        et_password.setText(password);
    }

    class loginClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String name = et_account.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            if (!name.equals(password)) {
                Toast.makeText(SharepreferenceActivity.this, "账号或密码输入错误！", Toast.LENGTH_SHORT).show();
                return;
            }
            if (cb_remeberPassword.isChecked()) {
                SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
                Editor ed = sp.edit();
                ed.putString("name", name);
                ed.putString("password", password);
                ed.commit();
            }

            Toast.makeText(SharepreferenceActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
            finish();

        }
    }
}
