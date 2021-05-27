package Librarians;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Loader implements Runnable{

    @Override
    public void run() {
        Shelf library = new Shelf("test");
        try {
            Shelf readFile = LibraryLoadFromJson.readFile();

            for(Book b : readFile.getBooks()){
                System.out.println(b.getTitle());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
