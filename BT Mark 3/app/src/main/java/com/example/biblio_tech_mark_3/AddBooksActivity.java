package com.example.biblio_tech_mark_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.ContentValues.TAG;

public class AddBooksActivity extends AppCompatActivity {
    EditText text;

    public static final String TAG = "!!!AddBooksActivity!!!";

    RecyclerView recyclerView;
    ViewBooksRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);

        Intent intent = getIntent();
        //add title button
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
    }

    public void addTitle(){
        EditText text = (EditText)findViewById(R.id.addTitle);
        String title = text.getText().toString();
        Log.i(TAG, "You clicked the add title button" + title);

        //add book to database
    }
    public void addISBN(){
        EditText text = (EditText)findViewById(R.id.addISBN);
        String ISBN = text.getText().toString();
        Log.i(TAG, "You clicked the add title button" + ISBN);

        //add book to database
    }
    public void addManual(){
        Log.i(TAG, "You clicked the add manual button");
        //open add manual activity
        Intent intent =new Intent(this,AddBooksManually.class);
        startActivity(intent);
    }
}