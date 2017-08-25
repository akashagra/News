package com.example.avma1997.news;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnDownloadCompleteListener, OnDownloadCompListener {
    ArrayList<News> newslist;
    ListView newsListView;
    NewsArrayAdapter newsadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        newsListView = (ListView) findViewById(R.id.newsListView);
        newslist = new ArrayList<>();

        newsadapter = new NewsArrayAdapter(this, newslist);


        newsListView.setAdapter(newsadapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                News n=newslist.get(position);
                String urlString=n.url;
                Uri uri=Uri.parse(urlString);
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                startActivity(intent);

            }
        });

        fetchStocksOnCreate("https://newsapi.org/v1/articles?source=the-times-of-india&sortBy=top&apiKey=af43671bbe8542e99f7c2e33654ca425");
        fetchStocksOnCreate("https://newsapi.org/v1/articles?source=the-hindu&sortBy=top&apiKey=af43671bbe8542e99f7c2e33654ca425");
        fetchStocksOnCreate("https://newsapi.org/v1/articles?source=techcrunch&sortBy=top&apiKey=af43671bbe8542e99f7c2e33654ca425");
        fetchStocksOnCreate("https://newsapi.org/v1/articles?source=business-insider&sortBy=top&apiKey=af43671bbe8542e99f7c2e33654ca425");
        fetchStocksOnCreate("https://newsapi.org/v1/articles?source=espn&sortBy=top&apiKey=af43671bbe8542e99f7c2e33654ca425");
        fetchStocksOnCreate("https://newsapi.org/v1/articles?source=national-geographic&sortBy=top&apiKey=af43671bbe8542e99f7c2e33654ca425");
        fetchStocksOnCreate("https://newsapi.org/v1/articles?source=google-news&sortBy=top&apiKey=af43671bbe8542e99f7c2e33654ca425");
        Log.i("abc","pahuch raha h");


    }


//    protected void onStart() {
//        super.onStart();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        newsListView = (ListView) findViewById(R.id.newsListView);
//        newslist = new ArrayList<>();
//
//        newsadapter = new NewsArrayAdapter(this, newslist);
//
//
//        newsListView.setAdapter(newsadapter);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                News n=newslist.get(position);
//                String urlString=n.url;
//                Uri uri=Uri.parse(urlString);
//                Intent intent=new Intent(Intent.ACTION_VIEW);
//                intent.setData(uri);
//                startActivity(intent);
//
//            }
//        });
//
//        fetchStocksOnCreate("https://newsapi.org/v1/articles?source=the-times-of-india&sortBy=top&apiKey=af43671bbe8542e99f7c2e33654ca425");
//        fetchStocksOnCreate("https://newsapi.org/v1/articles?source=the-hindu&sortBy=top&apiKey=af43671bbe8542e99f7c2e33654ca425");
//        fetchStocksOnCreate("https://newsapi.org/v1/articles?source=techcrunch&sortBy=top&apiKey=af43671bbe8542e99f7c2e33654ca425");
//        fetchStocksOnCreate("https://newsapi.org/v1/articles?source=business-insider&sortBy=top&apiKey=af43671bbe8542e99f7c2e33654ca425");
//        fetchStocksOnCreate("https://newsapi.org/v1/articles?source=espn&sortBy=top&apiKey=af43671bbe8542e99f7c2e33654ca425");
//        fetchStocksOnCreate("https://newsapi.org/v1/articles?source=national-geographic&sortBy=top&apiKey=af43671bbe8542e99f7c2e33654ca425");
//        fetchStocksOnCreate("https://newsapi.org/v1/articles?source=google-news&sortBy=top&apiKey=af43671bbe8542e99f7c2e33654ca425");
//        Log.i("abc","pahuch raha h");
//
//
//
//    }

    private void fetchStocksOnCreate(String s) {
        OnCreateNewsAsyncTask newsAsyncTask = new OnCreateNewsAsyncTask();
        newsAsyncTask.execute(s);
        newsAsyncTask.setOnDownloadCompListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.toi) {
            fetchStocks("https://newsapi.org/v1/articles?source=the-times-of-india&sortBy=latest&apiKey=af43671bbe8542e99f7c2e33654ca425");

        } else if (id == R.id.hindu) {
            fetchStocks("https://newsapi.org/v1/articles?source=the-hindu&sortBy=latest&apiKey=af43671bbe8542e99f7c2e33654ca425");

        } else if (id == R.id.techcrunch) {
            fetchStocks("https://newsapi.org/v1/articles?source=techcrunch&sortBy=latest&apiKey=af43671bbe8542e99f7c2e33654ca425");

        } else if (id == R.id.businessinsider) {
            fetchStocks("https://newsapi.org/v1/articles?source=business-insider&sortBy=latest&apiKey=af43671bbe8542e99f7c2e33654ca425");

        } else if (id == R.id.espn) {
            fetchStocks("https://newsapi.org/v1/articles?source=espn&sortBy=top&apiKey=af43671bbe8542e99f7c2e33654ca425");

        } else if (id == R.id.nationalgeographic) {
            fetchStocks("https://newsapi.org/v1/articles?source=national-geographic&sortBy=top&apiKey=af43671bbe8542e99f7c2e33654ca425");

        }
        else if (id == R.id.googlenews) {
          fetchStocks("https://newsapi.org/v1/articles?source=google-news&sortBy=top&apiKey=af43671bbe8542e99f7c2e33654ca425");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void fetchStocks(String urlString) {

        NewsAsyncTask newsAsyncTask = new NewsAsyncTask();
        newsAsyncTask.execute(urlString);
        newsAsyncTask.setOnDownloadCompleteListener(this);

    }

    @Override
    public void onDownloadComplete(ArrayList<News> Newslist) {

       newslist.clear();
       newslist.addAll(Newslist);

        newsadapter.notifyDataSetChanged();
    }

    @Override
    public void onDownloadComp(ArrayList<News> Newslist) {
        if(Newslist==null) {
            Toast.makeText(this, "Internet Not Working", Toast.LENGTH_LONG).show();
            return;
        }
        newslist.addAll(Newslist);
        newsadapter.notifyDataSetChanged();

    }
}
