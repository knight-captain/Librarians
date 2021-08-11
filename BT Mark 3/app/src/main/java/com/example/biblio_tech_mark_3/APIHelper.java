package com.example.biblio_tech_mark_3;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class APIHelper implements Callable<List<Book>> {
/* This class reads from the API openlibrary. */
    public static final String TAG = "APIHelper";

    private String title;
    private String ISBN; //can lookup 10 or 13
    public List<BookOld> results;

    public static String openLibURL = "http://openlibrary.org";
//    public static String limit = "limit=5";
    public static String charset = "UTF-8";

    @Override
    public List<BookOld> call() throws Exception {
        Log.i(TAG,"starting API");


        if (ISBN != null) {
            results = lookupISBN(ISBN);
            return results;
        }else{
            results = lookupTitle(title);
            return results;
        }
    }

    protected List<BookOld> lookupTitle(String title){
        String formatedTitle = title.replaceAll("\\s+", "+");
        String query = String.format("%s/%s.json?title=%s", openLibURL, "search", formatedTitle);
        Log.i(TAG, query);

        try
        {
            URLConnection connection = new URL(query).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();

            try (Scanner scanner = new Scanner(response))
            {
                String responseBody = scanner.useDelimiter("\\A").next(); //"\\A" means start of string
                String str[] = responseBody.split(",");
                List<String> reponseKeys = new ArrayList<String>();
                reponseKeys = Arrays.asList(str);

                List<String> bookKeys = new ArrayList<>();

                for (String key : reponseKeys){
                    if (key.contains("/books/")){
                        key = key.replaceAll("\\s+", "");
                        key = key.replaceAll("\"", "");
                        key = key.replaceAll("seed:", "");
                        key = key.replace("[","");
                        bookKeys.add(key);
                    }
                }

                for (String bookKey : bookKeys){
                    if (results.size() < 10) {
                        try {
                            BookOld book = lookupKey(bookKey);
                            results.add(book);
                        } catch (NullPointerException e){
                            Log.i(TAG, "" + e);
                        }
                    } else { break; }
                }

                return results;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        // should never get here?
        return null;
    }

    protected BookOld lookupKey(String key){
        String query = String.format("%s%s.json",openLibURL, key);
        Log.i(TAG, query);

        try
        {
            URLConnection connection = new URL(query).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();

            try (Scanner scanner = new Scanner(response))
            {
                String responseBody = scanner.useDelimiter("\\A").next();

                BookOld test = JsonHelper.jsonToBook(responseBody);

                Author authorName = LookupAuthor(test.getFirstAuthor().getKey());
                test.setAuthor( authorName );

                Log.i(TAG, test.toString());

                return test;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        // should never get here?
        return null;
    }

    protected List<BookOld> lookupISBN(String ISBN){
        String query = String.format("%s/%s/%s.json",openLibURL, "isbn", ISBN);
        Log.i(TAG, query);

        try
        {
            URLConnection connection = new URL(query).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();

            try (Scanner scanner = new Scanner(response))
            {
                String responseBody = scanner.useDelimiter("\\A").next();
                Log.i(TAG, responseBody);

                BookOld test = JsonHelper.jsonToBook(responseBody);
                Author authorName = LookupAuthor(test.getFirstAuthor().getKey());
                test.setAuthor( authorName );

                results.add(test);
                return results;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        // should never get here?
        return null;
    }

    protected Author LookupAuthor(String code) throws IOException {
        //fresh API to grab Author's name
        String query = String.format("%s%s.json",openLibURL, code);
        Log.i(TAG, query);

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
