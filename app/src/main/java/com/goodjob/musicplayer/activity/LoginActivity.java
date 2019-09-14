package com.goodjob.musicplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.goodjob.musicplayer.R;
import com.goodjob.musicplayer.bean.Bean;
import com.goodjob.musicplayer.sql.DatabaseHelper;
import com.goodjob.musicplayer.sql.Select;

import java.util.ArrayList;

/**
 * Created by 登录页面 on 2019/8/31 0031.
 */

public class LoginActivity extends Activity implements View.OnClickListener{

    private TextView add_new_user;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase writableDatabase;
    private EditText user_account_login;
    private EditText user_password_login;
    private Button user_login;
    private Select select;
    private ArrayList<Bean> list = new ArrayList<>();
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);
        //获取id
        initView();
        //点击事件
        initLinsenter();
        initData();
    }

    private void initData() {
        databaseHelper = new DatabaseHelper(this);
        writableDatabase = databaseHelper.getWritableDatabase();
        select = new Select();
    }

    private void initLinsenter() {
        //跳转到注册界面
        add_new_user.setOnClickListener(this);
        user_login.setOnClickListener(this);
    }

    private void initView() {
        add_new_user = (TextView) findViewById(R.id.add_new_user);
        user_login = (Button) findViewById(R.id.user_login);
        user_account_login = (EditText) findViewById(R.id.user_account_login);
        user_password_login = (EditText) findViewById(R.id.user_password_login);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //跳转到注册界面
            case R.id.add_new_user:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
                case R.id.user_login:
                    String p = user_account_login.getText().toString();
                    String s = user_password_login.getText().toString();
                    //不能为空
                    if (TextUtils.isEmpty(p)||TextUtils.isEmpty(s)) {
                        Toast.makeText(this,"账号和密码不能为空",Toast.LENGTH_SHORT).show();
                    }else {
                        ArrayList<Bean> select = this.select.getSelect(list, writableDatabase, 1, p, s);
                        Log.e("tags",select.toString());
                        if (select.size()==0) {
                            Toast.makeText(this,"账号或者密码错误！",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(this,"登录成功！",Toast.LENGTH_SHORT).show();
                            intent = new Intent(this, ListActivity.class);
                            int id = select.get(0).getId();
                            intent.putExtra("id",id+"");
                            startActivity(intent);
                            finish();
                        }
                    }
                    break;
        }
    }
}
