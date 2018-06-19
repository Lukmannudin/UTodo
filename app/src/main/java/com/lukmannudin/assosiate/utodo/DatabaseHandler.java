package com.lukmannudin.assosiate.utodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "UTodo.db";
    // Instantion

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }


    public long insertData(String at, String desc, String date ){
        // Gets  the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        //Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UTodoEntry.COLUMN_NAME_AT, at);
        values.put(UTodoEntry.COLUMN_NAME_DESCRIPTION, desc);
        values.put(UTodoEntry.COLUMN_NAME_DATE, date);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UTodoEntry.TABLE_NAME,null,values);
        return newRowId;
    }

    public ArrayList<Todo> readAllData(){
        SQLiteDatabase db = this.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                UTodoEntry.COLUMN_NAME_AT,
                UTodoEntry.COLUMN_NAME_DESCRIPTION,
                UTodoEntry.COLUMN_NAME_DATE
        };

        Cursor cursor = db.query(
                UTodoEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null );
        ArrayList<Todo> items = new ArrayList();
        Todo todo = null;

        while (cursor.moveToNext()){
            todo = new Todo();
            todo.setToDoAt(cursor.getString(cursor.getColumnIndex(UTodoEntry.COLUMN_NAME_AT)));
            todo.setToDoDesc(cursor.getString(cursor.getColumnIndex(UTodoEntry.COLUMN_NAME_DESCRIPTION)));
            todo.setToDoDate(cursor.getString(cursor.getColumnIndex(UTodoEntry.COLUMN_NAME_DATE)));
         items.add(todo);
        }
        return items;
    }



    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE "+ UTodoEntry.TABLE_NAME + " (" +
                    UTodoEntry._ID + " INTEGER PRIMARY KEY,"+
                    UTodoEntry.COLUMN_NAME_AT + " TEXT,"+
                    UTodoEntry.COLUMN_NAME_DESCRIPTION + " TEXT,"+
                    UTodoEntry.COLUMN_NAME_DATE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UTodoEntry.TABLE_NAME;

        /* Inner class that defines the table contents */
        public static class UTodoEntry implements BaseColumns {
            public static final String TABLE_NAME = "UTodoTable";
            public static final String COLUMN_NAME_AT = "At";
            public static final String COLUMN_NAME_DESCRIPTION = "Description";
            public static final String COLUMN_NAME_DATE = "Date";

        }

}
