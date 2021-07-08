package com.example.biblio_tech_mark_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddBooksActivity extends AppCompatActivity {
    EditText text;

    public static final String TAG = "!!!AddBooksActivity!!!";

    RecyclerView recyclerView;
    ViewBooksRecyclerViewAdapter adapter;
    List<Book> test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Log.i(TAG, message + " Received from Main");

        test = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            //add a test book
            //title, author, List genres, List Subjects, int ISBN, longString Description
            List<String> genre = new ArrayList<String>();
            genre.add("Non-Fiction" + i);
            List<String> subjects = new ArrayList<String>();
            subjects.add("Testing" + i);
            Book testBook = new Book(1, "TEST" + i, "unknown" + i, genre, subjects,-1 - i, "This property intentionally left blank"  + i);
            test.add(testBook);
        }

        //The RecyclerView and its Adapter
        recyclerView = findViewById(R.id.possibleBooks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        adapter = new AddBooksRecyclerViewAdapter(this, test);
//        adapter.setClickListener(this);

        ShowBooksOnRecyclerView();

        Button addTitleButton = findViewById(R.id.addTitleButton);
        addTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTitle(); }
        });
        Button addISBNButton = findViewById(R.id.addISBNButton);
        addISBNButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {addISBN(); }
        });
        Button addManualButton = findViewById(R.id.addManualButton);
        addManualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {addManual(); }
        });
        Button doneButton = findViewById(R.id.manageFinishButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    //This updates the RecyclerView
    public void ShowBooksOnRecyclerView() {
//        adapter = new AddBooksRecyclerViewAdapter(this, test);
//        adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);
    }
    public void addTitle(){
        EditText text = (EditText)findViewById(R.id.addTitle);
        String title = text.getText().toString();
        Log.i(TAG, "You clicked the add title button" + title);
        //get info from API
        String author = "Author";
        List<String> genre = Collections.singletonList("Genre");
        List<String> subjects = Collections.singletonList("subjects");

        //create new book
        Book newBook = new Book(1, title, author, genre, subjects,0000000000000, "This property intentionally left blank");
        //  dataBaseHelper.addOne(test);

        //add book to database
    }
    public void addISBN(){
        EditText text = (EditText)findViewById(R.id.addISBN);
        String ISBN = text.getText().toString();
        Log.i(TAG, "You clicked the add title button" + ISBN);
        //get info from API
        String title = "Title";
        String author = "Author";
        List<String> genre = Collections.singletonList("Genre");
        List<String> subjects = Collections.singletonList("subjects");

        //create new book
                Book newBook = new Book(1, title, author, genre, subjects,0000000000000, "This property intentionally left blank");
        //  dataBaseHelper.addOne(test);

        //add book to database
    }
    public void addManual(){
        Log.i(TAG, "You clicked the add manual button");
        //open add manual activity
        Intent intent = new Intent(this,AddBooksManually.class);

        Book blankBook = new Book(-1,"Unknown",null,null,null,-1,"This field intentionally left blank");

        String bookInJsonForm = JsonHelper.bookToJson(blankBook);
        intent.putExtra("bookInJsonForm",bookInJsonForm);

        startActivity(intent);
    }
//    @Override
//    public void onItemClick(View view, int position) {
//        Log.i(TAG, "You clicked " + adapter.getItem(position) + " on row number " + position);
//
//        Intent intent = new Intent(this,AddBooksManually.class);
//        Book clickedBook = adapter.getItem(position);
//        String bookInJsonForm = JsonHelper.bookToJson(clickedBook);
//        intent.putExtra("bookInJsonForm",bookInJsonForm);
//
//        startActivity(intent);
//    }
}