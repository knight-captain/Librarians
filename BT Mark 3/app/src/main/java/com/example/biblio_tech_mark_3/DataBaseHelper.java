package com.example.biblio_tech_mark_3;

/*From: https://www.youtube.com/watch?v=312RhjfetP8&t=3108s*/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "DataBaseHelper: ";

    //title, author, List genres, List Subjects, int ISBN, longString Description
    public static final String BOOK_TABLE = "BOOK_TABLE";

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_BOOK_TITLE = "BOOK_TITLE";
    public static final String COLUMN_AUTHOR = "AUTHOR";
    public static final String COLUMN_BOOK_GENRE = "BOOK_GENRE";
    public static final String COLUMN_BOOK_SUBJECTS = "BOOK_SUBJECTS";
    public static final String COLUMN_ISBN_13 = "ISBN_13";
    public static final String COLUMN_NOTES = "NOTES";
//    public static final String COLUMN_PAGES = "PAGES";
//    public static final String COLUMN_OWNED = "OWNED";

    public List<BookOld> getAllBooks(){
        List<BookOld> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + BOOK_TABLE; //this is where you construct the SQL query

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);//receive cursor, which is the SQL result "list"

        if (cursor.moveToFirst()){
            //loop through cursor and assign each item it's properties, and turn it into a book
            Log.i(TAG,"Starting cursor loop.");
            //TODO make new Book
            do {
                //title, author, List genres, List Subjects, int ISBN, longString Description
                int bookID = cursor.getInt(0);
                String bookTitle = cursor.getString(1);
                Author bookAuthor = new Author( cursor.getString(2));
                List<String> bookGenres = Arrays.asList(cursor.getString(3));
                List<String> bookSubjects = Arrays.asList(cursor.getString(4));
                long bookISBN = cursor.getInt(5);
                String bookNotes = cursor.getString(6);

                BookOld newBook = new BookOld(bookID, bookTitle, bookAuthor, bookGenres, bookSubjects, bookISBN, bookNotes);
                returnList.add(newBook);

            } while (cursor.moveToNext());
            Log.i(TAG, "cursor: " + returnList);
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean addOne(BookOld book) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //TODO make new Book
        //title, author, List genres, List Subjects, int ISBN, longString Description
        cv.put(COLUMN_BOOK_TITLE, book.getTitle());
        cv.put(COLUMN_AUTHOR, book.getAuthorName());
        cv.put(COLUMN_BOOK_GENRE, String.valueOf(book.getGenres()));
        cv.put(COLUMN_BOOK_SUBJECTS, String.valueOf(book.getSubjects()));
        cv.put(COLUMN_ISBN_13, book.getISBN());
        cv.put(COLUMN_NOTES, book.getNotes());

        long insert = db.insert(BOOK_TABLE, COLUMN_ID, cv); //nullColumnHack can be set to columns to ignore
        return insert != -1;
    }

    public boolean deleteOne(BookOld book){

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + BOOK_TABLE + " WHERE " + COLUMN_ID + " = " + book.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    //Called first time DB is accessed
    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO make new Book
        //create new table
        Log.i(TAG,"Creating DB (if there isn't one?)" );
        String createTableStatement = "CREATE TABLE " + BOOK_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_BOOK_TITLE + " TEXT, " + COLUMN_AUTHOR + " TEXT, " + COLUMN_BOOK_GENRE + " TEXT, " + COLUMN_BOOK_SUBJECTS + " ARRAY, " + COLUMN_ISBN_13 + " BIGINT, " + COLUMN_NOTES + " TEXT)";

        db.execSQL(createTableStatement);
    }

    //for version upgrades; Forward proofing/backwards compatibility
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public DataBaseHelper(@Nullable Context context) {
        super(context, "LibraryDB", null, 1);//pass these to the parent: SQLiteOpenHelper
        Log.i(TAG, String.valueOf(context));
    }
}
