package simone.it.esame.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import simone.it.esame.Models.Note;

/**
 * Created by Simone on 16/03/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String COLUMN_NAME_ID ="ID";
    private static final String COLUMN_NAME_TITLE = "title";
    private static final String COLUMN_NAME_TEXT = "text";
    private static final String COLUMN_NAME_DATE = "date";
    private static final String COLUMN_NAME_COLOR = "color";
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Note.db";
    public static final String TABLE_NAME = "Note";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+ DatabaseHandler.TABLE_NAME+ "("+ DatabaseHandler.COLUMN_NAME_ID + " INTEGER PRIMARY KEY, " +
            DatabaseHandler.COLUMN_NAME_TITLE + " TEXT,"+ DatabaseHandler.COLUMN_NAME_TEXT + " TEXT," + DatabaseHandler.COLUMN_NAME_DATE + " TEXT," + DatabaseHandler.COLUMN_NAME_COLOR + " INTEGER)";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DatabaseHandler.TABLE_NAME;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addNote (Note Note){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE, Note.getTitle());
        values.put(COLUMN_NAME_TEXT, Note.getText());
        values.put(COLUMN_NAME_DATE, Note.getDate());
        values.put(COLUMN_NAME_COLOR, Note.getColor());

        long i=db.insert(DatabaseHandler.TABLE_NAME, null, values);
        Note.setId((int)i);
        db.close();
    }

    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> notesList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
                note.setText(cursor.getString(2));
                note.setDate(cursor.getString(3));
                note.setColor(cursor.getInt(4));
                notesList.add(note);
            } while (cursor.moveToNext());
        }

        return notesList;
    }

    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE, note.getTitle());
        values.put(COLUMN_NAME_TEXT, note.getText());
        values.put(COLUMN_NAME_DATE, note.getDate());
        values.put(COLUMN_NAME_COLOR, note.getColor());
        return db.update(TABLE_NAME, values, COLUMN_NAME_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_NAME_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }

    public ArrayList<Note> getSearchNotes(CharSequence s) {
        ArrayList<Note> notesList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_TITLE + " LIKE '" + s + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Note Note = new Note();
                Note.setId(Integer.parseInt(cursor.getString(0)));
                Note.setTitle(cursor.getString(1));
                Note.setText(cursor.getString(2));
                Note.setDate(cursor.getString(3));
                Note.setColor(cursor.getInt(4));
                notesList.add(Note);
            } while (cursor.moveToNext());
        }

        return notesList;
    }

    public ArrayList<Note> getOrderDescNotes() {
        ArrayList<Note> NotesList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_NAME_TITLE + " DESC ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Note Note = new Note();
                Note.setId(Integer.parseInt(cursor.getString(0)));
                Note.setTitle(cursor.getString(1));
                Note.setText(cursor.getString(2));
                Note.setDate(cursor.getString(3));
                Note.setColor(cursor.getInt(4));
                NotesList.add(Note);
            } while (cursor.moveToNext());
        }

        return NotesList;
    }

    public ArrayList<Note> getOrderAscNotes() {
        ArrayList<Note> notesList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_NAME_TITLE + " ASC ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Note Note = new Note();
                Note.setId(Integer.parseInt(cursor.getString(0)));
                Note.setTitle(cursor.getString(1));
                Note.setText(cursor.getString(2));
                Note.setDate(cursor.getString(3));
                Note.setColor(cursor.getInt(4));
                notesList.add(Note);
            } while (cursor.moveToNext());
        }

        return notesList;
    }
}
