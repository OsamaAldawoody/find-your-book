package com.example.osamaa.booklist;

import android.graphics.Bitmap;

public class Books {
    private String title;
    private Bitmap image;
    private String url;
    private String publishDate;

    public Books(String title,Bitmap image,String url,String publishDate){
        this.title = title;
        this.image= image;

        this.url = url;
        this.publishDate = publishDate;
    }

    public String getTitle(){return title;}
    public Bitmap getImage(){return image;}
    public String getUrl(){return url;}
    public String getPublishDate(){return publishDate;}
}
