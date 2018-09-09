package com.shhridoy.notepad.mDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by whoami on 7/28/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constants.DROP_NOTES_TABLE);
        onCreate(db);
    }

    public void addNote(String title, String details, String dateTime, int lock, String color) {

        ContentValues cv = new ContentValues();
        cv.put(Constants.NOTES_TITLE, title);
        cv.put(Constants.NOTES_DETAILS, details);
        cv.put(Constants.NOTES_DATE_TIME, dateTime);
        cv.put(Constants.NOTES_LOCK, lock);
        cv.put(Constants.NOTES_COLOR, color);
        this.getWritableDatabase().insertOrThrow(Constants.NOTES_TABLE, "", cv);
        this.getWritableDatabase().close();

    }

    public boolean editNote(int id, String title, String details, String dateTime, int lock, String color) {
        ContentValues cv = new ContentValues();
        cv.put(Constants.NOTES_TITLE, title);
        cv.put(Constants.NOTES_DETAILS, details);
        cv.put(Constants.NOTES_DATE_TIME, dateTime);
        cv.put(Constants.NOTES_LOCK, lock);
        cv.put(Constants.NOTES_COLOR, color);

        int result = this.getWritableDatabase()
                .update(Constants.NOTES_TABLE, cv, Constants.NOTES_ID+" =?", new String[]{String.valueOf(id)});
        this.getWritableDatabase().close();
        return result > 0;
    }

    public boolean editNotesTitleDetails(int id, String title, String details, String dateTime) {
        ContentValues cv = new ContentValues();
        cv.put(Constants.NOTES_TITLE, title);
        cv.put(Constants.NOTES_DETAILS, details);
        cv.put(Constants.NOTES_DATE_TIME, dateTime);
        int result = this.getWritableDatabase()
                .update(Constants.NOTES_TABLE, cv, Constants.NOTES_ID+" =?", new String[]{String.valueOf(id)});
        this.getWritableDatabase().close();
        return result > 0;
    }

    public boolean editNotesLock(int id, int lock) {
        ContentValues cv = new ContentValues();
        cv.put(Constants.NOTES_LOCK, lock);
        int result = this.getWritableDatabase()
                .update(Constants.NOTES_TABLE, cv, Constants.NOTES_ID+" =?", new String[]{String.valueOf(id)});
        this.getWritableDatabase().close();
        return result > 0;
    }

    public boolean editNotesColor(int id, String color, String dateTime) {
        ContentValues cv = new ContentValues();
        cv.put(Constants.NOTES_COLOR, color);
        cv.put(Constants.NOTES_DATE_TIME, dateTime);
        int result = this.getWritableDatabase()
                .update(Constants.NOTES_TABLE, cv, Constants.NOTES_ID+" =?", new String[]{String.valueOf(id)});
        this.getWritableDatabase().close();
        return result > 0;
    }

    public Cursor getNotes() {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM "+Constants.NOTES_TABLE, null);
        return cursor;
    }

    public boolean removeNote(int id) {
        int result = this.getWritableDatabase().delete(Constants.NOTES_TABLE, Constants.NOTES_ID+" = "+id, null);
        this.getWritableDatabase().close();
        return result > 0;
    }

}
