package com.samsung.itschool.library;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "library1.db";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE = "library";
    public static final String LIBRARY_ID = "_id";
    public static final String LIBRARY_TITLE = "title";
    public static final String LIBRARY_AUTHOR = "author";
    public static final String LIBRARY_GENRE = "genre";
    public static final String LIBRARY_YEAR = "year";

    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + DATABASE_TABLE +
                "(" + LIBRARY_ID + " integer primary key autoincrement, " +
                LIBRARY_TITLE + " text, " + LIBRARY_AUTHOR + " text, "+
                LIBRARY_GENRE + " text, " + LIBRARY_YEAR +
                " integer);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }
}
