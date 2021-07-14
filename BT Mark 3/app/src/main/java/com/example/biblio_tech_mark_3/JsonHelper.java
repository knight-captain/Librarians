package com.example.biblio_tech_mark_3;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonHelper {

    public static final String TAG = "JsonHelper";

    public static String bookToJson(Book book){
        Log.i(TAG, "bookToJson");
        String bookInJsonForm;
        Gson gson = new Gson();
        bookInJsonForm = gson.toJson(book, Book.class);
        return bookInJsonForm;
    }

    public static Book jsonToBook(String json){
        Log.i(TAG, "jsonToBook");
        Book bookInBookForm = null;
        Gson gson = new Gson();
        try {
            bookInBookForm = gson.fromJson(json, Book.class);
            return bookInBookForm;
        } catch (IllegalStateException e){
            bookInBookForm.setTitle("This book did not load properly!");
            return bookInBookForm;
        } catch (JsonSyntaxException e){
            bookInBookForm.setTitle("This book did not load properly!");
            return bookInBookForm;
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
