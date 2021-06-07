package com.example.biblio_tech_mark2;

import android.app.Activity;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;

public class Loader extends AppCompatActivity implements Runnable{

    private Activity ma;
    public static final String TAG = "!!!Loader!!!";

    @Override
    public void run() {
        Shelf library = new Shelf("test");


        try {
            Log.i(TAG,"3");
            Shelf readFile = LibraryLoadFromJson.readFile();
            Log.i(TAG,"4");
            Log.i(TAG, String.valueOf(readFile));
            for(Book b : readFile.getBooks()){
                System.out.println(b.getTitle());
            }

//            Intent intent = new Intent(ma, SecondFragment.class);
//            intent.putExtra(TAG, String.valueOf(readFile));
//            startActivity(intent);

        } catch (FileNotFoundException e) {
            Log.i(TAG,"But why???");
            e.printStackTrace();
        }
    }

    public Loader (MainActivity ma){
        this.ma = new Activity();
    } //may need WeakReference
}
