package com.example.testlibrary;

public class BookModel {

    private int id;
    private String entries__title; //Title String
    private String entries__works__ISBN; //The book's ISBN (not edition key) TODO NEEDS TO BE UNIQUE!!!
//    private ArrayList<String> entries__authors__key; //The list of Authors' keys (usually one)
    private String entries__key; //The Edition Key
    private int entries__number_of_pages;
    private boolean owned;
    //Many others:
//    entries__subtitle;
//    entries__contributors__role, entries__contributors__name;
//    entries__series__001, entries__series__002;
//    entries__genres__-;
//    entries__subjects__004, entries__subjects__005, entries__subjects__006, entries__subjects__001, entries__subjects__002,	entries__subjects__003;
//    [subject_places], [subject_people];
//    entries__first_sentence__value, entries__description, entries__description__value;
//    entries__isbn_13__-	entries__dewey_decimal_class__-	entries__identifiers__librarything__-	entries__identifiers__goodreads__-	entries__identifiers__amazon__-	entries__covers__-;
//    entries__physical_format	entries__edition_name	entries__languages__key	entries__publishers__-	entries__publish_date;


    @Override
    public String toString() {
        return entries__title + " ("+ id +")" + "\n" +
               "ISBN='" + entries__works__ISBN + '\n' +
               "Pages=" + entries__number_of_pages + (owned ? ", (Do not own)" : " (Owned)");
    }

    public int getId() {
        return id;
    }

    public String getEntries__title() {
        return entries__title;
    }

    public void setEntries__title(String entries__title) {
        this.entries__title = entries__title;
    }

    public String getEntries__works__ISBN() {
        return entries__works__ISBN;
    }

    public void setEntries__works__ISBN(String entries__works__ISBN) {
        this.entries__works__ISBN = entries__works__ISBN;
    }

    public int getEntries__number_of_pages() {
        return entries__number_of_pages;
    }

    public void setEntries__number_of_pages(int entries__number_of_pages) {
        this.entries__number_of_pages = entries__number_of_pages;
    }

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public BookModel(int id,String entries__title, String entries__works__ISBN, int entries__number_of_pages, boolean owned) {
        this.id = id;
        this.entries__title = entries__title;
        this.entries__works__ISBN = entries__works__ISBN;
        this.entries__number_of_pages = entries__number_of_pages;
        this.owned = owned;
    }

    public BookModel() {

    }
}
