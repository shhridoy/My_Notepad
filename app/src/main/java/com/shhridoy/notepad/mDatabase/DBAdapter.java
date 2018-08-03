package com.shhridoy.notepad.mDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.provider.ContactsContract;
import android.widget.Toast;

/**
 * Created by whoami on 8/3/2018.
 */

public class DBAdapter {

    private Context context;
    private DatabaseHelper dbHelper;

    public DBAdapter (Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void saveNoteInDB(String title, String details, String password, String password_hint, String color) {
        try {
            dbHelper.addNote(title, details, password, password_hint, color);
            Toast.makeText(context, "Note saved!", Toast.LENGTH_LONG).show();
        } catch (SQLiteException e) {
            Toast.makeText(context, "Can't save!!!", Toast.LENGTH_LONG).show();
        }
    }

    public void editNoteInDB(int id, String title, String details, String password, String password_hint, String color) {
        boolean edited = dbHelper.editNote(id, title, details, password, password_hint, color);
        if (edited) {
            Toast.makeText(context, "Note updated.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Note doesn't updated!!", Toast.LENGTH_LONG).show();
        }
    }

    public boolean removeNoteFromDB(int id) {
        boolean removed = dbHelper.removeNote(id);
        if (removed) {
            Toast.makeText(context, "Notes removed!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Notes can't removed!", Toast.LENGTH_LONG).show();
        }
        return removed;
    }

}
