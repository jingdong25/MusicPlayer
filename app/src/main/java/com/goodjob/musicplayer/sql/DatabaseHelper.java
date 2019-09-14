package com.goodjob.musicplayer.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // 数据库表名
    public static final String userInfo = "userInfo";
    //手机号
    public static final String phone = "phone";
    //密码
    public static final String password = "password";
    //出生日期
    public static final String initdate = "initdate";
    //邮箱
    public static final String email = "email";
    //性别
    public static final String sex = "sex";
    //评论的表
    public static final String pingtable = "pingtable";
    //歌曲名字
    public static final String name = "name";
    //评论内容
    public static final String content = "content";

        public DatabaseHelper( Context context) {
        super(context, "db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库sql语句 并 执行
        String userInfos = "create table "+userInfo+"(id Integer primary key autoincrement, " +phone+"  varchar(200),"+password+" varchar(200),"+initdate+" varchar(200),"+email+" varchar(200),"+sex+" varchar(200))";
        String pinglun   = "create table "+pingtable+"(id Integer primary key autoincrement," +name+" varchar(200),"+content+" varchar(200))";
        db.execSQL(userInfos);
        db.execSQL(pinglun);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
