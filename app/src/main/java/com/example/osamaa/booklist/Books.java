package com.example.osamaa.booklist;

public class Books {
    private String title;
    private String imageLink;
    private String url;
    private String publishDate;

    public Books(String title,String imageLink,String url,String publishDate){
        this.title = title;
        this.imageLink= imageLink;

        this.url = url;
        this.publishDate = publishDate;
    }

    public String getTitle(){return title;}
    public String getImageLink(){return imageLink;}
    public String getUrl(){return url;}
    public String getPublishDate(){return publishDate;}
}
