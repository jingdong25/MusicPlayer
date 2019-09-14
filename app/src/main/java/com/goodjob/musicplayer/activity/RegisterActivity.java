package com.goodjob.musicplayer.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.goodjob.musicplayer.R;
import com.goodjob.musicplayer.sql.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText user_account_register,user_password_register,user_initdate_register,user_email_register;
    private Button user_register;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase writableDatabase;
    private RadioButton radio_men;
    private RadioButton radio_women;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);
        initView();
        initLinsenter();
       initData();

    }

    private void initData() {
        databaseHelper = new DatabaseHelper(this);
        writableDatabase = databaseHelper.getWritableDatabase();
    }

    private void initLinsenter() {
        user_register.setOnClickListener(this);
    }

    private void initView() {
        user_account_register = (EditText) findViewById(R.id.user_account_register);
        user_password_register = (EditText) findViewById(R.id.user_password_register);
        user_email_register = (EditText) findViewById(R.id.user_email_register);
        user_initdate_register = (EditText) findViewById(R.id.user_initdate_register);
        radio_men = (RadioButton) findViewById(R.id.radio_men);
        radio_women = (RadioButton) findViewById(R.id.radio_women);
        user_register = (Button) findViewById(R.id.user_register);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_register:
                //获取输入框 上的所有内容
                String p = user_account_register.getText().toString();
                String s = user_password_register.getText().toString();
                String d = user_initdate_register.getText().toString();
                String e = user_email_register.getText().toString();

                //不能为空
                if (TextUtils.isEmpty(s)||TextUtils.isEmpty(p) || TextUtils.isEmpty(d)||TextUtils.isEmpty(e)) {
                    Toast.makeText(this,"不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    //添加到数据库
                    ContentValues values = new ContentValues();
                    values.put(DatabaseHelper.phone,p);
                    values.put(DatabaseHelper.password,s);
                    values.put(DatabaseHelper.initdate,d);
                    values.put(DatabaseHelper.email,e);
                    if (radio_men.isChecked()) {
                       values.put(DatabaseHelper.sex,"男");
                    }else {
                       values.put(DatabaseHelper.sex,"女");
                    }
                    long insert = writableDatabase.insert(DatabaseHelper.userInfo, null, values);
                    if (insert > 0) {
                        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
                break;
        }
    }
}
