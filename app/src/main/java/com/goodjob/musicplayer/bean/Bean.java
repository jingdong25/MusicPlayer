package com.goodjob.musicplayer.bean;

/**
 * Created by Administrator on 2019/8/31 0031.
 */

public class Bean {
    private int id;
    private String phone;
    private String password;
    private String initdate;
    private String email;
    private String sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInitdate() {
        return initdate;
    }

    public void setInitdate(String initdate) {
        this.initdate = initdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Bean(int id, String phone, String password, String initdate, String email, String sex) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.initdate = initdate;
        this.email = email;
        this.sex = sex;
    }
}
