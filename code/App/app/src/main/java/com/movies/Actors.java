package com.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;
import java.util.List;


public class Actors extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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

        //TODO Movie neveket arrayba szedni is a spinner listával kiiratni
        for (int i = 0; i < Container.Movies.size(); i++){
            String name = Container.Movies.get(i).getName();
            m.add(name);
        }
        movie_spinner = findViewById(R.id.movie_spinner);
        ArrayAdapter<String> movie = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, m);
        movie.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        movie_spinner.setAdapter(movie);
        movie_spinner.setOnItemSelectedListener(this);

        actorGender_spinner = findViewById(R.id.actorGender_spinner);
        ArrayAdapter<CharSequence> gender = ArrayAdapter.createFromResource(this,R.array.Gender, android.R.layout.simple_spinner_dropdown_item);
        gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        actorGender_spinner.setAdapter(gender);
        actorGender_spinner.setOnItemSelectedListener(this);

        MaterialButton addbtn = (MaterialButton) findViewById(R.id.addactorbtn);

        MaterialButton backbtn = (MaterialButton) findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main_menu.class);
                startActivity(intent);
                finish();
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, age, gender, playedrole;
                name = String.valueOf(((TextView) findViewById(R.id.actorname)).getText());
                age = String.valueOf(((TextView) findViewById(R.id.actorage)).getText());
                gender = actorGender_spinner.getSelectedItem().toString().trim();
                playedrole = playedRole_spinner.getSelectedItem().toString().trim();
                for (int i = 0; i < Container.Movies.size(); i++){
                    if (Container.Movies.get(i).getName() == movie_spinner.getSelectedItem().toString().trim()){
                        MovieBase movie = Container.Movies.get(i);
                        Actor actor = new Actor(movie);
                        actor.setActor_name(name);
                        actor.setAge(Integer.parseInt(age));
                        actor.setGender(Gender.valueOf(gender));
                        actor.setPlayedRole(Movie_playedRole.valueOf(playedrole));
                        Container.Movies.set(i, actor);
                    }
                }
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
                            //cmd -> ipconfig -> ipv4 address
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Actor added Success")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Main_menu.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(),"All fields are required! Age has to be a number", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(),choice,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }
}


