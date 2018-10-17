package com.example.osamaa.booklist;

public class Books {
    private String title;
    private String author;
    private String url;
    private String publishDate;

    public Books(String title,String author,String url,String publishDate){
        this.title = title;
        this.author = author;

        this.url = url;
        this.publishDate = publishDate;
    }

    public String getTitle(){return title;}
    public String getAuthor(){return author;}
    public String getUrl(){return url;}
    public String getPublishDate(){return publishDate;}
}
