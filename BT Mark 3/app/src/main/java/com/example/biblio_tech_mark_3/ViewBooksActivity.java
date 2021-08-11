package com.example.biblio_tech_mark_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/* This is our search or view books already in our library. It can be searched by title, author or keyword.
   When the user enters the screen the database is placed in a recycle view to be scrolled through.
 */
public class ViewBooksActivity extends AppCompatActivity implements ViewBooksRecyclerViewAdapter.ItemClickListener {

    public static final String TAG = "ViewBooksActivity: ";

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


        //This is where it crashes
        showBooksOnRecyclerView(); //needs this to "update" the Rcyclerview the first time

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
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i(TAG, "You clicked " + adapter.getItem(position) + " on row number " + position);
//        dataBaseHelper.deleteOne(adapter.getItem(position));
        showBooksOnRecyclerView();
    }

    //This updates the RecyclerView
    public void showBooksOnRecyclerView() {
        adapter = new ViewBooksRecyclerViewAdapter(this, dataBaseHelper);
        adapter.setClickListener(this);

        new ItemTouchHelper(iTHCallback).attachToRecyclerView(recyclerView);

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

    //from https://youtu.be/M1XEqqo6Ktg
    //Make the new object, and it needs parameters: drag directions & swipe directions. Should autopopuulate methods
    ItemTouchHelper.SimpleCallback iTHCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        //ignore this one
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }
        //Make sure recycerView is put in here
        public void onSwiped( RecyclerView.ViewHolder recyclerView, int direction) {
            Log.i(TAG, "You clicked " + adapter.getItem(recyclerView.getAdapterPosition()) + " on row number " + recyclerView.getAdapterPosition());
            //get your data, then invoke the delete method, passing in the item
            dataBaseHelper.deleteOne(adapter.getItem(recyclerView.getAdapterPosition()));
            adapter.notifyDataSetChanged(); //not sure why this is needed?

            showBooksOnRecyclerView(); //as always, update the view
        }
    };
}