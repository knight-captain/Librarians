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

public class ViewBooksActivity extends AppCompatActivity implements ViewBooksRecyclerViewAdapter.ItemClickListener {

    public static final String TAG = "!!!ViewBooksActivity!!!";

    RecyclerView recyclerView;
    ViewBooksRecyclerViewAdapter adapter;

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);

        Intent intent = getIntent();

        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Log.i(TAG, message + " Received from Main");

        dataBaseHelper = new DataBaseHelper(this );

        //The RecyclerView and its Adapter
        recyclerView = findViewById(R.id.book_info);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                layoutManager.getOrientation());//TODO decoration not working?
//        recyclerView.addItemDecoration(dividerItemDecoration); //TODO decoration not working?
        adapter = new ViewBooksRecyclerViewAdapter(this, dataBaseHelper);
        adapter.setClickListener(this);

        ShowBooksOnRecyclerView();

        Button titleButton = findViewById(R.id.titlebutton);
        titleButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {titleSearch(); }
        });
        Button authorButton = findViewById(R.id.authorbutton);
        authorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {authorSearch();}
        });
        Button keywordButton = findViewById(R.id.keywordbutton);
        keywordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { keywordSearch(); }
        });
        Button viewFinishButton = findViewById(R.id.viewFinishButton);
        viewFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {finish(); }
        });

    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i(TAG, "You clicked " + adapter.getItem(position) + " on row number " + position);
        dataBaseHelper.deleteOne(adapter.getItem(position));
        ShowBooksOnRecyclerView();
    }

    //This updates the RecyclerView
    public void ShowBooksOnRecyclerView() {
        adapter = new ViewBooksRecyclerViewAdapter(this, dataBaseHelper);
        adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);
    }

    public void titleSearch(){
    // get the title to search for
    EditText text = (EditText)findViewById(R.id.find_input);
    String title = text.getText().toString();
    Log.i(TAG, "You clicked the title button" + title);

        // search the library by title and display results
    }

    public void authorSearch(){
        // get the author to search for
        EditText text = (EditText)findViewById(R.id.find_input);
        String author = text.getText().toString();
        Log.i(TAG, "You clicked the author button" + author);

        // search the library by author and display results
    }
    public void keywordSearch(){
        // get the keyword to search for
        EditText text = (EditText)findViewById(R.id.find_input);
        String keyword = text.getText().toString();
        Log.i(TAG, "You clicked the keyword button" + keyword);

        // search the library by keyword and display results
    }
}