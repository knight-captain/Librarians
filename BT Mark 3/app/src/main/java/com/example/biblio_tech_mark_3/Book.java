package com.example.biblio_tech_mark_3;

import java.util.Date;
import java.util.List;

public class Book {
    //todo make necessary getters & setters
    private List<String> publishers;
    private Identifiers identifiers;
    private List<Integer> covers;
    private List<String> local_id;
    private String physical_format;
    private LastModified last_modified;
    private int latest_revision;
    private String key;
    private List<Author> authors;
    private String ocaid;
    private String edition_name;
    private List<Language> languages;
    private List<String> source_records;
    private String title;
    private int number_of_pages;
    private Created created;
    private List<String> isbn_13;
    private List<String> isbn_10;
    private String publish_date;
    //todo Get the Work to have a list of Books
    private List<Work> works;
    private Type type;
    private int revision;
}

class Identifiers {
    public List<String> goodreads;
}

class Language{
    public String key;
}

class Created{
    public String type;
    public Date value;
}