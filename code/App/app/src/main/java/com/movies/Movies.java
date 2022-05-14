package com.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        universalPictures = new UniversalPictures();
        Pixar = new Pixar();
        TextView movieName =(TextView) findViewById(R.id.moviename);
        TextView length = (TextView) findViewById(R.id.length);

        category_spinner = findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> category = ArrayAdapter.createFromResource(this,R.array.Categorys, android.R.layout.simple_spinner_dropdown_item);
        category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_spinner.setAdapter(category);
        category_spinner.setOnItemSelectedListener(this);

        studio_spinner = findViewById(R.id.studio_spinner);
        ArrayAdapter<CharSequence> studio = ArrayAdapter.createFromResource(this,R.array.Studios, android.R.layout.simple_spinner_dropdown_item);
        category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studio_spinner.setAdapter(studio);
        studio_spinner.setOnItemSelectedListener(this);



        MaterialButton backbtn = (MaterialButton) findViewById(R.id.backbtn);


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main_menu.class);
                startActivity(intent);
                finish();
            }
        });


        MaterialButton addmoviebtn = (MaterialButton) findViewById(R.id.addmoviebtn);

            addmoviebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (studio_spinner.getSelectedItem().toString().trim().equals("UniversalPictures"))
                    {
                        if(category_spinner.getSelectedItem().toString().trim().equals("Comedy"))
                        {
                            comedyMovie = universalPictures.createComedyMovie(studio_spinner.getSelectedItem().toString(),category_spinner.getSelectedItem().toString(),movieName.getText().toString(),Integer.parseInt(length.getText().toString()));
                            Container.Movies.add(comedyMovie);
                        }
                        else
                        {
                            actionMovie = universalPictures.createActionMovie(studio_spinner.getSelectedItem().toString(),category_spinner.getSelectedItem().toString(),movieName.getText().toString(),Integer.parseInt(length.getText().toString()));
                            Container.Movies.add(actionMovie);
                        }
                    } else if (studio_spinner.getSelectedItem().toString().trim().equals("Pixar"))
                    {
                        if (category_spinner.getSelectedItem().toString().trim().equals("Comedy"))
                        {
                            comedyMovie = Pixar.createComedyMovie(studio_spinner.getSelectedItem().toString(), category_spinner.getSelectedItem().toString(), movieName.getText().toString(), Integer.parseInt(length.getText().toString()));
                            Container.Movies.add(comedyMovie);
                        }
                        else
                        {
                            actionMovie = Pixar.createActionMovie(studio_spinner.getSelectedItem().toString(), category_spinner.getSelectedItem().toString(), movieName.getText().toString(), Integer.parseInt(length.getText().toString()));
                            Container.Movies.add(actionMovie);
                        }
                    }
                    if(!movieName.equals("") && !length.equals("")) {
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
                                data[0] = studio_spinner.getSelectedItem().toString();
                                data[1] = category_spinner.getSelectedItem().toString();
<<<<<<< HEAD
                                data[2] = movieName.getText().toString();
                                data[3] = length.getText().toString();
                                PutData putData = new PutData("http://192.168.1.199/Mobile_API/Add_movie.php", "POST", field, data);
=======
                                data[2] = movieName.toString();
                                data[3] = length.toString();
                                PutData putData = new PutData(Config.showURL + "Add_movie.php", "POST", field, data);
>>>>>>> main
                                //cmd -> ipconfig -> ipv4 address
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();
                                        if (result.equals("Movie addded successfully")){
                                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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