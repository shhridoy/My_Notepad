package com.shhridoy.notepad;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.shhridoy.notepad.mDatabase.DatabaseHelper;
import com.shhridoy.notepad.mFragments.SettingsFragment;
import com.shhridoy.notepad.mRecyclerView.ListItem;
import com.shhridoy.notepad.mRecyclerView.RecyclerViewAdapter;
import com.shhridoy.notepad.mUtilities.MyPreferences;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> itemsList;
    private DatabaseHelper dbHelper;
    private Cursor cursor;
    private String viewPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemsList = new ArrayList<>();

        loadRecyclerViewFromDatabase();

        viewPref = MyPreferences.getPreference(this, "Notes View");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (fragmentManager.findFragmentByTag("Settings") != null && fragmentManager.findFragmentByTag("Settings").isVisible()) {
                fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Settings")).commit();
                if (!viewPref.equals(MyPreferences.getPreference(this, "Notes View"))) {
                    finish();
                    startActivity(new Intent(this, MainActivity.class));
                }
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            
            return true;
        } else if (id == R.id.action_add_notes) {
            startActivity(new Intent(this, AddNoteActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_trash_can) {
            Toast.makeText(this, "Trash Can", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_theme) {
            Toast.makeText(this, "Theme", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_settings) {

            if(fragmentManager.findFragmentByTag("Settings") != null) {
                fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Settings")).commit();
            } else {
                fragmentManager.beginTransaction().add(android.R.id.content, new SettingsFragment(), "Settings").commit();
            }

        } else if (id == R.id.nav_about) {
            Toast.makeText(this, "About", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "Sharing Intent", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "Sending Intent", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadRecyclerViewFromDatabase(){
        itemsList.clear();

        dbHelper = new DatabaseHelper(this);
        cursor = dbHelper.getNotes();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No notes saved yet!",Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String details = cursor.getString(2);
                String date_time = cursor.getString(3);
                int lock = cursor.getInt(4);
                String color = cursor.getString(5);

                ListItem listItems = new ListItem(id, title, details, date_time, lock, color);
                itemsList.add(listItems);
                adapter = new RecyclerViewAdapter(this, itemsList);
                recyclerView.setAdapter(adapter);
            }
        }
    }
}
