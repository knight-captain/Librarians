package com.example.biblio_tech_mark_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;

public class AddBooksManually extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books_manually);

        Intent intent = getIntent();

        Button manualbutton = findViewById(R.id.manualButton);
        manualbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {addBookManually(); }
        });

        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void addBookManually(){
        EditText ettitle = (EditText)findViewById(R.id.titleManual);
        String title = ettitle.getText().toString();

        EditText etauthor = (EditText)findViewById(R.id.authorManual);
        String author = etauthor.getText().toString();

        EditText etgenre = (EditText)findViewById(R.id.genreManual);
        List<String> genre = Collections.singletonList(etgenre.getText().toString());
        List<String> subjects = Collections.singletonList("subject");
        //create new book
            Book newBook = new Book(1, title, author, genre, subjects,0000000000000, "This property intentionally left blank");
      //  dataBaseHelper.addOne(test);
        //add to database
        Log.i(TAG, "You added this book" + title + " " + author + " " + genre);
        finish();
    }
}