package com.example.biblio_tech_mark2;

import android.util.Log;

import com.google.gson.Gson;

import java.io.*;

public class LibraryLoadFromJson {
    private static String librarySaveData = "ShortLibrary";
    public static final String TAG = "TESING STUFF!!!!!!!!!";

    public static Shelf readFile() throws FileNotFoundException {
        //reads the CSV and adds each line to an array
        Shelf json = null;

        Gson gson = new Gson();
        Log.i(TAG,"3");
        try {
            BufferedReader br = new BufferedReader(new FileReader(librarySaveData)); //TODO FIX

            //convert the json string back to object
            json = gson.fromJson(br, Shelf.class);
            Log.i(TAG, "yo");

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG,"4");
        return json;
    }
}
