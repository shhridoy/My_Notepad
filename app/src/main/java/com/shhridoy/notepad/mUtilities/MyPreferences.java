package com.shhridoy.notepad.mUtilities;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by whoami on 8/9/2018.
 */

public class MyPreferences {

    public static void setPreference(Context context, String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(key, value)
                .apply();
    }

    public static String getPreference(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, "Default");
    }

}
