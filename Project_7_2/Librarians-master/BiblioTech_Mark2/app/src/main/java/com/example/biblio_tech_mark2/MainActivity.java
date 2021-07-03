package com.example.biblio_tech_mark2;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "!!!Main activity!!!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG,"Starting loader and Thread");
        Loader loader = new Loader(/*this*/);
        Thread loading = new Thread((Runnable) loader, "loader");
        loading.start();


    }
}