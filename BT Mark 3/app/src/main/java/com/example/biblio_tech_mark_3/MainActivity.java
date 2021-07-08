package com.example.biblio_tech_mark_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity: ";
    public static final String EXTRA_MESSAGE = "com.example.biblio_tech_mark_3.MESSAGE";

    DataBaseHelper dataBaseHelper;

    public void viewBooksButton(){
        //this button will take us to the view library screen

        Log.i(TAG, "viewBooksButton");

        Intent intent = new Intent(this, ViewBooksActivity.class);
        intent.putExtra(EXTRA_MESSAGE, String.valueOf(this));

        startActivity(intent);
    }

    public void addBookButton(){
        //this will be the method linked to the "add book" button
        Log.i(TAG, "AddBookButton");
        Intent intent =new Intent(this,AddBooksActivity.class);
        intent.putExtra(EXTRA_MESSAGE, String.valueOf(this));
        startActivity(intent);

    }

    public void manageShelves(){
        //this will be the method linked to the "manage shelves" button
        Log.i(TAG, "ManageShelves");
        Intent intent =new Intent(this,ManageShelvesActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");

        //creates the book picture on the main screen
        ImageView bookPic = (ImageView) findViewById(R.id.bookpic);
        int imageResource = getResources().getIdentifier("@drawable/book",null,this.getPackageName());
        bookPic.setImageResource(imageResource);

        dataBaseHelper = new DataBaseHelper(this);

// create view books button
        Button search_button = findViewById(R.id.viewBooks);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewBooksButton();
            }
        });
// create the add book button
        Button add_book_button = findViewById(R.id.add_book_button);
        add_book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 addBookButton();
            }
        });
// create the manage button
        Button manage_library_button = findViewById(R.id.manage_library);
        manage_library_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageShelves();
            }
        });
    }
}