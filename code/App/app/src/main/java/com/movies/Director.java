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

public class Director extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner movie_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_director);

        TextView directorName = (TextView) findViewById(R.id.directorname);
        //TODO Movie neveket arrayba szedni is a spinner listával kiiratni
        movie_spinner = findViewById(R.id.movie_spinner);
        ArrayAdapter<CharSequence> studio = ArrayAdapter.createFromResource(this,R.array.Studios, android.R.layout.simple_spinner_dropdown_item);
        studio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        movie_spinner.setAdapter(studio);
        movie_spinner.setOnItemSelectedListener(this);

        MaterialButton addbtn = (MaterialButton) findViewById(R.id.adddirectorbtn);

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
                String name;
                name = String.valueOf(((TextView) findViewById(R.id.directorname)).getText());
                if(!name.equals("")) {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[1];
                            field[0] = "name";
                            String[] data = new String[1];
                            data[0] = name;
                            PutData putData = new PutData("http://192.168.0.172/Mobile_API/addDirector.php", "POST", field, data);
                            //cmd -> ipconfig -> ipv4 address
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Director added Success")){
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
                    Toast.makeText(getApplicationContext(),"All fields are required!", Toast.LENGTH_SHORT).show();
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