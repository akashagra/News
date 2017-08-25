package com.example.avma1997.news;

import android.widget.ImageView;

/**
 * Created by Avma1997 on 7/12/2017.
 */

public class News {
   String imageurl;
    String title;
    String description;
    String url;

    public News(String imageurl, String title, String description, String url) {
        this.imageurl = imageurl;
        this.title = title;
        this.description = description;
        this.url = url;
    }
}
