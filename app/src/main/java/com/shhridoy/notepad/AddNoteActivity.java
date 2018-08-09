package com.shhridoy.notepad;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shhridoy.notepad.mDatabase.DBAdapter;
import com.shhridoy.notepad.mDatabase.RetrieveDBInfoByID;
import com.shhridoy.notepad.mDialogs.MyDialogs;
import com.shhridoy.notepad.mUtilities.MyPreferences;

public class AddNoteActivity extends AppCompatActivity {

    EditText etNotesTitle, etNotesDetails;
    Button saveBtn;

    private int note_id;

    private String note_title = null, note_details = null, note_color = null;
    private static int note_lock = 0;

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
            note_lock = retrieveDBInfo.getLock();
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
                                note_lock,
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

            if (!MyPreferences.getPreference(this, "Password").equals("Default")) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(Html.fromHtml("<font color='#000000'>Note Lock!!</font>"));
                builder.setMessage(Html.fromHtml("<font color='#000000'>Do you want to lock this note?</font>"));

                builder.setNegativeButton(Html.fromHtml("No"),new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton(Html.fromHtml("Yes"),new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        note_lock = 1;
                        if (getIntent().getStringExtra("ID") != null) {
                            DBAdapter.editNotesLock(getApplicationContext(), note_id, note_lock);
                        }
                        dialog.cancel();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            } else {

                boolean isPassSet = MyDialogs.setPassword(this);
                if (isPassSet) {
                    note_lock = 1;
                    if (getIntent().getStringExtra("ID") != null) {
                        DBAdapter.editNotesLock(getApplicationContext(), note_id, note_lock);
                    }
                }
            }
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
                note_lock = 1;
                if (getIntent().getStringExtra("ID") != null) {
                    DBAdapter.editNotesLock(getApplicationContext(), note_id, note_lock);
                }
                dialog.cancel();
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
