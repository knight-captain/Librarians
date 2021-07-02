package com.example.biblio_tech_mark_3;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class APIHelper implements Callable<List<Book>> {

    public static final String TAG = "!!!APIHelper!!!";

    private String title;
    private String ISBN; //can lookup 10 or 13
    public List<Book> results;

    public static String openLibURL = "http://openlibrary.org";
    public static String apiType = "isbn";//test value
//    public static String limit = "limit=5";
    public static String charset = "UTF-8";

    @Override
    public List<Book> call() throws Exception {
        Log.i(TAG,"starting API");
        String query = String.format("%s/%s/%s.json",openLibURL, apiType, ISBN);
        System.out.println(query);

        if (ISBN != null) {
            try
            {
                URLConnection connection = new URL(query).openConnection();
                connection.setRequestProperty("Accept-Charset", charset);
                InputStream response = connection.getInputStream();

                try (Scanner scanner = new Scanner(response))
                {
                    String responseBody = scanner.useDelimiter("\\A").next();
                    Log.i(TAG, responseBody);
                    Book test = JsonHelper.jsonToBook(responseBody);
                    Log.i(TAG,String.format("%s by %s about %s & %s; ISBN: %d. Notes: %s", test.getTitle(), test.getAuthor(), String.valueOf(test.getGenres()),String.valueOf(test.getSubjects()), test.getISBN(), test.getNotes() ));
                    results.add(test);
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            return results;
        }else{
            return null;
        }
    }

    public APIHelper (String title, String ISBN){
        this.title = title;
        this.ISBN = ISBN;
        this.results = new ArrayList<>();
    }
}
