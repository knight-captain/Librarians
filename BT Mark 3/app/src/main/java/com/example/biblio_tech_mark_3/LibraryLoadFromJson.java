package com.example.biblio_tech_mark_3;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.*;

public class LibraryLoadFromJson {
    //    private static String librarySaveData = "ShortLibrary";// fix to Android
    //using a string instead
    public static String testJson =
            "{\"name\":\"Shelf\",\"books\":[" +
                    "{\"Series\":\"The Chronicles of Narnia\",\"Subtitle\":\"\",\"Sub-genre \":\"Epic Fantasy\",\"Genre \":\"SF/F\",\"Editions/Language\":\"B\\u0026N Collectable\",\"Author\":\"C.S. Lewis\",\"TITLE\":\"The Chronicles of Narnia\",\"# \":\"\",\"Illustrator/Translator/Editor\":\"\",\"Age\":\"MG\"}," +
                    "{\"Series\":\"The Chronicles of Narnia\",\"Subtitle\":\"\",\"Sub-genre \":\"Epic Fantasy\",\"Genre \":\"SF/F\",\"Editions/Language\":\"Hardcover\",\"Author\":\"C.S. Lewis\",\"TITLE\":\"The Chronicles of Narnia\",\"# \":\"\",\"Illustrator/Translator/Editor\":\"\",\"Age\":\"MG\"}," +
                    "{\"Series\":\"The Chronicles of Narnia\",\"Subtitle\":\"\",\"Sub-genre \":\"Epic Fantasy\",\"Genre \":\"SF/F\",\"Editions/Language\":\"Paperback\",\"Author\":\"C.S. Lewis\",\"TITLE\":\"The Chronicles of Narnia\",\"# \":\"\",\"Illustrator/Translator/Editor\":\"\",\"Age\":\"MG\"}," +
                    "{\"Series\":\"The Lord of the Rings\",\"Subtitle\":\"\",\"Sub-genre \":\"Epic Fantasy\",\"Genre \":\"SF/F\",\"Editions/Language\":\"Paperback; Boxed\",\"Author\":\"J.R.R. Tolkein\",\"TITLE\":\"The Hobbit\",\"# \":\"0\",\"Illustrator/Translator/Editor\":\"\",\"Age\":\"A\"}," +
                    "{\"Series\":\"The Lord of the Rings\",\"Subtitle\":\"\",\"Sub-genre \":\"Epic Fantasy\",\"Genre \":\"SF/F\",\"Editions/Language\":\"Paperback; Boxed\",\"Author\":\"J.R.R. Tolkein\",\"TITLE\":\"The Fellowship of the Ring\",\"# \":\"1\",\"Illustrator/Translator/Editor\":\"\",\"Age\":\"A\"}," +
                    "{\"Series\":\"The Lord of the Rings\",\"Subtitle\":\"\",\"Sub-genre \":\"Epic Fantasy\",\"Genre \":\"SF/F\",\"Editions/Language\":\"Paperback; Boxed\",\"Author\":\"J.R.R. Tolkein\",\"TITLE\":\"The Two Towers\",\"# \":\"2\",\"Illustrator/Translator/Editor\":\"\",\"Age\":\"A\"}," +
                    "{\"Series\":\"The Lord of the Rings\",\"Subtitle\":\"\",\"Sub-genre \":\"Epic Fantasy\",\"Genre \":\"SF/F\",\"Editions/Language\":\"Paperback; Boxed\",\"Author\":\"J.R.R. Tolkein\",\"TITLE\":\"The Return of the King\",\"# \":\"3\",\"Illustrator/Translator/Editor\":\"\",\"Age\":\"A\"}," +
                    "{\"Series\":\"The Lord of the Rings\",\"Subtitle\":\"\",\"Sub-genre \":\"Epic Fantasy\",\"Genre \":\"SF/F\",\"Editions/Language\":\"Boxed\",\"Author\":\"J.R.R. Tolkein\",\"TITLE\":\"The Lord of the Rings\",\"# \":\"\",\"Illustrator/Translator/Editor\":\"\",\"Age\":\"A\"}]}";
    public static final String TAG = "!LibraryLoadFromJson!";

    public static Shelf readFile() {

        Shelf json = null;

        Gson gson = new Gson();
        json = gson.fromJson(testJson, Shelf.class);

        //TODO Below needs to be fixed to android
        //SQLite: https://developer.android.com/training/data-storage/sqlite
        Log.i(TAG,"this should save this file onto the device...");
        MainActivity.SaveData(json);

        Log.i(TAG,"returning json to Loader");
        return json;
    }
}
