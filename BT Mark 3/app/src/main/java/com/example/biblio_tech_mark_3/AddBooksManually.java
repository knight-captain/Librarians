package com.example.biblio_tech_mark_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddBooksManually extends AppCompatActivity {

    public static final String TAG = "AddBooksManually";

    DataBaseHelper dataBaseHelper;
    Book book;

    EditText titleTV;
    EditText authorTV;
    EditText genreTV;
    EditText subjectTV;
    EditText isbnTV;
    EditText notesTV;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books_manually);
        dataBaseHelper = new DataBaseHelper(this);

        Intent intent = getIntent();
        String bookInJsonForm = intent.getStringExtra("bookInJsonForm");
        book = JsonHelper.jsonToBook(bookInJsonForm);

        Log.i(TAG, book.getTitle() + " " + book.getAuthor() + " " + book.getGenres() + " " + book.getSubjects() + " " + book.getISBN() + " " + book.getNotes());

        //TODO if book ISBN = -1, then leave stuff blank, otherwise fill in the entry fields with the passed book's info: this could be from AddBooks or from editing a book from the View books.
        if(book.getTitle() != null) {
            titleTV = (EditText)findViewById(R.id.titleManual);
            titleTV.setText(book.getTitle()/*, TextView.BufferType.EDITABLE*/);
        }
        if(book.getAuthor() != null) {
            authorTV = (EditText)findViewById(R.id.authorManual);
            authorTV.setText(book.getAuthor());
        }
        if(book.getGenres() != null) {
            genreTV = (EditText)findViewById(R.id.genreManual);
            genreTV.setText(book.getGenres().toString());
        }

        // Does not like the EditText wants to be a TextField
        if(book.getSubjects() != null) {
            subjectTV = (EditText)findViewById(R.id.subjectsManual);
            subjectTV.setText(book.getSubjects().toString());
        }

        // Error about int can't be a null -> needed to be an int = -1
        if(book.getISBN() != -1) {
            isbnTV = (EditText)findViewById(R.id.isbnManual);
            isbnTV.setText(Integer.toString( book.getISBN() ));
        }

        if(book.getNotes() != null) {
            notesTV = (EditText)findViewById(R.id.descriptionManual);
            notesTV.setText(String.valueOf( book.getNotes() ));
        }


        Button manualButton = findViewById(R.id.manualButton);
        manualButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { addBookManually(); }
        });

        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

//        Button manualButton = findViewById(R.id.manualButton);
//        manualButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {addBookManually(); }
//        });

    }

    public void addBookManually(){
        titleTV = (EditText) findViewById(R.id.titleManual);
        String title = titleTV.getText().toString();

        authorTV = (EditText) findViewById(R.id.authorManual);
        String authorName = authorTV.getText().toString();

        genreTV = (EditText) findViewById(R.id.genreManual);
        String genre = genreTV.getText().toString();
        List<String> genres = new ArrayList<>();

        subjectTV = (EditText) findViewById(R.id.subjectsManual);
        String subject = subjectTV.getText().toString();
        List<String> subjects = new ArrayList<>();

        isbnTV = (EditText)findViewById(R.id.isbnManual);
        int ISBN = Integer.parseInt(isbnTV.getText().toString() ); //TODO ISBN's are too big for Int!

        notesTV = (EditText)findViewById(R.id.descriptionManual);
        String note = notesTV.getText().toString();

        //add a test book
        book.setTitle(title);
        book.setAuthor(authorName);

        book.setGenres(genres);
        book.addGenre(genre);
        book.setSubjects(subjects);
        book.addSubject(subject);

        book.setISBN(ISBN);
        book.setNotes(note);

        dataBaseHelper.addOne(book);
        Log.i(TAG,"added test book: " + book.toString() + " and helper has " + dataBaseHelper);

        finish();

    }
}