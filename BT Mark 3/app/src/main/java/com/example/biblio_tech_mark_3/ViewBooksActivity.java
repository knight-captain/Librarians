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

public class ViewBooksActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    public static final String TAG = "!!!ViewBooksActivity!!!";

    MainActivity mainActivity;
    EditText find_input;
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;

    DataBaseHelper dataBaseHelper;

    private EditText titleID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);

        Intent intent = getIntent();

        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Log.i(TAG, message + " Received from Main");

        dataBaseHelper = new DataBaseHelper(this );
        Log.i(TAG,"this is where it breaks. DBH: " + dataBaseHelper);

        Button titlebutton = findViewById(R.id.titlebutton);
        titlebutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {titleSearch(); }
        });
        Button authorbutton = findViewById(R.id.authorbutton);
        authorbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {authorSearch();}
        });
        Button keywordbutton = findViewById(R.id.keywordbutton);
        keywordbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { keywordSearch(); }
        });


        //For testing
//        Book test = new Book(2, "TEST2", "1-234-56890-124-0", 101, false);
//        dataBaseHelper.addOne(test); //TODO not showing up

        //The RecyclerView and its Adapter
        recyclerView = findViewById(R.id.book_info);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                layoutManager.getOrientation());//TODO decoration not working?
//        recyclerView.addItemDecoration(dividerItemDecoration); //TODO decoration not working?


        adapter = new MyRecyclerViewAdapter(this, dataBaseHelper.getAllBooks());
        adapter.setClickListener(this);

        ShowBooksOnRecyclerView();

    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i(TAG, "You clicked " + adapter.getItem(position) + " on row number " + position);
    }

    //This updates the RecyclerView
    private void ShowBooksOnRecyclerView() {
        adapter = new MyRecyclerViewAdapter(this, dataBaseHelper.getAllBooks());
        recyclerView.setAdapter(adapter);
    }

//    public ViewBooksActivity(){
//
//        Log.i(TAG, "view point 1");
//
//        titleID = findViewById(R.id.view_title);
//        String title = titleID.getText().toString();
//        Log.i(TAG, title);
//

//    }

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