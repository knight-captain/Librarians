package com.example.biblio_tech_mark_3;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JsonHelper {
/* This reads in the JSON from the API and gets into a our book class. */
    public static final String TAG = "JsonHelper";

    public static String bookToJson(BookOld book){
        //TODO change to new Book
        Log.i(TAG, "bookToJson");
        String bookInJsonForm;
        Gson gson = new Gson();
        bookInJsonForm = gson.toJson(book, BookOld.class);
        return bookInJsonForm;
    }

    public static BookOld jsonToBook(String json){
        //TODO change to new Book
        Log.i(TAG, "jsonToBook");
        //TODO change to new Book
        BookOld bookInBookForm = null;

        Gson gson = new Gson();
        try {
            bookInBookForm = gson.fromJson(json, BookOld.class);
            Log.i(TAG,bookInBookForm.getTitle());
            return bookInBookForm;
        } catch (IllegalStateException e){
//            bookInBookForm.setTitle("This book did not load properly!");
            return null;
        } catch (JsonSyntaxException e){
//            bookInBookForm.setTitle("This book did not load properly!");
            return null;
        }
    }

    public static Author jsonToAuthor(String json){
        Log.i(TAG, "jsonToAuthor");
        Author author;
        Gson gson = new Gson();
        author = gson.fromJson(json, Author.class);
        return author;
    }
}
