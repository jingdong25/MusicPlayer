package com.goodjob.musicplayer.sql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.goodjob.musicplayer.bean.Bean;

import java.util.ArrayList;

public class Select {
    private Cursor cursor;
    public ArrayList<Bean> getSelect(ArrayList<Bean> list, SQLiteDatabase writableDatabase , int i, String phone,String password) {
        if (i == 0) {
            // 注意空格！
            cursor = writableDatabase.query(DatabaseHelper.userInfo, null,"id=?", new String[]{phone}, null, null,null);
        }else if(i == 1){
            cursor = writableDatabase.query(DatabaseHelper.userInfo,null,"phone=? and password=?",new String[]{phone,password},null,null,null);
        }
        list.clear();
        int phone2 = cursor.getColumnIndex(DatabaseHelper.phone);
        int password2 = cursor.getColumnIndex(DatabaseHelper.password);
        int email = cursor.getColumnIndex(DatabaseHelper.email);
        int id = cursor.getColumnIndex("id");
        int initdate = cursor.getColumnIndex(DatabaseHelper.initdate);
        int sex = cursor.getColumnIndex(DatabaseHelper.sex);
        while (cursor.moveToNext()) {
            String name1 = cursor.getString(phone2);
            String sexs = cursor.getString(sex);
            String password1 = cursor.getString(password2);
            String email1 = cursor.getString(email);
            String initdate1 = cursor.getString(initdate);
            Integer id1 = cursor.getInt(id);
            Bean bean = new Bean(id1, name1, password1, initdate1, email1, sexs);
            Log.e("tag",bean.toString());
            list.add(bean);
        }
        return list;
    }
}
