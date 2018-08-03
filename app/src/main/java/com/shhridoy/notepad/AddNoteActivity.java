package com.shhridoy.notepad;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shhridoy.notepad.mDatabase.DBAdapter;
import com.shhridoy.notepad.mDatabase.DatabaseHelper;
import com.shhridoy.notepad.mDatabase.RetrieveDBInfoByID;

public class AddNoteActivity extends AppCompatActivity {

    EditText etNotesTitle, etNotesDetails;
    Button saveBtn;

    private int note_id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etNotesTitle = findViewById(R.id.addNoteTitleET);
        etNotesDetails = findViewById(R.id.addNoteDetailsET);
        saveBtn = findViewById(R.id.SaveBtn);

        if (getIntent() != null) {
            note_id = getIntent().getIntExtra("ID", 0);
        }

        if (note_id != -1) {
            RetrieveDBInfoByID retrieveDBInfo = new RetrieveDBInfoByID(this, note_id);
            etNotesTitle.setText(retrieveDBInfo.getTitle());
            etNotesDetails.setText(retrieveDBInfo.getDetails());
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNotesDetails.getText().toString().trim().length() != 0) {
                    DBAdapter dbAdapter = new DBAdapter(getApplicationContext());
                    if (note_id == -1) {
                        dbAdapter.saveNoteInDB(
                                etNotesTitle.getText().toString(),
                                etNotesDetails.getText().toString(),
                                null,
                                null,
                                null);
                    } else {
                        dbAdapter.editNoteInDB(
                                note_id,
                                etNotesTitle.getText().toString(),
                                etNotesDetails.getText().toString(),
                                null,
                                null,
                                null
                        );
                    }
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Note is empty!!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}
