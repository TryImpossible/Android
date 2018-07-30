package com.barry.basic.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.barry.basic.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class InternalStroageActivity extends Activity implements View.OnClickListener {
    public final static String TAG = "InternalStroageActivity";
    private EditText et_account;
    private EditText et_password;
    private Button btn_login;
    private CheckBox cb_remeberPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_stroage);

        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        cb_remeberPassword = (CheckBox) findViewById(R.id.cb_remeberPassword);

        readAccount();
    }

    public void readAccount() {
        File file = new File(getCacheDir(), "info.txt");
        Log.e(this.TAG, file.toString());
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String  text = br.readLine();
                String s[] = text.split("&");
                et_account.setText(s[0]);
                et_password.setText(s[1]);
                fis.close();
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        String name = et_account.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if (!name.equals(password)) {
            Toast.makeText(InternalStroageActivity.this, "账号或密码输入错误！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (cb_remeberPassword.isChecked()) {
            File file = new File(getCacheDir(), "info.txt");
            Log.e(InternalStroageActivity.TAG, file.toString());
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write((name + "&" + password).getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Toast.makeText(InternalStroageActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
        finish();

    }

}
