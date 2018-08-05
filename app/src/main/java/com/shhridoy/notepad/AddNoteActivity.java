package com.shhridoy.notepad;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shhridoy.notepad.mDatabase.DBAdapter;
import com.shhridoy.notepad.mDatabase.RetrieveDBInfoByID;

public class AddNoteActivity extends AppCompatActivity {

    EditText etNotesTitle, etNotesDetails;
    Button saveBtn;

    private int note_id;

    private String note_title = null, note_details = null, note_password = null, note_password_hint = null, note_color = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etNotesTitle = findViewById(R.id.addNoteTitleET);
        etNotesDetails = findViewById(R.id.addNoteDetailsET);
        saveBtn = findViewById(R.id.SaveBtn);

        if (getIntent().getStringExtra("ID") != null) {
            note_id = Integer.parseInt(getIntent().getStringExtra("ID"));
            RetrieveDBInfoByID retrieveDBInfo = new RetrieveDBInfoByID(this, note_id);
            etNotesTitle.setText(retrieveDBInfo.getTitle());
            etNotesDetails.setText(retrieveDBInfo.getDetails());

            note_title = retrieveDBInfo.getTitle();
            note_details = retrieveDBInfo.getDetails();
            note_password = retrieveDBInfo.getPassword();
            note_password_hint = retrieveDBInfo.getPassword_hint();
            note_color = retrieveDBInfo.getColor();
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNotesDetails.getText().toString().trim().length() != 0 && etNotesTitle.getText().toString().trim().length() != 0) {

                    note_title = etNotesTitle.getText().toString();
                    note_details = etNotesDetails.getText().toString();

                    if (getIntent().getStringExtra("ID") == null) {
                        DBAdapter.saveNoteInDB(
                                getApplicationContext(),
                                note_title,
                                note_details,
                                note_password,
                                note_password_hint,
                                note_color
                        );
                    } else {
                        DBAdapter.editNotesTitleAndDetails(
                                getApplicationContext(),
                                note_id,
                                note_title,
                                note_details
                        );
                    }
                    Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Note is empty!!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_color) {

            return true;
        } else if (id == R.id.action_add_password) {
            passwordSetDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void passwordSetDialog() {
        final Dialog dialog = new Dialog(this);
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
                String password1 = passwdField1.getText().toString();
                String password2 = passwdField2.getText().toString();
                String passwordHint = passwdHintField.getText().toString();
                if (password1.trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "You must put a password first.", Toast.LENGTH_LONG).show();
                } else if (password2.trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "You must retype password once again.", Toast.LENGTH_LONG).show();
                } else if (passwordHint.trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "You should put a password hint.", Toast.LENGTH_LONG).show();
                } else if (!password1.equals(password2)) {
                    Toast.makeText(getApplicationContext(), "Password doesn't match.", Toast.LENGTH_LONG).show();
                } else {
                    note_password = passwdField1.getText().toString().trim();
                    note_password_hint = passwdHintField.getText().toString().trim();
                    if (getIntent().getStringExtra("ID") != null) {
                        DBAdapter.editNotesPassword(getApplicationContext(), note_id, note_password, note_password_hint);
                    }
                    dialog.cancel();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

}
