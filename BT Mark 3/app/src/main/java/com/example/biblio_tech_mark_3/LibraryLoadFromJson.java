package com.example.biblio_tech_mark_3;

//TODO Currently not bing used. Will prolly be used once to import the library from Google Sheet/TSV

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.*;

public class LibraryLoadFromJson {
    //    private static String librarySaveData = "ShortLibrary";// fix to Android
    //using a string instead
    public static String testJson = "";
    public static final String TAG = "LibraryLoadFromJson: ";

    public static Shelf readFile() {

        Shelf json = null;

        Gson gson = new Gson();
        json = gson.fromJson(testJson, Shelf.class);

        //TODO Below needs to be fixed to android
        //SQLite: https://developer.android.com/training/data-storage/sqlite
        Log.i(TAG,"this should save this file onto the device...");
        MainActivity.SaveData(json);

        Log.i(TAG,"returning json to Loader");
        return json;
    }
}
