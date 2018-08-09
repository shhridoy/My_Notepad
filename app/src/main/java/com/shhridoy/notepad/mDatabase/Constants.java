package com.shhridoy.notepad.mDatabase;

/**
 * Created by whoami on 7/28/2018.
 */

public class Constants {

    public static final String DB_NAME = "notepad.db";
    public static final int DB_VERSION = 1;

    // NOTES TABLE CONSTANTS
    public static final String NOTES_TABLE = "notes";

    public static final String NOTES_ID = "notes_id";
    public static final String NOTES_TITLE = "notes_title";
    public static final String NOTES_DETAILS = "notes_details";
    public static final String NOTES_DATE_TIME = "notes_date_time";
    public static final String NOTES_LOCK = "notes_lock";
    //public static final String NOTES_PASS_HINT = "notes_pass_hint";
    public static final String NOTES_COLOR = "notes_color";

    public static final String CREATE_NOTES_TABLE = "CREATE TABLE "+ NOTES_TABLE +
            "( " +
            NOTES_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NOTES_TITLE +" TEXT, " +
            NOTES_DETAILS +" TEXT, " +
            NOTES_DATE_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
            NOTES_LOCK + " INTEGER DEFAULT 0, " +
            NOTES_COLOR + " TEXT);";

    public static final String DROP_NOTES_TABLE = "DROP TABLE IF EXISTS " + NOTES_TABLE;

}
