package com.example.biblio_tech_mark_3;

import java.util.Date;
import java.util.List;

public class Book {
    //todo set all to private and make necessary getters & setters
    private List<String> publishers;
    public Identifiers identifiers;
    public List<Integer> covers;
    public List<String> local_id;
    public String physical_format;
    public LastModified last_modified;
    public int latest_revision;
    public String key;
    public List<Author> authors;
    public String ocaid;
    public String edition_name;
    public List<Language> languages;
    public List<String> source_records;
    public String title;
    public int number_of_pages;
    public Created created;
    public List<String> isbn_13;
    public List<String> isbn_10;
    public String publish_date;
    public List<Work> works;
    public Type type;
    public int revision;
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