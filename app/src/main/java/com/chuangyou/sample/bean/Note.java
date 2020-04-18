package com.chuangyou.sample.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity(indexes = {
        @Index(value = "text, date DESC",unique = true)
})
public class Note {

    public static final int TEXT = 1;
    public static final int WARN = 2;

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String text;

    private Date date;
    private int type;

    @Generated(hash = 1529172362)
    public Note(Long id, @NotNull String text, Date date, int type) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.type = type;
    }
    @Generated(hash = 1272611929)
    public Note() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String t = "";
        if (type==Note.TEXT)
            t = "text";
        else if (type==Note.WARN)
            t = "warn";
        return "Note{" +
                "id= " + id +
                ", text= " + text +
                ", date= " + date +
                ", type= " + t +
                "}";
    }
    public void setId(Long id) {
        this.id = id;
    }
}
