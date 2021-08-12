package com.example.biblio_tech_mark_3;

public class Author {
    //todo make necessary getters & setters
    private String name;
    private String personal_name;
    private LastModified last_modified;
    private String key;
    private Type type;
    private int id;
    private int revision;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public Author (String name) {
        this.name = name;
    }
}