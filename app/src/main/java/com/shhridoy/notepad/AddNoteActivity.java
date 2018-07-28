package com.shhridoy.notepad;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shhridoy.notepad.mDatabase.DatabaseHelper;

public class AddNoteActivity extends AppCompatActivity {

    EditText etNotesTitle, etNotesDetails;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        etNotesTitle = findViewById(R.id.addNoteTitleET);
        etNotesDetails = findViewById(R.id.addNoteDetailsET);
        saveBtn = findViewById(R.id.SaveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNotesDetails.getText().toString().trim().length() != 0) {
                    saveNoteToDB(
                            etNotesTitle.getText().toString(),
                            etNotesDetails.getText().toString(),
                            null,
                            null,
                            null);
                } else {
                    Toast.makeText(getApplicationContext(), "Note is empty!!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void saveNoteToDB(String title, String details, String password, String password_hint, String color) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        try {
            dbHelper.addNote(title, details, password, password_hint, color);
            Toast.makeText(this, "Note saved!", Toast.LENGTH_LONG).show();
        } catch (SQLiteException e) {
            Toast.makeText(this, "Can't save!!!", Toast.LENGTH_LONG).show();
        }
    }

}
