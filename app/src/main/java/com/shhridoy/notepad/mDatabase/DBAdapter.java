package com.shhridoy.notepad.mDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.provider.ContactsContract;
import android.widget.Toast;

/**
 * Created by whoami on 8/3/2018.
 */

public class DBAdapter {

    public static void saveNoteInDB(Context context, String title, String details, String password, String password_hint, String color) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        try {
            dbHelper.addNote(title, details, password, password_hint, color);
            Toast.makeText(context, "Note saved!", Toast.LENGTH_LONG).show();
        } catch (SQLiteException e) {
            Toast.makeText(context, "Can't save!!!", Toast.LENGTH_LONG).show();
        }
    }

    public static void editNoteInDB(Context context, int id, String title, String details, String password, String password_hint, String color) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        boolean edited = dbHelper.editNote(id, title, details, password, password_hint, color);
        makeToast(context, edited, "Note updated", "Note doesn't updated!!");
    }

    public static void editNotesTitleAndDetails(Context context, int id, String title, String details) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        boolean edited = dbHelper.editNotesTitleDetails(id, title, details);
        makeToast(context, edited, "Note updated", "Note doesn't updated!!");
    }

    public static void editNotesPassword(Context context, int id, String password, String password_hint) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        boolean passwdEdited = dbHelper.editNotesPassword(id, password, password_hint);
        makeToast(context, passwdEdited, "Password changed!", "Password doesn't changed!");
    }

    public static void editNotesColor(Context context, int id, String color) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        boolean colorChanged = dbHelper.editNotesColor(id, color);
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
