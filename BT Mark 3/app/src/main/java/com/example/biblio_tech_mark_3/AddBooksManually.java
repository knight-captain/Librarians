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

    public static final String TAG = "!!!AddBooksManually!!!";

    DataBaseHelper dataBaseHelper;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books_manually);

        Intent intent = getIntent();
        String bookInJsonForm = intent.getStringExtra("bookInJsonForm");
        book = JsonHelper.jsonToBook(bookInJsonForm);
        Log.i(TAG, book.toString());

        //TODO if book ISBN = -1, then leave stuff blank, otherwise fill in the entry fields with the passed book's info: this could be from AddBooks or from editing a book from the View books.

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
        List<String> genres = new ArrayList<>();
        genres.add(firstGenre);

        //TODO Subject field, like
        List<String> subjects = new ArrayList<>();

        //TODO ISBN field (with camera lookup!)

        //TODO notes field

        //add a test book
        //title, author, List genres, List Subjects, int ISBN, longString Description
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenres(genres);
        book.setSubjects(subjects);
        book.addSubject("test");
        book.setISBN(-1);
        book.setNotes("");

        dataBaseHelper.addOne(book);
        Log.i(TAG,"added test book: " + book.toString() + " and helper has " + dataBaseHelper);

    }
}