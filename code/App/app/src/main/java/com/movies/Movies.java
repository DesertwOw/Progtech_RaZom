package com.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class Movies extends AppCompatActivity {

    Studio universalPictures;
    ActionMovie movie;
    TextView movieName;
    TextView length;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        universalPictures = new UniversalPictures();
        TextView movieName =(TextView) findViewById(R.id.moviename);
        TextView length = (TextView) findViewById(R.id.length);

        MaterialButton addmoviebtn = (MaterialButton) findViewById(R.id.addmoviebtn);

        addmoviebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movie = universalPictures.createActionMovie(movieName.getText().toString(),Integer.parseInt(length.getText().toString()));
            }
        });



    }


}