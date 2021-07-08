package com.example.biblio_tech_mark_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ManageShelvesActivity extends AppCompatActivity {

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