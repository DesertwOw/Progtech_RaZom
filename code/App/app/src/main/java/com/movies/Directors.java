package com.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.movies.Decorators.Director;
import com.movies.Decorators.MovieBase;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

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

public class Directors extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner movie_spinner;
    private List<String> m = new ArrayList<String>();

    String urladdress = Config.showURL + "getDirectorID.php";
    String[] director_id;
    String[] director_name;
    BufferedInputStream is;
    String line = null;
    String result = null;
    final String MOVIELFILL = "List created succesfuly";
    final String MOVIESPINNER = "Movie spinner set up";
    final String ADDBTN = "Button created";
    final String BACKBTN = "Button created";
    final String BACKTOMENU = "Handling back to menu";
    final String UPLOADGOOD = "Data uploading";
    final String UPLOADBAD = "Data upload failed";
    final String GOODREQ = "Good request handled";
    final String BADREQ = "Bad request handled";
    final String FETCHG = "Fetch request good";
    final String FETCHB = "Fetch request failed";
    final String GETREQ = "Get request started";
    final String BADGETREQ = "Get request failed";
    final String JSONENCODE = "Json encoded succesfuly";
    final String JSONFAIL = "Failed the encoding";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_director);

        TextView directorName = (TextView) findViewById(R.id.directorname);


        for (int i = 0; i < Container.Movies.size(); i++){
            String name = Container.Movies.get(i).getName();
            m.add(name);
            Log.i(MOVIELFILL,"List filled with good data");

        }
        movie_spinner = findViewById(R.id.movie_spinner);
        ArrayAdapter<String> movie = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, m);
        movie.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        movie_spinner.setAdapter(movie);
        movie_spinner.setOnItemSelectedListener(this);
        Log.i(MOVIESPINNER,"Spinner created successfully");

        MaterialButton addbtn = (MaterialButton) findViewById(R.id.adddirectorbtn);
        Log.i(ADDBTN,"Add button created successfully");

        MaterialButton backbtn = (MaterialButton) findViewById(R.id.backbtn);
        Log.i(BACKBTN,"Back button created successfully");

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main_menu.class);
                startActivity(intent);
                finish();
                Log.i(BACKTOMENU,"Back to menu intent works");
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, moviename;
                name = String.valueOf(((TextView) findViewById(R.id.directorname)).getText());
                moviename = movie_spinner.getSelectedItem().toString().trim();
                for (int i = 0; i < Container.Movies.size(); i++){
                    if (Container.Movies.get(i).getName() == moviename){
                        MovieBase movie = Container.Movies.get(i);
                        Director director = new Director(movie);
                        director.setDirector_name(name);
                        Container.Movies.set(i, director);
                    }
                }
                AddDirector(name);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        collectData();
                        addDirectionMovie(name, moviename);
                    }
                }, 5000);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        //Toast.makeText(getApplicationContext(),choice,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void AddDirector(String name){
        if(!name.equals("")) {
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = new String[1];
                    field[0] = "name";
                    String[] data = new String[1];
                    data[0] = name;
                    PutData putData = new PutData(Config.showURL + "addDirector.php", "POST", field, data);
                    Log.i(GOODREQ,"Good request handled to the server");
                    //cmd -> ipconfig -> ipv4 address
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Director added Success")){
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                Log.i(UPLOADGOOD,"Data uploaded successfully");
                            }
                            else {
                                Toast.makeText(getApplicationContext(), result,Toast.LENGTH_SHORT).show();
                                Log.e(UPLOADBAD,"Data upload failed");
                            }
                        }
                    }
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(),"All fields are required!", Toast.LENGTH_SHORT).show();
            Log.e(BADREQ,"Bad request handled to the server");
        }
    }

    @Override
    public void onBackPressed() {
        Intent backtomenu = new Intent(getApplicationContext(),Main_menu.class);
        startActivity(backtomenu);
        finish();
    }

    public void collectData()
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
            director_id = new String[ja.length()];
            director_name = new String[ja.length()];

            for(int i = 0; i<=ja.length(); i++){
                jo = ja.getJSONObject(i);
                director_id[i] = jo.getString("director_id");
                director_name[i] = jo.getString("director_name");

                Log.i(JSONENCODE,"Data loaded");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(JSONFAIL,"Data load failed");
        }
    }

    public void addDirectionMovie(String name, String movie){
        String dir_id = "";
        String mov_id = "";
        for(int i = 0; i < director_id.length; i++){
            if(director_name[i].equals(name)){
                dir_id = director_id[i];
                break;
            }
        }
        for(int i = 0; i < Container.movie_name.length; i++){
            if(Container.movie_name[i].equals(movie)){
                mov_id = Container.movie_id[i];
                break;
            }
        }
        Handler handler = new Handler();
        String finalDir_id = dir_id;
        String finalMov_id = mov_id;
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[2];
                field[0] = "dir_id";
                field[1] = "movie_id";
                String[] data = new String[2];
                data[0] = finalDir_id;
                data[1] = finalMov_id;
                PutData putData = new PutData(Config.showURL + "addDirectedMovie.php", "POST", field, data);
                Log.i(GOODREQ,"Request to the server is good");
                //cmd -> ipconfig -> ipv4 address
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("Direction added Success")){
                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Main_menu.class);
                            startActivity(intent);
                            finish();
                            Log.i(UPLOADGOOD,"Upload function is working properly");
                        }
                        else {
                            Toast.makeText(getApplicationContext(), result,Toast.LENGTH_SHORT).show();
                            Log.e(UPLOADBAD,"Upload function encountered errors");
                        }
                    }
                }
            }
        });


    }
}