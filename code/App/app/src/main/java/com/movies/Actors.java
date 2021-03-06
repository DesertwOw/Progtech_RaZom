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
import com.movies.Decorators.Actor;
import com.movies.Decorators.MovieBase;
import com.movies.Enums.Gender;
import com.movies.Enums.Movie_playedRole;
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


public class Actors extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String urladdress = Config.showURL + "getActorID.php";
    String[] actor_id;
    String[] actor_name;
    BufferedInputStream is;
    String line = null;
    String result = null;
    final String ACTORSLAYOUT = "Actors layout loading";
    final String PlAYEDROLESPIN = "P_r spinner load";
    final String MOVIESLIST = "Movies list inc";
    final String MOVIESPINNER = "Movie spinner loading";
    final String ACTORGENDERSPINNER = "A_g spinner load";
    final String ADDBTN = "Add_button set up";
    final String BACKBTN = "Back_button set up";
    final String MainMenuBack = "Heading back to";
    final String UPLOADFUNCTION = "Upload function good";
    final String UPLOADERROR = "Upload function bad";
    final String GOODREQ = "Request handled well";
    final String BADREQ = "Request handled badly";
    final String FETCHG = "Fetch request good";
    final String FETCHB = "Fetch request failed";
    final String GETREQ = "Get request started";
    final String BADGETREQ = "Get request failed";
    final String JSONENCODE = "Json encoded succesfuly";
    final String JSONFAIL = "Failed the encoding";


    private Spinner movie_spinner;
    private Spinner playedRole_spinner;
    private Spinner actorGender_spinner;
    private List<String> m = new ArrayList<String>();
    private String[] arrayPLayRoled = new String[] {Movie_playedRole.Main_Character.name(), Movie_playedRole.Guest_Star.name(), Movie_playedRole.Supporting_Actor.name(), Movie_playedRole.Background_Actor.name()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actors);
        TextView actorName = (TextView) findViewById(R.id.actorname);
        TextView actorAge = (TextView) findViewById(R.id.actorage);

        playedRole_spinner = findViewById(R.id.playedrole_spinner);
        ArrayAdapter<String> playedRole = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, arrayPLayRoled );
        playedRole.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playedRole_spinner.setAdapter(playedRole);
        playedRole_spinner.setOnItemSelectedListener(this);

        Log.i(ACTORSLAYOUT,"Actors layout set up successfully");
        Log.i(PlAYEDROLESPIN,"Played_role spinner set up successfully");

        for (int i = 0; i < Container.Movies.size(); i++){
            String name = Container.Movies.get(i).getName();
            m.add(name);
            Log.i(MOVIESLIST,"Movies List filled with data");
        }
        movie_spinner = findViewById(R.id.movie_spinner);
        ArrayAdapter<String> movie = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, m);
        movie.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        movie_spinner.setAdapter(movie);
        movie_spinner.setOnItemSelectedListener(this);
        Log.i(MOVIESPINNER,"Movies spinner set up successfully");

        actorGender_spinner = findViewById(R.id.actorGender_spinner);
        ArrayAdapter<CharSequence> gender = ArrayAdapter.createFromResource(this,R.array.Gender, android.R.layout.simple_spinner_dropdown_item);
        gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        actorGender_spinner.setAdapter(gender);
        actorGender_spinner.setOnItemSelectedListener(this);
        Log.i(ACTORGENDERSPINNER,"Actors_spinner set up successfully");

        MaterialButton addbtn = (MaterialButton) findViewById(R.id.addactorbtn);
        Log.i(ADDBTN,"Add button created");
        MaterialButton backbtn = (MaterialButton) findViewById(R.id.backbtn);
        Log.i(BACKBTN, "Back button created");

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main_menu.class);
                startActivity(intent);
                finish();
                Log.i(MainMenuBack,"Sent back to main menu successfully");
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, age, gender, playedrole, moviename;
                name = String.valueOf(((TextView) findViewById(R.id.actorname)).getText());
                age = String.valueOf(((TextView) findViewById(R.id.actorage)).getText());
                gender = actorGender_spinner.getSelectedItem().toString().trim();
                playedrole = playedRole_spinner.getSelectedItem().toString().trim();
                moviename = movie_spinner.getSelectedItem().toString().trim();
                for (int i = 0; i < Container.Movies.size(); i++){
                    if (Container.Movies.get(i).getName() == moviename){
                        MovieBase movie = Container.Movies.get(i);
                        Actor actor = new Actor(movie);
                        actor.setActor_name(name);
                        actor.setAge(Integer.parseInt(age));
                        actor.setGender(Gender.valueOf(gender));
                        actor.setPlayedRole(Movie_playedRole.valueOf(playedrole));
                        Container.Movies.set(i, actor);
                    }
                }
                AddActor(name, age, gender);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        collectData();
                        addActInMovie(name, moviename, playedrole);
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

    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    public void AddActor(String name, String age, String gender){
        if(!name.equals("") && !age.equals("") && !gender.equals("") && (isNumeric(age) && Integer.parseInt(age) > 0)) {
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = new String[3];
                    field[0] = "name";
                    field[1] = "age";
                    field[2] = "gender";
                    String[] data = new String[3];
                    data[0] = name;
                    data[1] = age;
                    data[2] = gender;
                    PutData putData = new PutData(Config.showURL + "addActor.php", "POST", field, data);
                    Log.i(GOODREQ,"Request to the server is good");
                    //cmd -> ipconfig -> ipv4 address
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Actor added Success")){
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                Log.i(UPLOADFUNCTION,"Upload function is working properly");
                            }
                            else {
                                Toast.makeText(getApplicationContext(), result,Toast.LENGTH_SHORT).show();
                                Log.e(UPLOADERROR,"Upload function encountered errors");
                            }
                        }
                    }
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(),"All fields are required! Age has to be a number", Toast.LENGTH_SHORT).show();
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
            actor_id = new String[ja.length()];
            actor_name = new String[ja.length()];

            for(int i = 0; i<=ja.length(); i++){
                jo = ja.getJSONObject(i);
                actor_id[i] = jo.getString("actor_id");
                actor_name[i] = jo.getString("actor_name");

                Log.i(JSONENCODE,"Data loaded");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(JSONFAIL,"Data load failed");
        }
    }

    public void addActInMovie(String name, String movie, String playedRole){
        String act_id = "";
        String mov_id = "";
        for(int i = 0; i < actor_id.length; i++){
            if(actor_name[i].equals(name)){
                act_id = actor_id[i];
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
        String finalAct_id = act_id;
        String finalMov_id = mov_id;
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[3];
                field[0] = "actor_id";
                field[1] = "movie_id";
                field[2] = "played_role";
                String[] data = new String[3];
                data[0] = finalAct_id;
                data[1] = finalMov_id;
                data[2] = playedRole;
                PutData putData = new PutData(Config.showURL + "addActInMovie.php", "POST", field, data);
                Log.i(GOODREQ,"Request to the server is good");
                //cmd -> ipconfig -> ipv4 address
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("Played role added Success")){
                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Main_menu.class);
                            startActivity(intent);
                            finish();
                            Log.i(UPLOADFUNCTION,"Upload function is working properly");
                        }
                        else {
                            Toast.makeText(getApplicationContext(), result,Toast.LENGTH_SHORT).show();
                            Log.e(UPLOADERROR,"Upload function encountered errors");
                        }
                    }
                }
            }
        });


    }

}


