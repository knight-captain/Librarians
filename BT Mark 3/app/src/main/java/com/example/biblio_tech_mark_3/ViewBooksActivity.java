package com.example.biblio_tech_mark_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ViewBooksActivity extends AppCompatActivity {

    public static final String TAG = "!!!ViewBooksActivity!!!";

    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Log.i(TAG, message);



    }
}