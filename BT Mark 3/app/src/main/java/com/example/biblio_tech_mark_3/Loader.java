package com.example.biblio_tech_mark_3;

//TODO Currently not bing used (DELETE?)


import android.app.Activity;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;

public class Loader extends AppCompatActivity implements Runnable{

    private Activity ma;
    public static final String TAG = "!!!Loader!!!";

    @Override
    public void run() {
    //DataBaseHelper kinda took over this class's job. Will prolly delete it later

        Shelf library = new Shelf("test");

        // Gets temp library from LibraryLoadFromJson.
        // TODO should get internal data instead.

        Log.i(TAG,"Headed to LibraryLoadFromJson for data");
        Shelf readFile = LibraryLoadFromJson.readFile();

        Log.i(TAG, "file" + String.valueOf(readFile));

        for(Book b : readFile.getBooks()){
            System.out.println(b.getTitle());
        }

    }

}
