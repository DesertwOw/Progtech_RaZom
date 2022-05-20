package com.movies.Pages;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.movies.Config;
import com.movies.Main_menu;
import com.movies.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Listed_movies_from_database extends AppCompatActivity {

    String urladdress = Config.showURL + "fetch_movie.php";
    String[] movie_studio;
    String[] movie_category;
    String[] movie_name;
    String[] movie_length;
    String[] movie_rate;
    ListView listView;
    BufferedInputStream is;
    String line = null;
    String result = null;

    final String FETCHG = "Fetch request good";
    final String FETCHB = "Fetch request failed";
    final String GETREQ = "Get request started";
    final String BADGETREQ = "Get request failed";
    final String LISTVIEW = "List view created";
    final String JSONENCODE = "Json encoded succesfuly";
    final String JSONFAIL = "Failed the encoding";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listed_movies_from_database);

        listView=(ListView) findViewById(R.id.lvMovies);

        Log.i(LISTVIEW,"Creation successfull");

        StrictMode.setThreadPolicy((new  StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
        CustomListView customListView = new CustomListView(this,movie_studio,movie_category,movie_name,movie_length,movie_rate);
        listView.setAdapter(customListView);
        ViewGroup headerView = (ViewGroup) getLayoutInflater().inflate(R.layout.header,listView,false);
        listView.addHeaderView(headerView);


    }

    @Override
    public void onBackPressed() {
        Intent backtomenu = new Intent(getApplicationContext(), Main_menu.class);
        startActivity(backtomenu);
        finish();
    }

    private void collectData()
    {
        try {
            URL url = new URL(urladdress);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            is = new BufferedInputStream(con.getInputStream());
            Log.i(GETREQ,"Request started");
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e(BADGETREQ,"Request failed");
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
            Log.i(FETCHG,"Fetch finished");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e(FETCHB,"Fetch failed");
        }

        try{
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;
            movie_studio = new String[ja.length()];
            movie_category = new String[ja.length()];
            movie_name = new String[ja.length()];
            movie_length = new String[ja.length()];
            movie_rate = new String[ja.length()];

            for(int i = 0; i<=ja.length(); i++){
                jo = ja.getJSONObject(i);
                movie_studio[i] = jo.getString("movie_studio");
                movie_category[i] = jo.getString("movie_category");
                movie_name[i] = jo.getString("movie_name");
                movie_length[i] = jo.getString("movie_length");
                movie_rate[i] = jo.getString("movie_rate");

                Log.i(JSONENCODE,"Data loaded");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(JSONFAIL,"Data load failed");
        }
    }
}