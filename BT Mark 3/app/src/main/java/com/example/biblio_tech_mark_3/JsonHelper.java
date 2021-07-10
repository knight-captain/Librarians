package com.example.biblio_tech_mark_3;

import android.util.Log;

import com.google.gson.Gson;

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
        Book bookInBookForm;
        Gson gson = new Gson();
        bookInBookForm = gson.fromJson(json, Book.class);
        return bookInBookForm;
    }
}
