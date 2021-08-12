package com.example.biblio_tech_mark_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ManageShelvesActivity extends AppCompatActivity {
/* This is just a write up of what we would like to work on more for our app. It has a finish button. */
    public static final String TAG = "ManageShelvesActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_shelves);

        Intent intent = getIntent();
        Button manageFinishButton = findViewById(R.id.manageFinishButton);
        manageFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {finish(); }
        });

    }
}