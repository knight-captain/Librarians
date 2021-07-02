package com.example.biblio_tech_mark_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class AddBooksManually extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books_manually);

        Intent intent = getIntent();

        dataBaseHelper = new DataBaseHelper(this);



        Button manualbutton = findViewById(R.id.manualButon);
        manualbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {addBookManually(); }
        });

        //add book to database
    }

    public void addBookManually(){
        EditText ettitle = (EditText)findViewById(R.id.titleManual);
        String title = ettitle.getText().toString();

        EditText etauthor = (EditText)findViewById(R.id.authorManual);
        String author = etauthor.getText().toString();

        EditText etgenre = (EditText)findViewById(R.id.genreManual);
        String firstGenre = etgenre.getText().toString();

        //add a test book
        //title, author, List genres, List Subjects, int ISBN, longString Description
        List<String> genre = new ArrayList<String>();
        genre.add(firstGenre);
        List<String> subjects = new ArrayList<String>();
        genre.add("Testing");
        Book test = new Book(1, title, author, genre, subjects,-1, "This book was made by the AddBooks button");
        dataBaseHelper.addOne(test);
        Log.i(TAG,"added test book: " + test.toString() + " and helper has " + dataBaseHelper);

        Log.i(TAG, "You added this book" + title + " " + author + " " + genre);

    }
}