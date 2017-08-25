package com.example.avma1997.news;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Avma1997 on 7/12/2017.
 */

 public class NewsAsyncTask extends AsyncTask<String, Void, ArrayList<News>> {
    OnDownloadCompleteListener mListener;

    void setOnDownloadCompleteListener(OnDownloadCompleteListener listener) {
        mListener = listener;
    }


    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected ArrayList<News> doInBackground(String... params) {

        String urlString = params[0];

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();

            Scanner s = new Scanner(inputStream);
            String str = "";
            while (s.hasNext()) {
                str += s.nextLine();
            }

            Log.i("FetchedString ", str);
            return parseNews(str);

        } catch (MalformedURLException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ArrayList<News> parseNews(String str) {

        try {
            ArrayList<News> Newslist = new ArrayList<>();
            JSONObject NewssJson = new JSONObject(str);
            JSONArray articles = NewssJson.getJSONArray("articles");
            for (int i = 0; i < articles.length(); i++) {
                JSONObject postJson = (JSONObject) articles.get(i);
                String title=postJson.getString("title");
                String description = postJson.getString("description");
                String urltoimage=postJson.getString("urlToImage");
                String url=postJson.getString("url");

                News n = new News(urltoimage,title,description,url);


                Newslist.add(n);


            }

            return Newslist;


        } catch (JSONException e) {

        }
        return null;

    }


    protected void onPostExecute(ArrayList<News> Newslist) {
        super.onPostExecute(Newslist);
        if (mListener != null) {
            mListener.onDownloadComplete(Newslist);
        }
    }
}

interface OnDownloadCompleteListener {

    void onDownloadComplete(ArrayList<News> Newslist);

}


