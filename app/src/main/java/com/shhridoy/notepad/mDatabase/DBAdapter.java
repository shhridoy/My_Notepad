package com.shhridoy.notepad.mDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

/**
 * Created by whoami on 8/3/2018.
 */

public class DBAdapter {

    public static void saveNoteInDB(Context context, String title, String details, String dateTime, int lock, String color) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        try {
            dbHelper.addNote(title, details, dateTime, lock, color);
            Toast.makeText(context, "Note saved!", Toast.LENGTH_LONG).show();
        } catch (SQLiteException e) {
            Toast.makeText(context, "Can't save!!!", Toast.LENGTH_LONG).show();
        }
    }

    public static void editNoteInDB(Context context, int id, String title, String details, String dateTime, int lock, String color) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        boolean edited = dbHelper.editNote(id, title, details, dateTime, lock, color);
        makeToast(context, edited, "Note updated", "Note doesn't updated!!");
    }

    public static void editNotesTitleAndDetails(Context context, int id, String title, String details, String dateTime) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        boolean edited = dbHelper.editNotesTitleDetails(id, title, details, dateTime);
        makeToast(context, edited, "Note updated", "Note doesn't updated!!");
    }

    public static void editNotesLock(Context context, int id, int lock) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        boolean passwdEdited = dbHelper.editNotesLock(id, lock);
        makeToast(context, passwdEdited, "Locked!", "Doesn't Lock!");
    }

    public static void editNotesColor(Context context, int id, String color, String dateTime) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        boolean colorChanged = dbHelper.editNotesColor(id, color, dateTime);
        makeToast(context, colorChanged, "Color changed!", "Color doesn't changed!");
    }

    public static boolean removeNoteFromDB(Context context, int id) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        boolean removed = dbHelper.removeNote(id);
        makeToast(context, removed, "Note removed!", "Note can't removed!");
        return removed;
    }

    private static void makeToast(Context context, boolean b, String pText, String nText) {
        if (b) {
            Toast.makeText(context, pText, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, nText, Toast.LENGTH_LONG).show();
        }
    }

}
