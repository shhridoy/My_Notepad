package com.shhridoy.notepad.mUtilities;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by whoami on 9/9/2018.
 */

public class DatesFormat {

    public static String getFullDateAndTime () {
        Calendar c = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
        String datetime = dateformat.format(c.getTime());
        return datetime;
    }

    public static String getFullDateAndTime2() {
        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        return mydate;
    }

    public static String getDateAndYear() {
        String date = null;
        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        String[] dateAndTime = mydate.split(" ");
        try {
            date = dateAndTime[0] + " " + dateAndTime[1] + " " + dateAndTime[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            date = mydate;
            e.printStackTrace();
        }

        return date;
    }

}
