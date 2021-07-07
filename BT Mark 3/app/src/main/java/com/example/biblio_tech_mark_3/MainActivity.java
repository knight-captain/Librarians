package com.example.biblio_tech_mark_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "!!!Main activity!!!";
    public static final String EXTRA_MESSAGE = "com.example.biblio_tech_mark_3.MESSAGE";

    DataBaseHelper dataBaseHelper;
    
    public static void SaveData(Shelf json){
        Log.i(TAG, "This should save the data, but doesn't");
        //TODO delete section
    }

    public void LoadData(){
        Log.i(TAG,"making new DBHelper");
//        TODO delete loader class
//        Loader loader = new Loader();
//        Thread loading = new Thread((Runnable) loader, "loader");
//        loading.start();
        dataBaseHelper = new DataBaseHelper(this);

//        //add a test book
//        //title, author, List genres, List Subjects, int ISBN, longString Description
//        List<String> genre = new ArrayList<String>();
//        genre.add("Non-Fiction");
//        List<String> subjects = new ArrayList<String>();
//        genre.add("Testing");
//        Book test = new Book(1, "TEST", "unknown", genre, subjects,-1, "This property intentionally left blank");
//        dataBaseHelper.addOne(test);
//        Log.i(TAG,"added test book: " + test.toString() + " and helper has " + dataBaseHelper);

//        return dataBaseHelper;
    }

    public void ViewBooksButton(){
        //this button will take us to the view library screen

        Log.i(TAG, "button clicked sending intent to ViewBooks");

        Intent intent = new Intent(this, ViewBooksActivity.class);
        intent.putExtra(EXTRA_MESSAGE, String.valueOf(this));

        startActivity(intent);

    }

    public void AddBookButton(){
        //this will be the method linked to the "add book" button
        Log.i(TAG, "This takes you to the add book screen");
        Intent intent =new Intent(this,AddBooksActivity.class);
        intent.putExtra(EXTRA_MESSAGE, String.valueOf(this));
        startActivity(intent);

    }

    public void ManageShelves(){
        //this will be the method linked to the "manage shelves" button
        Log.i(TAG, "This takes you to manage library");
        Intent intent =new Intent(this,ManageShelvesActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //creates the book picture on the main screen
        ImageView bookPic = (ImageView) findViewById(R.id.bookpic);
        int imageResource = getResources().getIdentifier("@drawable/book",null,this.getPackageName());
        bookPic.setImageResource(imageResource);

        LoadData();
        Log.i(TAG, "DBhelper " + dataBaseHelper);

// create view books button
        Button search_button = findViewById(R.id.viewBooks);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewBooksButton();
            }
        });
// create the add book button
        Button add_book_button = findViewById(R.id.add_book_button);
        add_book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 AddBookButton();
            }
        });

// create the manage button
        Button manage_library_button = findViewById(R.id.manage_library);
        manage_library_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageShelves();
            }
        });
    }
}