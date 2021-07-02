package com.example.biblio_tech_mark_3;

import java.util.ArrayList;
import java.util.List;

public class Book { //title, author, List genres, List Subjects, int ISBN, longString Description

    private int id;
    private String title; //Title String -> related to Work
    private String author; //The Author ->lookup from Author(s) key (usually one)
    private List<String> genres; //More than one?
    private List<String> subjects; //~6+2=8 long    entries__subjects__004, entries__subjects__005, entries__subjects__006, entries__subjects__001, entries__subjects__002,	entries__subjects__003 [subject_places], [subject_people];
    private int ISBN; //The book's ISBN13 (not edition key, unique to book=edition). If ISBN10 then add prefix
    private String notes; // also notes entries__first_sentence__value, entries__description, entries__description__value; //Have each value concatenate, not List?
//    private boolean owned;
    //Many others:
//    private String entries__key; //The Edition Key
//    entries__subtitle;
//    entries__contributors__role, entries__contributors__name;
//    entries__series__001, entries__series__002;
//    entries__isbn_13__-	entries__dewey_decimal_class__-	entries__identifiers__librarything__-	entries__identifiers__goodreads__-	entries__identifiers__amazon__-	entries__covers__-;
//    entries__physical_format	entries__edition_name	entries__languages__key	entries__publishers__-	entries__publish_date;


    @Override
    public String toString() {
        return title + "  by "+ author + "\n" +
                "ISBN=" + ISBN  + '\n' +
                "Notes:'" + notes;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public List<String> getGenres() { return genres; }
    public void setGenres(List<String> genres) { this.genres = genres; }
    public void addGenre(String subject){ this.genres.add(subject); }

    public List<String> getSubjects() { return subjects; }
    public void setSubjects(List<String> subjects) { this.subjects = subjects; }
    public void addSubject(String subject){ this.subjects.add(subject); }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) { this.notes = notes; }

    public int getISBN() {
        return ISBN;
    }
    public void setISBN(int ISBN) { this.ISBN = ISBN; }

    public Book(int id, String title, String author, List<String> genres, List<String> subjects, int ISBN, String notes) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genres = genres;
        this.subjects = subjects;
        this.ISBN = ISBN;
        this.notes = notes;
    }

    public Book() {

    }
}