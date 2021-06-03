package com.example.biblio_tech_mark2;

import android.util.Log;

import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class Loader implements Runnable{

    private MainActivity ma;
    public static final String TAG = "TESING STUFF!!!!!!!!!";

    @Override
    public void run() {
        Shelf library = new Shelf("test");

        Log.i(TAG,"2");

        QuickSave.writeFileOnInternalStorage(ma.getApplicationContext());

        try {
            Shelf readFile = LibraryLoadFromJson.readFile();
            Log.i(TAG, String.valueOf(readFile));
            for(Book b : readFile.getBooks()){
                System.out.println(b.getTitle());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i(TAG,"But why???");

        }
    }

    public Loader (MainActivity ma){
        this.ma = new MainActivity();
    } //may need WeakReference
}
