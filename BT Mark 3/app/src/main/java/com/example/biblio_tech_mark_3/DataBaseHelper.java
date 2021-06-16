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
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "!!!DataBaseHelper activity!!!";

    public static final String BOOK_TABLE = "BOOK_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_BOOK_TITLE = "BOOK_TITLE";
    public static final String COLUMN_ISBN_13 = "ISBN_13";
    public static final String COLUMN_PAGES = "PAGES";
    public static final String COLUMN_OWNED = "OWNED";

    public List<Book> getAllBooks(){
        List<Book> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + BOOK_TABLE; //this is where you construct the SQL query

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);//receive cursor, which is the SQL result "list"

        if (cursor.moveToFirst()){
            //loop through cursor and assign each item it's properties, and turn it into a book
            do {
                int bookID = cursor.getInt(0);
                String bookTitle = cursor.getString(1);
                String bookISBN = cursor.getString(2);
                int bookPages = cursor.getInt(3);
                boolean bookOwned = cursor.getInt(4) == 1 ? false: true;

                Book newBook = new Book(bookID, bookTitle, bookISBN, bookPages, bookOwned);
                returnList.add(newBook);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean addOne(Book book) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BOOK_TITLE, book.getEntries__title());
        cv.put(COLUMN_PAGES, book.getEntries__number_of_pages());
        cv.put(COLUMN_ISBN_13, book.getEntries__works__ISBN());
        cv.put(COLUMN_OWNED, book.isOwned());

        long insert = db.insert(BOOK_TABLE, COLUMN_ID, cv); //nullColumnHack can be set to columns to ignore
        return insert != -1;
    }

    public boolean deleteOne(Book book){

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
        //create new table

        String createTableStatement = "CREATE TABLE " + BOOK_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_BOOK_TITLE + " TEXT, " + COLUMN_ISBN_13 + " TEXT, " + COLUMN_PAGES + " INT, " + COLUMN_OWNED + " BOOL)";

        db.execSQL(createTableStatement);
    }

    //for version upgrades; Forward proofing/backwards compatibility
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public DataBaseHelper(@Nullable Context context) {
        super(context, "LibraryDB", null, 1);//pass these to the parent: SQLiteOpenHelper
        Log.i(TAG,"HERE?");
    }
}
