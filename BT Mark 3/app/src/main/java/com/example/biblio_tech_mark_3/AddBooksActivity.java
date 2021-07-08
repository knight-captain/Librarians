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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class AddBooksActivity extends AppCompatActivity implements AddBooksRecyclerViewAdapter.ItemClickListener{

    EditText text;

    public static final String TAG = "AddBooksActivity: ";

    RecyclerView recyclerView;
    AddBooksRecyclerViewAdapter adapter;

    List<Book> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);

        // don't really need this intent?
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Log.i(TAG, message + " Received from Main");

        resultList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            //add a test book
            //title, author, List genres, List Subjects, int ISBN, longString Description
            List<String> genre = new ArrayList<String>();
            genre.add("Non-Fiction" + i);
            List<String> subjects = new ArrayList<String>();
            subjects.add("Testing" + i);
            Book testBook = new Book(1, "TEST" + i, "unknown" + i, genre, subjects,-1 - i, "This property intentionally left blank"  + i);
            resultList.add(testBook);
        }


        //The RecyclerView and its Adapter
        recyclerView = findViewById(R.id.possibleBooks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//        adapter = new AddBooksRecyclerViewAdapter(this, test);
//        adapter.setClickListener(this);

        adapter = new AddBooksRecyclerViewAdapter(this, resultList);
        adapter.setClickListener(this);


        ShowBooksOnRecyclerView();

        Button addTitleButton = findViewById(R.id.addTitleButton);
        addTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addTitle();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Button addISBNButton = findViewById(R.id.addISBNButton);
        addISBNButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addISBN();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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

        adapter = new AddBooksRecyclerViewAdapter(this, resultList);
        adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);
    }
//    public void addTitle(){
////        adapter = new AddBooksRecyclerViewAdapter(this, resultList);
//        adapter.setClickListener(this);
//
//        recyclerView.setAdapter(adapter);
//    }

    public void addTitle() throws ExecutionException, InterruptedException {

        EditText text = (EditText)findViewById(R.id.addTitle);
        String title = text.getText().toString();
        Log.i(TAG, "You clicked the add title button" + title);
        //todo get info from API
        String author = "Author";
        List<String> genre = Collections.singletonList("Genre");
        List<String> subjects = Collections.singletonList("subjects");

        //create new book
        Book newBook = new Book(1, title, author, genre, subjects,0000000000000, "This property intentionally left blank");
        //  dataBaseHelper.addOne(test);

        //TODO lookup title as work on API and return list to recyclerView

        //TODO grab missing info from other isbns
    }
    public void addISBN() throws ExecutionException, InterruptedException {
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

        Log.i(TAG, "You clicked the add ISBN button" + ISBN);

        //TODO lookup individual book by isbn and return it to the recyclerView
        APIHelper apiH = new APIHelper(null, ISBN);
        FutureTask apiTask = new FutureTask(apiH);
        Thread lookupThread = new Thread(apiTask);
        lookupThread.start();

        resultList = (List<Book>) apiTask.get();


        ShowBooksOnRecyclerView();
        //TODO grab missing info from other isbns
        //TODO Camera grab ISBN
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

    @Override
    public void onItemClick(View view, int position) {
        Log.i(TAG, "You clicked " + adapter.getItem(position) + " on row number " + position);

        Intent intent = new Intent(this,AddBooksManually.class);
        Book clickedBook = adapter.getItem(position);
        String bookInJsonForm = JsonHelper.bookToJson(clickedBook);

        intent.putExtra("bookInJsonForm",bookInJsonForm);

        startActivity(intent);
    }

}