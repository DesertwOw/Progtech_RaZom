package com.movies;

import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Container {

    protected static List<MovieBase> Movies = new ArrayList<MovieBase>();

    static String urladdress = Config.showURL + "fetch_movie.php";
    static String[] movie_studio;
    static String[] movie_category;
    static String[] movie_name;
    static String[] movie_length;
    static BufferedInputStream is;
    static String line = null;
    static String result = null;

    static final String FETCHG = "Fetch request good";
    static final String FETCHB = "Fetch request failed";
    static final String GETREQ = "Get request started";
    static final String BADGETREQ = "Get request failed";
    static final String JSONENCODE = "Json encoded succesfuly";
    static final String JSONFAIL = "Failed the encoding";

    protected static void collectData()
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

            for(int i = 0; i<=ja.length(); i++){
                jo = ja.getJSONObject(i);
                movie_studio[i] = jo.getString("movie_studio");
                movie_category[i] = jo.getString("movie_category");
                movie_name[i] = jo.getString("movie_name");
                movie_length[i] = jo.getString("movie_length");

                Log.i(JSONENCODE,"Data loaded");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(JSONFAIL,"Data load failed");
        }
    }

    protected static void fillContainer(String studio, String title, String category, String lenght){
        Movies.clear();
        for (int i = 0; i < movie_studio.length; i++){
            studio = movie_studio[i];
            category = movie_category[i];
            title = movie_name[i];
            lenght = movie_length[i];
           ActionMovie temp = new ActionMovie(studio, category, title, Integer.parseInt(lenght));
           Movies.add(temp);
        }
    }
}
