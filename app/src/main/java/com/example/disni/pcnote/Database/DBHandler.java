package com.example.disni.pcnote.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public boolean readInfo(String noteName){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                MasterFile.Notes._ID,
                MasterFile.Notes.COLUMN_NAME_NOTENAME,
                MasterFile.Notes.COLUMN_NAME_NOTECONTENT
        };

        String selection = MasterFile.Notes.COLUMN_NAME_NOTENAME + " = ?";
        String[] selectionArgs = { noteName };

        String sortOrder = MasterFile.Notes.COLUMN_NAME_NOTENAME + " DESC";

        Cursor cursor = db.query(
          MasterFile.Notes.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while(cursor.moveToNext()){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
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
