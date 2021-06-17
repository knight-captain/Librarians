package com.example.biblio_tech_mark_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class ViewBooksActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    public static final String TAG = "!!!ViewBooksActivity!!!";

    MainActivity mainActivity;

    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Log.i(TAG, message + " Received from Main");

        dataBaseHelper = new DataBaseHelper(this );
        Log.i(TAG,"this is where it breaks. DBH: " + dataBaseHelper);

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

    public ViewBooksActivity(){

    }
}