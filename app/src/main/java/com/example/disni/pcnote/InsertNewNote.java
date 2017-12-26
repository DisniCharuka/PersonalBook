package com.example.disni.pcnote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.disni.pcnote.Database.DBHandler;

public class InsertNewNote extends AppCompatActivity {
    protected DBHandler mDbHandler;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Notes.db";

    Button btnSave, btnDiscard;
    TextView txtTitle, txtNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_new_note);
        mDbHandler = new DBHandler(this, DATABASE_NAME, null, DATABASE_VERSION);
        buttonClick();
    }

    private void buttonClick() {
        btnSave = (Button)findViewById(R.id.btnSave);
        btnDiscard = (Button)findViewById(R.id.btnDiscard);
        txtNote = (TextView)findViewById(R.id.txtNote);
        txtTitle = (TextView)findViewById(R.id.txtTitle);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(txtTitle.getText().toString().equals("")|| (txtNote.getText().toString().equals("")))){
                    mDbHandler.addInfo(txtTitle.getText().toString(), txtNote.getText().toString());
                    txtTitle.setText("");
                    txtNote.setText("");
                    Log.d("insert success", "Successfully inserted!");
                }
            }
        });

        btnDiscard.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             boolean x;
             if(!(txtTitle.getText().toString().equals(""))){

                 x = mDbHandler.readInfo(txtTitle.getText().toString());
                 Log.d(" success", "Existin record!");
             }
         }
        });
    }

}