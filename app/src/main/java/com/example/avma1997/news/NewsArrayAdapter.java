package com.example.avma1997.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Avma1997 on 7/12/2017.
 */

public class NewsArrayAdapter extends ArrayAdapter<News> {
    ArrayList<News> newslist;
    Context context;


    public NewsArrayAdapter(Context context, ArrayList<News> newslist) {
        super(context, 0);
        this.context = context;
        this.newslist = newslist ;

    }
    public int getCount() {
        return newslist.size();
    }

    static class NewsViewHolder {

      ImageView image;
        TextView titletv;
        TextView desctv;


        NewsViewHolder(ImageView image,TextView titletv,TextView desctv) {

            this.image=image;
            this.titletv=titletv;
            this.desctv=desctv;



        }

    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            ImageView image=(ImageView) convertView.findViewById(R.id.image_view);
            TextView titletv=(TextView)convertView.findViewById(R.id.title_textview);
            TextView desctv=(TextView) convertView.findViewById(R.id.desc_textview);


            NewsViewHolder newsViewHolder = new NewsViewHolder(image,titletv,desctv);
            convertView.setTag(newsViewHolder);

        }
        News n = newslist.get(position);

        NewsViewHolder newsViewHolder = (NewsViewHolder) convertView.getTag();
      Picasso.with(context).load(n.imageurl).into(newsViewHolder.image);
        newsViewHolder.titletv.setText(n.title);
        newsViewHolder.desctv.setText(n.description);


        return convertView;
    }



}

