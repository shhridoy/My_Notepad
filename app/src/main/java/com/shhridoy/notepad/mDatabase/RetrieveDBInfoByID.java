package com.shhridoy.notepad.mDatabase;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by whoami on 8/1/2018.
 */

public class RetrieveDBInfoByID {

    private Context context;
    private int id;
    private String title = null, details = null, date_time = null, color = null;
    private int lock = 0;

    public RetrieveDBInfoByID(Context context, int id) {
        this.context = context;
        this.id = id;
        CollectInfoFromDB(id);
    }

    private void CollectInfoFromDB (int id) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        Cursor cursor = databaseHelper.getNotes();
        while (cursor.moveToNext()) {
            if (id == cursor.getInt(0)) {
                title = cursor.getString(1);
                details = cursor.getString(2);
                date_time = cursor.getString(3);
                lock = cursor.getInt(4);
                color = cursor.getString(5);
                break;
            }
        }
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
