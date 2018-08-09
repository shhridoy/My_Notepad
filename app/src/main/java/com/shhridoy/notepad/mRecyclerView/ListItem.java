package com.shhridoy.notepad.mRecyclerView;

/**
 * Created by whoami on 7/28/2018.
 */

public class ListItem {

    private int id;
    private String title;
    private String details;
    private String date_time;
    private int lock;
    private String color;

    public ListItem(int id, String title, String details, String date_time, int lock, String color) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.date_time = date_time;
        this.lock = lock;
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

    public int getLock() {
        return lock;
    }

    public String getColor() {
        return color;
    }
}
