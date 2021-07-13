package com.example.biblio_tech_mark_3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class AddBooksActivity extends AppCompatActivity implements AddBooksRecyclerViewAdapter.ItemClickListener{

    EditText text;
    Button addISBNbutton;
    String ISBN;

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
            Book testBook = new Book(1, "TEST" + i, new Author ("/authors/OL34184A"), genre, subjects,1000000000000l + i, "This property intentionally left blank"  + i);
            resultList.add(testBook);
        }


        //The RecyclerView and its Adapter
        recyclerView = findViewById(R.id.possibleBooks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        showBooksOnRecyclerView();

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
            public void onClick(View v) { finish(); }
        });
    }

    //This updates the RecyclerView
    public void showBooksOnRecyclerView() {

        adapter = new AddBooksRecyclerViewAdapter(this, resultList);
        adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);
    }

    public void addManual(){
        Log.i(TAG, "You clicked the add manual button");
        //open add manual activity
        Intent intent = new Intent(this,AddBooksManually.class);

        Book blankBook = new Book(-1,null, new Author(null),null,null, 9780671504397l,"This field intentionally left blank");

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

    public void addTitle() throws ExecutionException, InterruptedException {

        EditText text = (EditText) findViewById(R.id.addTitle);
        String title = text.getText().toString();
        Log.i(TAG, "You clicked the add title button" + title);
        //todo get info from API

        //TODO lookup title as work on API and return list to recyclerView
        APIHelper apiH = new APIHelper( title,"9780140328721");
        FutureTask apiTask = new FutureTask(apiH);
        Thread lookupThread = new Thread(apiTask);
        lookupThread.start();

        resultList = (List<Book>) apiTask.get();
        showBooksOnRecyclerView();

        //TODO grab missing info from other isbns
    }

    public void addISBN() throws ExecutionException, InterruptedException {

        //scanner code
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();

        //get info from API
        Log.i(TAG, "You clicked the add ISBN button" + ISBN);

        //TODO lookup individual book by isbn and return it to the recyclerView
        APIHelper apiH = new APIHelper(null, ISBN);
        FutureTask apiTask = new FutureTask(apiH);
        Thread lookupThread = new Thread(apiTask);
        lookupThread.start();

        resultList = (List<Book>) apiTask.get();

        showBooksOnRecyclerView();
        //TODO grab missing info from other isbns
        //TODO Camera grab ISBN
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode,data);
        if (result != null){
            if(result.getContents() != null){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
//               ISBN =result.getContents();
//                Log.i(TAG, "The number you scanned " + ISBN);
                builder.setTitle("Scanning Result");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            addISBN();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                builder.setNegativeButton("Finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        ISBN =result.getContents();
//                        Log.i(TAG, "The number you scanned " + ISBN);
                       finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else{
                Toast.makeText(this, "No Results", Toast.LENGTH_LONG).show();
            }
        } else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}