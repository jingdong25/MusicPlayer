package com.goodjob.musicplayer.bean;

/**
 * Created by Administrator on 2019/9/1 0001.
 */

public class CommentBean {
    private int id;
    private String name;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CommentBean(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }
}
