package com.example.testlibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List; //Ironically? Both MyRecyclerViewAdapter and DataBaseHelper return List<BookModel>

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    //reference buttons
    Button buttonViewAll, buttonAddBook;
    Switch ownBook;
    EditText editTextBookTitle, editTextISBN, editNumPages;

    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddBook = findViewById(R.id.buttonAddBook);
        buttonViewAll = findViewById(R.id.buttonViewAll);

        ownBook = findViewById(R.id.ownBook);
        editTextBookTitle = findViewById(R.id.editTextBookTitle);
        editTextISBN = findViewById(R.id.editTextISBN);
        editNumPages = findViewById(R.id.editNumPages);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        //The RecyclerView and its Adapter
        recyclerView = findViewById(R.id.recyclerViewForBooks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());//TODO decoration not working?
        recyclerView.addItemDecoration(dividerItemDecoration); //TODO decoration not working?


        adapter = new MyRecyclerViewAdapter(MainActivity.this, dataBaseHelper.getAllBooks());
        adapter.setClickListener(this);

        ShowBooksOnRecyclerView();

        //listeners
        buttonAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BookModel newBook;
                try {
                    newBook = new BookModel(
                            -1,
                            editTextBookTitle.getText().toString(),
                            editTextISBN.getText().toString(),
                            Integer.parseInt(editNumPages.getText().toString()),
                            ownBook.isChecked());

                    Toast.makeText(MainActivity.this, newBook.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "There was a Problem", Toast.LENGTH_SHORT).show();
                    newBook = new BookModel(-1, "Unknown", "XXX0000000000", -1, false);
                }

                boolean success = dataBaseHelper.addOne(newBook);

                ShowBooksOnRecyclerView();

                Toast.makeText(MainActivity.this, "Success " + success, Toast.LENGTH_SHORT).show();
            }
        });

        buttonViewAll.setOnClickListener(v -> ShowBooksOnRecyclerView());


//        //TODO this is the code that will delete an item
//        BookModel swipedBook = (BookModel) view.getAdapterPosition();
//        boolean b = dataBaseHelper.deleteOne(swipedBook);
//        if (b) {
//            System.out.println("YO");
//            Toast.makeText(MainActivity.this, "Deleted " /*+ swipedBook*/, Toast.LENGTH_SHORT).show();
//        }
    }

    //This updates the RecyclerView
    private void ShowBooksOnRecyclerView() {
//        dataBaseHelper = new DataBaseHelper(MainActivity.this );

        adapter = new MyRecyclerViewAdapter(MainActivity.this, dataBaseHelper.getAllBooks());
        recyclerView.setAdapter(adapter);
    }

    //TODO this doesn't seem to do anything...
    public void onItemClick(View view, int position) {
        System.out.println("You clicked " + adapter.getItem(position) + " on row number " + position);
    }
}