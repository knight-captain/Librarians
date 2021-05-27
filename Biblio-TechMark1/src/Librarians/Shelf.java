package Librarians;

import java.util.ArrayList;

public class Shelf {
    private String name;
    private ArrayList<Book> books;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public ArrayList<Book> getBooks(){ return books; }

    public Shelf(String name){
        this.name = name;
        this.books = new ArrayList<>();
    }
}
