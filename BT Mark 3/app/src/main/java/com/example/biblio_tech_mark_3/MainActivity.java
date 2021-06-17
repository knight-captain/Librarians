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

    DataBaseHelper dataBaseHelper;

    public static void SaveData(Shelf json){
        Log.i(TAG, "This should save the data, but doesn't");
        //TEST COMMENT
    }

    public void LoadData(){
        Log.i(TAG,"making new DBHelper");
//        Loader loader = new Loader();
//        Thread loading = new Thread((Runnable) loader, "loader");
//        loading.start();
        dataBaseHelper = new DataBaseHelper(this);

        Book test = new Book(1, "TEST", "1-234-56890-124-3", 100, false);
        dataBaseHelper.addOne(test);
        Log.i(TAG,"added test book: " + test.toString() + " and helper has " + dataBaseHelper);

//        return dataBaseHelper;
    }

    public void ViewBooksButton(View view){
        //this button will take us to the view library screen
        Log.i(TAG, ", button clicked sending intent to ViewBooks");
        String test = "This is data";

        Intent intent = new Intent(this, ViewBooksActivity.class);
        intent.putExtra(EXTRA_MESSAGE, String.valueOf(this));
//        intent.putExtra("DBH", dataBaseHelper);

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
        Log.i(TAG, "DBhelper " + dataBaseHelper);

    }
}