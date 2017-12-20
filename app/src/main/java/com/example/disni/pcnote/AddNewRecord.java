package com.example.disni.pcnote;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.disni.pcnote.Database.DBHandler;

import java.nio.file.Path;

public class AddNewRecord extends AppCompatActivity {
    protected DBHandler mDbHandler;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Notes.db";

    Button btnSave, btnDiscard;
    TextView txtNoteName, mtxtNoteContent;
    boolean x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_record);
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
        mDbHandler = new DBHandler(this, DATABASE_NAME, null, DATABASE_VERSION);
        /*Context ctx = this;
        Path dbpath = ctx.getDatabasePath(DATABASE_NAME);*/
        buttonClick();
    }

    private void buttonClick() {
        btnSave = (Button)findViewById(R.id.btnSave);
        btnDiscard = (Button)findViewById(R.id.btnDiscard);
        txtNoteName = (TextView)findViewById(R.id.txtNoteName);
        mtxtNoteContent = (TextView)findViewById(R.id.mtxtNoteContent);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNoteName.getText().toString().equals("") || mtxtNoteContent.getText().toString().equals("")) {
                    String noteName = txtNoteName.getText().toString();
                    String noteContent = mtxtNoteContent.getText().toString();

                    mDbHandler.addInfo(noteName,noteContent);

                }

            }
        });

        btnDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNoteName.getText().toString().equals("")){
                    String noteName = txtNoteName.getText().toString();

                    x = mDbHandler.readInfo(noteName);
                }
            }
        });
    }

}
