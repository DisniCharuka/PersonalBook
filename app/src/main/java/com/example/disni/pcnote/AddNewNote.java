package com.example.disni.pcnote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.disni.pcnote.Database.DBHandler;

public class AddNewNote extends AppCompatActivity {
    protected DBHandler mDbHandler;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Notes.db";

    Button btnSave, btnDiscard;
    TextView txtNoteName, multxtNote;
    boolean x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        buttonClick();
    }

    private void buttonClick() {
        btnSave = (Button)findViewById(R.id.btnSave);
        btnDiscard = (Button)findViewById(R.id.btnDiscard);
        txtNoteName = (TextView)findViewById(R.id.txtNoteName);
        multxtNote = (TextView)findViewById(R.id.multxtNote);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtNoteName.getText().toString().equals("") || multxtNote.getText().toString().equals("")) {
                    String noteName = txtNoteName.getText().toString();
                    String noteContent = multxtNote.getText().toString();

                    mDbHandler.addInfo(noteName, noteContent);
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
