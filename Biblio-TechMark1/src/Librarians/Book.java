package Librarians;

import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("TITLE")
    private String title;
    @SerializedName("Author")
    private String author;
    @SerializedName("Series")
    private String series;
//    private int pages;

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Book (){
        this.title = "unknown book";
        this.author = "unknown author";
        this.series = "none";
    }
}