package com.example.disni.pcnote.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by disni on 12/12/2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + MasterFile.Notes.TABLE_NAME + " (" +
                        MasterFile.Notes._ID + " INTEGER PRIMARY KEY, " +
                        MasterFile.Notes.COLUMN_NAME_NOTENAME + " TEXT," +
                        MasterFile.Notes.COLUMN_NAME_NOTECONTENT + " TEXT, " +
                        MasterFile.Notes.COLUMN_NAME_NOTEATTACHMENT + " BLOB)";

                db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addInfo(String noteName, String noteContent){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MasterFile.Notes.COLUMN_NAME_NOTENAME, noteName);
        values.put(MasterFile.Notes.COLUMN_NAME_NOTECONTENT, noteContent);

        long newRowId = db.insert(MasterFile.Notes.TABLE_NAME, null, values);
    }

    public List readInfo(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                MasterFile.Notes._ID,
                MasterFile.Notes.COLUMN_NAME_NOTENAME,
                MasterFile.Notes.COLUMN_NAME_NOTECONTENT
        };

        String selection = MasterFile.Notes.COLUMN_NAME_NOTENAME + " = ?";
        String[] selectionArgs = new String[]{ "New note" };

        String sortOrder = MasterFile.Notes.COLUMN_NAME_NOTENAME + " DESC";

        Cursor cursor = db.query(
          MasterFile.Notes.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List noteTitles = new ArrayList<>();

        while(cursor.moveToNext())
        {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MasterFile.Notes.COLUMN_NAME_NOTENAME));
            noteTitles.add(title);
        }
        cursor.close();
        return noteTitles;

    }

    public Cursor getNoteDetails(String noteTitle){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;

        String[] projection = {
                MasterFile.Notes._ID,
                MasterFile.Notes.COLUMN_NAME_NOTENAME,
                MasterFile.Notes.COLUMN_NAME_NOTECONTENT
        };

        String selection = MasterFile.Notes.COLUMN_NAME_NOTENAME + "=?";
        String[] selectionArgs = new String[]{ noteTitle };

        //String sortOrder = MasterFile.Notes.COLUMN_NAME_NOTENAME + " DESC";

        if (!noteTitle.equals("")) {
            cursor = db.query(
                    MasterFile.Notes.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );
        }
        else
        {
            cursor = db.query(
                    MasterFile.Notes.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }

       return cursor;

    }

/*
    public void deleteInfo(String userName){
        SQLiteDatabase db = getReadableDatabase();
        String selection = UsersMaster.Users.COLUMN_NAME_USERNAME + " LIKE ?";
        String[] selectionArgs = { userName };
        db.delete(UsersMaster.Users.TABLE_NAME, selection, selectionArgs);
    }
    */
}
