package com.movies;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Listed_movies_from_database extends AppCompatActivity {

    String urladdress = "http://192.168.1.199/Mobile_API/fetch_movies.php";
    String[] movie_studio;
    String[] movie_category;
    String[] movie_name;
    String[] movie_length;
    ListView listView;
    BufferedInputStream is;
    String line = null;
    String result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listed_movies_from_database);

        listView = (ListView) findViewById(R.id.lvMovies);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
        CustomListView customListView = new CustomListView(this, movie_studio, movie_category, movie_name, movie_length);
        listView.setAdapter(customListView);
    }

    private void collectData()
    {
        try {
            URL url = new URL(urladdress);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            is = new BufferedInputStream(con.getInputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            while((line=br.readLine()) != null){
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try{
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;
            movie_studio = new String[ja.length()];
            movie_category = new String[ja.length()];
            movie_name = new String[ja.length()];
            movie_length = new String[ja.length()];

            for(int i = 0; i<=ja.length(); i++){
                jo = ja.getJSONObject(i);
                movie_studio[i] = jo.getString("movie_studio");
                movie_category[i] = jo.getString("movie_category");
                movie_name[i] = jo.getString("movie_name");
                movie_length[i] = jo.getString("movie_length");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}