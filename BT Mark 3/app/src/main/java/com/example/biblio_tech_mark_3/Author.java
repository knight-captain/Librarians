package com.example.biblio_tech_mark_3;

public class Author {
    //todo set all to private and make necessary getters & setters
    public String name;
    public String personal_name;
    public LastModified last_modified;
    public String key;
    public Type type;
    public int id;
    public int revision;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public Author (String name) {
        this.name = name;
    }
}