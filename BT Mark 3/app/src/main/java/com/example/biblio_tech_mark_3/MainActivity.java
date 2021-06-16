package com.example.biblio_tech_mark_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "!!!Main activity!!!";
    public static final String EXTRA_MESSAGE = "com.example.biblio_tech_mark_3.MESSAGE";


    public static void SaveData(Shelf json){
        Log.i(TAG, "This should save the data, but doesn't");
        //TEST COMMENT
    }

    public static void LoadData(){
        Log.i(TAG,"Starting loader and Thread");
        Loader loader = new Loader();
        Thread loading = new Thread((Runnable) loader, "loader");
        loading.start();
    }

    public void ViewBooksButton(View view){
        //this button will take us to the view library screen
        Log.i(TAG, "This should take you to your library, but doesn't");
        String test = "This is data";

        Intent intent = new Intent(this, ViewBooksActivity.class);
        intent.putExtra(EXTRA_MESSAGE,test);
        startActivity(intent);
    }

    public void AddBookButton(){
        //this will be the method linked to the "add book" button
        Log.i(TAG, "This should let you add a book to your library, but doesn't");
    }

    public void ManageShelves(){
        //this will be the method linked to the "manage shelves" button
        Log.i(TAG, "This should let you manage your shelves, but doesn't");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadData();

    }
}