package com.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Movies extends AppCompatActivity {

    Studio universalPictures;
    ActionMovie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        universalPictures = new UniversalPictures();
        TextView movieName =(TextView) findViewById(R.id.moviename);
        TextView length = (TextView) findViewById(R.id.length);

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
                TextView movieName =(TextView) findViewById(R.id.moviename);
                TextView length = (TextView) findViewById(R.id.length);
                if(!movieName.equals("") && !length.equals("")) {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            movie = universalPictures.createActionMovie(movieName.getText().toString(),Integer.parseInt(length.getText().toString()));
                            String[] field = new String[5];
                            field[0] = "moviename";
                            field[1] = "length";
                            String[] data = new String[5];
                            data[0] = movieName.toString();
                            data[1] = length.toString();
                            PutData putData = new PutData("http://192.168.1.199/Mobile_API/Add_movie.php", "POST", field, data);
                            //cmd -> ipconfig -> ipv4 address
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Movie Added successfully!")){
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
                else {
                    Toast.makeText(getApplicationContext(),"All fields are required!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


}