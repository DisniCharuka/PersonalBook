package com.example.disni.pcnote;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        //final ArrayList<String> noteList = new ArrayList<>();
        final ListView lstNotes = (ListView)findViewById(R.id.lstNotes);
        List myNotes = mDbHandler.readInfo();
        ArrayAdapter arrayAdapter = new ArrayAdapter(ViewNotes.this, android.R.layout.simple_list_item_1, myNotes);
        lstNotes.setAdapter(arrayAdapter);

        lstNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String noteAtPosition = lstNotes.getItemAtPosition(position).toString();
                //String note = noteList.get(position);
                Intent intent = new Intent(ViewNotes.this, InsertNewNote.class );
                intent.putExtra("noteSelected", noteAtPosition);
                startActivity(intent);

            }
        });
    }
}
