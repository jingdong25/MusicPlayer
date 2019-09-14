package com.goodjob.musicplayer.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.goodjob.musicplayer.R;
import com.goodjob.musicplayer.bean.Bean;
import com.goodjob.musicplayer.bean.CommentBean;
import com.goodjob.musicplayer.sql.DatabaseHelper;
import com.goodjob.musicplayer.sql.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PingActivity extends AppCompatActivity implements View.OnClickListener {

    private String title;
    private ImageView comment_back;
    private ListView comment_recycler;
    private ImageView comment_publish;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase writableDatabase;
    private ArrayList<Bean> select1;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_ping);
        initData();
        initView();
        initLinsenter();
    }

    private void initLinsenter() {
        comment_back.setOnClickListener(this);
        comment_publish.setOnClickListener(this);
    }

    private void initView() {
        comment_back = (ImageView) findViewById(R.id.comment_back);
        comment_recycler = (ListView) findViewById(R.id.comment_recycler);
        comment_publish = (ImageView) findViewById(R.id.comment_publish);
        adapter = new ArrayAdapter<>(PingActivity.this, android.R.layout.simple_list_item_1, list);
        comment_recycler.setAdapter(adapter);
    }

    private void initData() {
        databaseHelper = new DatabaseHelper(this);
        writableDatabase = databaseHelper.getWritableDatabase();
        //根据账号和密码进行查询
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
       select();
    }

    private void select() {
        list.clear();
        Cursor cursor = writableDatabase.query(DatabaseHelper.pingtable, null, DatabaseHelper.name + "=?", new String[]{title}, null, null, null);
        int name = cursor.getColumnIndex(DatabaseHelper.name);
        int id = cursor.getColumnIndex("id");
        int content = cursor.getColumnIndex(DatabaseHelper.content);
        while (cursor.moveToNext()) {
            String name1 = cursor.getString(name);
            String content1 = cursor.getString(content);
            Integer id1 = cursor.getInt(id);
//            CommentBean commentBean = new CommentBean(id1, name1, content1);
            Log.e("tag",content1);
            list.add(content1);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //返回
            case R.id.comment_back:
                finish();
                break;
            case R.id.comment_publish:
                initDialog();
                break;
        }
    }

    private void initDialog() {
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(PingActivity.this);
        final View dialogView = LayoutInflater.from(PingActivity.this).inflate(R.layout.publish_popup_view,null);
        final EditText pub_ed_txt = (EditText) dialogView.findViewById(R.id.pub_ed_txt);
        customizeDialog.setTitle("发布评论");
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("确定发送",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String trim = pub_ed_txt.getText().toString().trim();
                        if (!trim.isEmpty()) {
                            ContentValues values = new ContentValues();
                            values.put(DatabaseHelper.content,trim);
                            values.put(DatabaseHelper.name,title);
                            long insert = writableDatabase.insert(DatabaseHelper.pingtable, null, values);
                            if (insert > 0) {
                                select();
                                Toast.makeText(PingActivity.this, "评论成功", Toast.LENGTH_LONG).show();
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(PingActivity.this, "输入框不能为空", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        customizeDialog.setNegativeButton("取消发送",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        customizeDialog.show();
    }
}
