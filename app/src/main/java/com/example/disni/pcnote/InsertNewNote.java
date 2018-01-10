package com.example.disni.pcnote;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.disni.pcnote.Database.DBHandler;
import com.example.disni.pcnote.Database.MasterFile;

import java.util.ArrayList;
import java.util.List;

public class InsertNewNote extends AppCompatActivity {
    protected DBHandler mDbHandler;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Notes.db";

    Button btnSave, btnDiscard;
    TextView txtTitle, mltTtxtNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_new_note);
        buttonClick(); // this should happen on top because this initialize the txt fields.
        mDbHandler = new DBHandler(this, DATABASE_NAME, null, DATABASE_VERSION);
        String note = null;

        Bundle info = getIntent().getExtras();
        if (info != null) {
            note = info.getString("noteSelected");
        }

        Cursor cursor = null;

        if (note != null)
            cursor = mDbHandler.getNoteDetails(note);           //fetch note details for given title


        if (cursor != null)
        {
            if (cursor.getCount() > 0 )
            {
                while (cursor.moveToNext()){                 //reading the cursor until it has content
                    String t1 = cursor.getString( cursor.getColumnIndexOrThrow(MasterFile.Notes.COLUMN_NAME_NOTENAME));
                    String t2 = cursor.getString( cursor.getColumnIndexOrThrow(MasterFile.Notes.COLUMN_NAME_NOTECONTENT));

                    txtTitle.setText(t1);
                    mltTtxtNote.setText(t2);
                }
            }
        }

    }

    private void buttonClick() {
        btnSave = (Button)findViewById(R.id.btnSave);
        btnDiscard = (Button)findViewById(R.id.btnDiscard);
        mltTtxtNote = (TextView)findViewById(R.id.mltTxtNote);
        txtTitle = (TextView)findViewById(R.id.txtTitle);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(txtTitle.getText().toString().equals("")|| (mltTtxtNote.getText().toString().equals("")))){
                    mDbHandler.addInfo(txtTitle.getText().toString(), mltTtxtNote.getText().toString());
                    txtTitle.setText("");
                    mltTtxtNote.setText("");
                    Log.d("insert success", "Successfully inserted!");
                }
            }
        });

        /*btnDiscard.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             boolean x;
             if(!(txtTitle.getText().toString().equals(""))){

                 x = mDbHandler.readInfo(txtTitle.getText().toString());
                 if(x)
                     Log.d(" success", "Existin record!");
                 else
                     Log.d(" not success", "no record!");
             }
         }
        });*/
    }

}
