package com.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;

public class Movies extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Studio universalPictures;
    Studio Pixar;
    ActionMovie actionMovie;
    ComedyMovie comedyMovie;

    private Spinner category_spinner;
    private Spinner studio_spinner;

    final String ACTIVITYMOVIES = "Activity loaded";
    final String UNIVERSALSTUDIO = "Universal studiocreated";
    final String PIXARSTUDIO = "Pixar studio created";
    final String ACTIONMOVIE = "Action movie created";
    final String COMEDYMOVIE = "Comedy movie created";
    final String CATEGORYSPINNER = "Category spinner inc";
    final String STUDIOSPINNER = "Studio spinner created";
    final String BACKBTN = "Button created";
    final String BACKTOMAINMENU = "Handled back to menu";
    final String ADDMOVIEBTN = "Button created";
    final String GOODREQ = "Good request handled";
    final String BADREQ  = "Bad request handled";
    final String GOODUPLOAD = "Files uploaded!";
    final String BADUPLOAD = "Files not uploaded";
    final String ITEMSELECTION = "Item selected";
    final String MOVIECREATED = "Movie created";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        Log.i(ACTIVITYMOVIES,"Movies Activity xml loaded successfully!");

        universalPictures = new UniversalPictures();
        Log.i(UNIVERSALSTUDIO,"Universal Pictures created");
        Pixar = new Pixar();
        Log.i(PIXARSTUDIO,"Pixar created");
        TextView movieName =(TextView) findViewById(R.id.moviename);
        TextView length = (TextView) findViewById(R.id.length);

        category_spinner = findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> category = ArrayAdapter.createFromResource(this,R.array.Categorys, android.R.layout.simple_spinner_dropdown_item);
        category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_spinner.setAdapter(category);
        category_spinner.setOnItemSelectedListener(this);
        Log.i(CATEGORYSPINNER,"Category spinner incremented successfully!");

        studio_spinner = findViewById(R.id.studio_spinner);
        ArrayAdapter<CharSequence> studio = ArrayAdapter.createFromResource(this,R.array.Studios, android.R.layout.simple_spinner_dropdown_item);
        category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studio_spinner.setAdapter(studio);
        studio_spinner.setOnItemSelectedListener(this);
        Log.i(STUDIOSPINNER, "Studio spinner created successfully!");



        MaterialButton backbtn = (MaterialButton) findViewById(R.id.backbtn);
        Log.i(BACKBTN, "Back button created successfully!");

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main_menu.class);
                startActivity(intent);
                finish();
                Log.i(BACKTOMAINMENU,"Back to main menu intent handled");
            }
        });


        MaterialButton addmoviebtn = (MaterialButton) findViewById(R.id.addmoviebtn);
        Log.i(ADDMOVIEBTN,"Add movie button created succsesfully!");

            addmoviebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (studio_spinner.getSelectedItem().toString().trim().equals("UniversalPictures"))
                    {
                        Log.i(UNIVERSALSTUDIO, "Universal Studio picked during the movie creation");
                        if(category_spinner.getSelectedItem().toString().trim().equals("Comedy"))
                        {
                            Log.i(COMEDYMOVIE,"Comedy movie category picked during the movie creation");
                            comedyMovie = universalPictures.createComedyMovie(studio_spinner.getSelectedItem().toString(),category_spinner.getSelectedItem().toString(),movieName.getText().toString(),Integer.parseInt(length.getText().toString()));
                            Container.Movies.add(comedyMovie);
                            Log.i(MOVIECREATED, "Movie created by the given data");
                        }
                        else
                        {
                            actionMovie = universalPictures.createActionMovie(studio_spinner.getSelectedItem().toString(),category_spinner.getSelectedItem().toString(),movieName.getText().toString(),Integer.parseInt(length.getText().toString()));
                            Container.Movies.add(actionMovie);
                            Log.i(UNIVERSALSTUDIO, "Universal Studio picked during the movie creation");
                            Log.i(ACTIONMOVIE, "Action movie category picked during the movie creation");
                            Log.i(MOVIECREATED, "Movie created by the given data");
                        }
                    } else if (studio_spinner.getSelectedItem().toString().trim().equals("Pixar"))
                        Log.i(PIXARSTUDIO, "Pixar Studio picked during the movie creation");
                    {
                        if (category_spinner.getSelectedItem().toString().trim().equals("Comedy"))
                        {
                            Log.i(COMEDYMOVIE,"Comedy movie category picked during the movie creation");
                            comedyMovie = Pixar.createComedyMovie(studio_spinner.getSelectedItem().toString(), category_spinner.getSelectedItem().toString(), movieName.getText().toString(), Integer.parseInt(length.getText().toString()));
                            Container.Movies.add(comedyMovie);
                            Log.i(MOVIECREATED, "Movie created by the given data");
                        }
                        else
                        {
                            actionMovie = Pixar.createActionMovie(studio_spinner.getSelectedItem().toString(), category_spinner.getSelectedItem().toString(), movieName.getText().toString(), Integer.parseInt(length.getText().toString()));
                            Container.Movies.add(actionMovie);
                            Log.i(ACTIONMOVIE,"Action movie category picked during the movie creation");
                            Log.i(MOVIECREATED, "Movie created by the given data");
                        }
                    }
                    AddMovie(studio_spinner.getSelectedItem().toString(), category_spinner.getSelectedItem().toString(), movieName.getText().toString(), length.getText().toString());

                }
            });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(),choice,Toast.LENGTH_LONG).show();
        Log.i(ITEMSELECTION,"Item selected");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void AddMovie(String studio, String category, String name, String lenght){
        if(!name.equals("") && !lenght.equals("")) {
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = new String[4];
                    field[0] = "movie_studio";
                    field[1] = "movie_category";
                    field[2] = "movie_name";
                    field[3] = "movie_length";
                    String[] data = new String[4];
                    data[0] = studio;
                    data[1] = category;
                    data[2] = name;
                    data[3] = lenght;
                    PutData putData = new PutData(Config.showURL + "Add_movie.php", "POST", field, data);
                    Log.i(GOODREQ,"Good request handled to the server");
                    //cmd -> ipconfig -> ipv4 address
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Movie addded successfully")){
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Main_menu.class);
                                startActivity(intent);
                                finish();
                                Log.i(GOODUPLOAD,"Data uploaded successfully!");
                            }
                            else {
                                Toast.makeText(getApplicationContext(), result,Toast.LENGTH_SHORT).show();
                                Log.i(BADUPLOAD,"Data upload encountered some problems");
                            }
                        }
                    }
                }
            });
            Log.i(BADREQ,"Bad request handled to the server");
        }
    }
}