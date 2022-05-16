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
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;
import java.util.List;

public class Directors extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner movie_spinner;
    private List<String> m = new ArrayList<String>();

    final String MOVIELFILL = "List created succesfuly";
    final String MOVIESPINNER = "Movie spinner set up";
    final String ADDBTN = "Button created";
    final String BACKBTN = "Button created";
    final String BACKTOMENU = "Handling back to menu";
    final String UPLOADGOOD = "Data uploading";
    final String UPLOADBAD = "Data upload failed";
    final String GOODREQ = "Good request handled";
    final String BADREQ = "Bad request handled";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_director);

        TextView directorName = (TextView) findViewById(R.id.directorname);
        //TODO Movie neveket arrayba szedni is a spinner listával kiiratni
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
                String name;
                name = String.valueOf(((TextView) findViewById(R.id.directorname)).getText());
                for (int i = 0; i < Container.Movies.size(); i++){
                    if (Container.Movies.get(i).getName() == movie_spinner.getSelectedItem().toString().trim()){
                        MovieBase movie = Container.Movies.get(i);
                        Director director = new Director(movie);
                        director.setDirector_name(name);
                        Container.Movies.set(i, director);
                    }
                }
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
                                    if (result.equals("Directors added Success")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Main_menu.class);
                                        startActivity(intent);
                                        finish();
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
}