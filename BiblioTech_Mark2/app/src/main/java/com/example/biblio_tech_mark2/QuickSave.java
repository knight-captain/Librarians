package com.example.biblio_tech_mark2;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class QuickSave {
    public static String json = "{\"name\":\"Shelf\",\"books\":[{\"Series\":\"The Chronicles of Narnia\",\"Subtitle\":\"\",\"Sub-genre \":\"Epic Fantasy\",\"Genre \":\"SF/F\",\"Editions/Language\":\"B\\u0026N Collectable\",\"Author\":\"C.S. Lewis\",\"TITLE\":\"The Chronicles of Narnia\",\"# \":\"\",\"Illustrator/Translator/Editor\":\"\",\"Age\":\"MG\"},{\"Series\":\"The Chronicles of Narnia\",\"Subtitle\":\"\",\"Sub-genre \":\"Epic Fantasy\",\"Genre \":\"SF/F\",\"Editions/Language\":\"Hardcover\",\"Author\":\"C.S. Lewis\",\"TITLE\":\"The Chronicles of Narnia\",\"# \":\"\",\"Illustrator/Translator/Editor\":\"\",\"Age\":\"MG\"},{\"Series\":\"The Chronicles of Narnia\",\"Subtitle\":\"\",\"Sub-genre \":\"Epic Fantasy\",\"Genre \":\"SF/F\",\"Editions/Language\":\"Paperback\",\"Author\":\"C.S. Lewis\",\"TITLE\":\"The Chronicles of Narnia\",\"# \":\"\",\"Illustrator/Translator/Editor\":\"\",\"Age\":\"MG\"},{\"Series\":\"The Lord of the Rings\",\"Subtitle\":\"\",\"Sub-genre \":\"Epic Fantasy\",\"Genre \":\"SF/F\",\"Editions/Language\":\"Paperback; Boxed\",\"Author\":\"J.R.R. Tolkein\",\"TITLE\":\"The Hobbit\",\"# \":\"0\",\"Illustrator/Translator/Editor\":\"\",\"Age\":\"A\"},{\"Series\":\"The Lord of the Rings\",\"Subtitle\":\"\",\"Sub-genre \":\"Epic Fantasy\",\"Genre \":\"SF/F\",\"Editions/Language\":\"Paperback; Boxed\",\"Author\":\"J.R.R. Tolkein\",\"TITLE\":\"The Fellowship of the Ring\",\"# \":\"1\",\"Illustrator/Translator/Editor\":\"\",\"Age\":\"A\"},{\"Series\":\"The Lord of the Rings\",\"Subtitle\":\"\",\"Sub-genre \":\"Epic Fantasy\",\"Genre \":\"SF/F\",\"Editions/Language\":\"Paperback; Boxed\",\"Author\":\"J.R.R. Tolkein\",\"TITLE\":\"The Two Towers\",\"# \":\"2\",\"Illustrator/Translator/Editor\":\"\",\"Age\":\"A\"},{\"Series\":\"The Lord of the Rings\",\"Subtitle\":\"\",\"Sub-genre \":\"Epic Fantasy\",\"Genre \":\"SF/F\",\"Editions/Language\":\"Paperback; Boxed\",\"Author\":\"J.R.R. Tolkein\",\"TITLE\":\"The Return of the King\",\"# \":\"3\",\"Illustrator/Translator/Editor\":\"\",\"Age\":\"A\"},{\"Series\":\"The Lord of the Rings\",\"Subtitle\":\"\",\"Sub-genre \":\"Epic Fantasy\",\"Genre \":\"SF/F\",\"Editions/Language\":\"Boxed\",\"Author\":\"J.R.R. Tolkein\",\"TITLE\":\"The Lord of the Rings\",\"# \":\"\",\"Illustrator/Translator/Editor\":\"\",\"Age\":\"A\"}]}";

    public static void writeFileOnInternalStorage(Context activity){

        //Context context = activity.getApplicationContext();
        
        
        File dir = new File(activity.getFilesDir(), "mydir");
        if(!dir.exists()){
            dir.mkdir();
        }

        try {
            File gpxfile = new File(dir, "SaveData");
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(json);
            Log.i("QUICKSAVE", String.valueOf(gpxfile));
            writer.flush();
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
