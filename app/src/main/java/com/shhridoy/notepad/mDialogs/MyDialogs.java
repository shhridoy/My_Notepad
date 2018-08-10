package com.shhridoy.notepad.mDialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shhridoy.notepad.R;
import com.shhridoy.notepad.ViewNoteActivity;
import com.shhridoy.notepad.mUtilities.MyPreferences;

/**
 * Created by whoami on 8/9/2018.
 */

public class MyDialogs {

    private static boolean value;

    public static boolean setPassword(final Context context) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.set_password_dialog);

        final EditText passwdField1 = dialog.findViewById(R.id.setPasswdDlgPasswdET1);
        final EditText passwdField2 = dialog.findViewById(R.id.setPasswdDlgPasswdET2);
        final EditText passwdHintField = dialog.findViewById(R.id.setPasswdDlgPasswdHintET);
        Button btnCancel = dialog.findViewById(R.id.setPasswdDlgBtnCancel);
        Button btnOk = dialog.findViewById(R.id.setPasswdDlgBtnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwdField1.getText().toString().trim().length() == 0) {
                    Toast.makeText(context, "You must put a password first.", Toast.LENGTH_LONG).show();
                } else if (passwdField2.getText().toString().trim().length() == 0) {
                    Toast.makeText(context, "You must retype password once again.", Toast.LENGTH_LONG).show();
                } else if (passwdHintField.getText().toString().trim().length() == 0) {
                    Toast.makeText(context, "You should put a password hint.", Toast.LENGTH_LONG).show();
                } else if (!passwdField1.getText().toString().trim().equals(passwdField2.getText().toString().trim())) {
                    Toast.makeText(context, "Password doesn't match.", Toast.LENGTH_LONG).show();
                } else {
                    MyPreferences.setPreference(context, "Password", passwdField1.getText().toString().trim());
                    MyPreferences.setPreference(context, "Password Hint", passwdHintField.getText().toString().trim());
                    value = true;
                    dialog.cancel();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = false;
                dialog.cancel();
            }
        });

        dialog.show();

        return value;
    }

    public static boolean inputPassword(final Context context, final int note_id, final String tag) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.input_password_dialog);

        final EditText etPassword = dialog.findViewById(R.id.inputPasswdDlgPasswdET);
        final TextView tvPasswordHint = dialog.findViewById(R.id.inputPasswdDlgPasswdHintTV);
        Button btnOk = dialog.findViewById(R.id.inputPasswdDlgBtnOk);
        Button btnCancel = dialog.findViewById(R.id.inputPasswdDlgBtnCancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyPreferences.getPreference(context, "Password").equals(etPassword.getText().toString().trim())) {
                    if (tag.equalsIgnoreCase("View Note")) {
                        Intent intent = new Intent(context, ViewNoteActivity.class);
                        intent.putExtra("ID", String.valueOf(note_id));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                    value = true;
                    dialog.cancel();
                } else {
                    Toast.makeText(context, "Password is incorrect!!", Toast.LENGTH_LONG).show();
                    tvPasswordHint.setText("Hint: "+MyPreferences.getPreference(context, "Password Hint"));
                    value = false;
                }

                etPassword.setText("");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = false;
                dialog.cancel();
            }
        });

        dialog.show();
        return value;
    }

    private static void makeToast(Context context, boolean b, String pText, String nText) {
        if (b) {
            Toast.makeText(context, pText, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, nText, Toast.LENGTH_LONG).show();
        }
    }

}



