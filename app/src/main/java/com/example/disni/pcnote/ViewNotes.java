package com.example.disni.pcnote;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.disni.pcnote.Database.DBHandler;

import java.util.ArrayList;
import java.util.List;

public class ViewNotes extends AppCompatActivity {
    protected DBHandler mDbHandler;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Notes.db";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);
        mDbHandler = new DBHandler(this, DATABASE_NAME, null, DATABASE_VERSION);

        final ArrayList<String> noteList = new ArrayList<>();
        ListView lstNotes = (ListView)findViewById(R.id.lstNotes);
        List myNotes = mDbHandler.readInfo();
        ArrayAdapter arrayAdapter = new ArrayAdapter(ViewNotes.this, android.R.layout.simple_list_item_1, myNotes);
        lstNotes.setAdapter(arrayAdapter);

    }
}
