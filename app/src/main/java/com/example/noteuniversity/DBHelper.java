package com.example.noteuniversity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DB = "CREATE TABLE " + Constants.TABLE_NAME + " ( " +
                Constants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                Constants.COLUMN_TITLE + " TEXT , " +
                Constants.COLUMN_TEXT + " TEXT , " +
                Constants.COLUMN_DATE + " TEXT " + " ) ";

        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_DB = "DROP TABLE IF EXISTS " + Constants.TABLE_NAME;
        db.execSQL(DROP_DB);
        onCreate(db);
    }

    public void insertNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.COLUMN_TITLE, note.getTitle());
        contentValues.put(Constants.COLUMN_TEXT, note.getText());
        contentValues.put(Constants.COLUMN_DATE, note.getTextDate());
        db.insert(Constants.TABLE_NAME, null, contentValues);
        db.close();
    }

    public ArrayList<Note> getNote() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Note> notes = new ArrayList<>();

        String GET_NOTE = "SELECT * FROM " + Constants.TABLE_NAME;
        Cursor cursor = db.rawQuery(GET_NOTE, null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                notes.add(new Note(cursor.getString(1), cursor.getString(2),
                        cursor.getString(3)));
                cursor.moveToNext();
            }
        }
        return notes;
    }

    public void updateNote(Note searchNote,Note updateNote){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(Constants.COLUMN_TITLE,updateNote.getTitle());
        contentValues.put(Constants.COLUMN_TEXT,updateNote.getText());
        contentValues.put(Constants.COLUMN_DATE,updateNote.getTextDate());

        db.update(Constants.TABLE_NAME,contentValues,Constants.COLUMN_TITLE+" =? AND "+
                Constants.COLUMN_TEXT+" =? AND "+ Constants.COLUMN_DATE+" = ? "
                ,new String[]{searchNote.getTitle(),searchNote.getText()
                ,searchNote.getTextDate()});


    }

    public void deleteNote(Note note){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(Constants.TABLE_NAME,Constants.COLUMN_TITLE+" =? AND "+ Constants.COLUMN_TEXT+" =? AND "+
                Constants.COLUMN_DATE+" = ?  ",new String[]{note.getTitle(),note.getText(),note.getTextDate()});
    }

}
