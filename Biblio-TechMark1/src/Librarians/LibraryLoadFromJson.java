package Librarians;

import com.google.gson.Gson;

import java.io.*;

public class LibraryLoadFromJson {
    private static String librarySaveData = "/Users/McVey/IdeaProjects/Biblio-TechMark1/src/Librarians/LibrarySaveData";

    public static Shelf readFile() throws FileNotFoundException {
        //reads the CSV and adds each line to an array
        Shelf json = null;

//        Scanner scanner = new Scanner(new File(librarySaveData));
//        json = scanner.next();
//        scanner.close();
//
//        System.out.println("File read");
//
//        Gson gson = new Gson();
//        JsonReader reader = new JsonReader(new StringReader(json));
//        reader.setLenient(true);
//
//        Shelf library = gson.fromJson(reader, Shelf.class);

        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(librarySaveData));

            //convert the json string back to object
            json = gson.fromJson(br, Shelf.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
