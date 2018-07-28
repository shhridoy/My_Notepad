package com.shhridoy.notepad.mRecyclerView;

/**
 * Created by whoami on 7/28/2018.
 */

public class ListItem {

    private int id;
    private String title;
    private String details;
    private String date_time;
    private String password;
    private String password_hint;
    private String color;

    public ListItem(int id, String title, String details, String date_time, String password, String password_hint, String color) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.date_time = date_time;
        this.password = password;
        this.password_hint = password_hint;
        this.color = color;
    }

    public ListItem(int id, String title, String details, String date_time) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.date_time = date_time;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public String getDate_time() {
        return date_time;
    }

    public String getPassword() {
        return password;
    }

    public String getPassword_hint() {
        return password_hint;
    }

    public String getColor() {
        return color;
    }
}
