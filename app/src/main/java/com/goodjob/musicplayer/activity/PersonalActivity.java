package com.goodjob.musicplayer.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.goodjob.musicplayer.R;
import com.goodjob.musicplayer.bean.Bean;
import com.goodjob.musicplayer.sql.DatabaseHelper;
import com.goodjob.musicplayer.sql.Select;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/8/31 0031.
 */

public class PersonalActivity extends Activity {

    private ArrayList<Bean> list = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase writableDatabase;
    private ArrayList<Bean> select1;
    private EditText setphone;
    private EditText setemail;
    private EditText setinitdate;
    private RadioButton radio_men;
    private RadioButton radio_women;
    private EditText setpassword;
    private Button user_update;
    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);
        initData();
        initView();
        initLinsenter();
        inidSetText();
    }

    private void inidSetText() {
        //根据id进行查询并且赋值
        String email = select1.get(0).getEmail();
        String initdate = select1.get(0).getInitdate();
        String sex = select1.get(0).getSex();
        String phone = select1.get(0).getPhone();
        String password = select1.get(0).getPassword();
        setphone.setText(phone);
        setpassword.setText(password);
        setemail.setText(email);
        setinitdate.setText(initdate);
        //如果查询出来是男的让后就让男的复选框选中
        if (sex.equals("男")) {
            radio_men.setChecked(true);
        }else {
            radio_women.setChecked(true);
        }
    }

    private void initLinsenter() {
        user_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String i = setinitdate.getText().toString();
                final String p = setphone.getText().toString();
                final String s = setpassword.getText().toString();
                final String e = setemail.getText().toString();
                //不能为空
                if (TextUtils.isEmpty(i)||TextUtils.isEmpty(p)||TextUtils.isEmpty(s)||TextUtils.isEmpty(e)) {
                    Toast.makeText(PersonalActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    //修改个人信息
                    ContentValues values = new ContentValues();
                    values.put(DatabaseHelper.phone,p);
                    values.put(DatabaseHelper.password,s);
                    values.put(DatabaseHelper.initdate,i);
                    values.put(DatabaseHelper.email,e);
                    if (radio_men.isChecked()) {
                        values.put(DatabaseHelper.sex,"男");
                    }else {
                        values.put(DatabaseHelper.sex,"女");
                    }
                    //根据手机号修改个人信息
                    int update = writableDatabase.update(DatabaseHelper.userInfo, values,   "id=?", new String[]{id});
                    if (update > 0) {
                        Toast.makeText(PersonalActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

    private void initView() {
        setphone = (EditText) findViewById(R.id.setphone);
        setpassword = (EditText) findViewById(R.id.setpassword);
        setemail = (EditText) findViewById(R.id.setemail);
        setinitdate = (EditText) findViewById(R.id.setinitdate);
        radio_men = (RadioButton) findViewById(R.id.radio_men);
        radio_women = (RadioButton) findViewById(R.id.radio_women);
        user_update = (Button) findViewById(R.id.user_update);
        
    }

    private void initData() {
        Intent intent = getIntent();
        databaseHelper = new DatabaseHelper(this);
        writableDatabase = databaseHelper.getWritableDatabase();
        id = intent.getStringExtra("id");
        Select select = new Select();
        //根据账号和密码进行查询
        select1 = select.getSelect(list, writableDatabase, 0, id, null);
    }
}
