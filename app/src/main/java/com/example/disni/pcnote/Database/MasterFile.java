package com.example.disni.pcnote.Database;

import android.provider.BaseColumns;

/**
 * Created by disni on 12/12/2017.
 */

public final class MasterFile {

    private MasterFile(){}

    public static class Notes implements BaseColumns{
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_NAME_NOTENAME = "notename";
        public static final String COLUMN_NAME_NOTECONTENT = "notecontent";
        public static final String COLUMN_NAME_NOTEATTACHMENT ="noteattachment";
    }
}
