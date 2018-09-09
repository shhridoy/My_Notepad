package com.shhridoy.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shhridoy.notepad.mDatabase.DBAdapter;
import com.shhridoy.notepad.mDatabase.DatabaseHelper;
import com.shhridoy.notepad.mDatabase.RetrieveDBInfoByID;

public class ViewNoteActivity extends AppCompatActivity {

    private TextView tvTitle, tvDetails, tvDates;
    private Button btnEdit;
    private int note_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
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

        tvTitle = findViewById(R.id.viewNoteTitleTV);
        tvDetails = findViewById(R.id.viewNoteDetailsTV);
        tvDates = findViewById(R.id.viewNoteDatesTV);

        note_id = Integer.parseInt(getIntent().getStringExtra("ID"));

        if (getIntent() != null) {
            RetrieveDBInfoByID dbdata = new RetrieveDBInfoByID(this, note_id);
            tvTitle.setText(dbdata.getTitle());
            tvDetails.setText(dbdata.getDetails());
            tvDates.setText(dbdata.getDate_time());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_delete_note) {
            //DBAdapter dbAdapter = new DBAdapter(getApplicationContext());
            boolean removed = DBAdapter.removeNoteFromDB(getApplicationContext(), note_id);
            if (removed) {
                finish();
            }
            return true;
        } else if (id == R.id.action_edit_note) {
            Intent intent = new Intent(ViewNoteActivity.this, AddNoteActivity.class);
            intent.putExtra("ID", String.valueOf(note_id));
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
