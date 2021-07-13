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

    public static final String TAG = "APIHelper";

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


        if (ISBN != null) {
            results = lookupISBN(ISBN);
            return results;
        }else{
            return null;
        }
    }

    protected List<Book> lookupTitle(String title){
        return null;
    }

    protected List<Book> lookupISBN(String ISBN){
        String query = String.format("%s/%s/%s.json",openLibURL, apiType, ISBN);
        System.out.println(query);

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
                Author authorName = LookupAuthor(test.getFirstAuthor().getKey());
                test.setAuthor( authorName );

                results.add(test);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        // should never get here?
        return results;
    }

    protected Author LookupAuthor(String code) throws IOException {
        //fresh API to grab Author's name
        String query = String.format("%s%s.json",openLibURL, code);
        System.out.println(query);

        try
        {
            URLConnection connection = new URL(query).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();

            try (Scanner scanner = new Scanner(response))
            {
                String responseBody = scanner.useDelimiter("\\A").next();
                Author author = JsonHelper.jsonToAuthor(responseBody);
                return author;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        // should never get here?
        return null;
    }

    public APIHelper (String title, String ISBN){
        this.title = title;
        this.ISBN = ISBN;
        this.results = new ArrayList<>();
    }
}
