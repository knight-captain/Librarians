package com.example.biblio_tech_mark_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import static android.content.ContentValues.TAG;

public class AddBooksManually extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books_manually);

        Intent intent = getIntent();

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
        String genre = etgenre.getText().toString();

        Log.i(TAG, "You added this book" + title + " " + author + " " + genre);

    }
}