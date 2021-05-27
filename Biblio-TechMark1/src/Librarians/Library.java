package Librarians;

import java.util.ArrayList;

public class Library {
    private Shelf allBooks;
    private ArrayList<Shelf> allShelves;

    public ArrayList<Shelf> showShelves() { return this.allShelves; }

    public ArrayList<Book> showAllBooks(){ return allBooks.getBooks(); }


    public Library (){
        this.allBooks = new Shelf("All Books");
        this.allShelves = new ArrayList<>();
        allShelves.add(allBooks);
    }
}
