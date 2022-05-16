package com.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Profile extends AppCompatActivity {

    final String MODIFYBTN = "Button created";
    final String BACKBTN = "Button created";
    final String PROFILELAYER = "Layer loaded";
    final String UPLOADGOOD = "Good data upload";
    final String UPLOADBAD = "Bad data upload";
    final String GOODREQ = "Good request handled";
    final String BADREQ = "Bad request handled";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.i(PROFILELAYER,"Profile layer loaded successfully!");

        TextView userid = (TextView) findViewById(R.id.userid);
        TextView username =(TextView) findViewById(R.id.username);
        TextView passwd = (TextView) findViewById(R.id.password);
        TextView first_name = (TextView) findViewById(R.id.first_name);
        TextView last_name = (TextView) findViewById(R.id.last_name);
        TextView email = (TextView) findViewById(R.id.email);

        MaterialButton modifybtn = (MaterialButton) findViewById(R.id.modifybtn);
        Log.i(MODIFYBTN,"Modify button created successfully!");
        MaterialButton backbtn = (MaterialButton) findViewById(R.id.backbtn);
        Log.i(BACKBTN,"Back button created successfully!");

        modifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, passwd, first_name, last_name, email;
                username = String.valueOf(((TextView) findViewById(R.id.username)).getText());
                passwd = String.valueOf(((TextView) findViewById(R.id.password)).getText());
                first_name = String.valueOf(((TextView) findViewById(R.id.first_name)).getText());
                last_name = String.valueOf(((TextView) findViewById(R.id.last_name)).getText());
                email = String.valueOf(((TextView) findViewById(R.id.email)).getText());

                if(!username.equals("") && !passwd.equals("") && !first_name.equals("") && !last_name.equals("") && !email.equals("")) {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[6];
                            field[0] = "id";
                            field[1] = "username";
                            field[2] = "passwd";
                            field[3] = "first_name";
                            field[4] = "last_name";
                            field[5] = "email";
                            String[] data = new String[6];
                            data[0] = "1";
                            data[1] = username;
                            data[2] = passwd;
                            data[3] = first_name;
                            data[4] = last_name;
                            data[5] = email;
                            PutData putData = new PutData(Config.showURL + "modify.php", "POST", field, data);
                            Log.i(GOODREQ,"Good request handled to the server");
                            //cmd -> ipconfig -> ipv4 address
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Modify Success")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Main_menu.class);
                                        startActivity(intent);
                                        finish();
                                        Log.i(UPLOADGOOD,"Data uploaded successfully!");
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), result,Toast.LENGTH_SHORT).show();
                                        Log.i(UPLOADBAD,"Data upload encountered some problems");
                                    }
                                }
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(),"All fields are required!", Toast.LENGTH_SHORT).show();
                    Log.i(BADREQ,"Bad request handled to the server");
                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main_menu.class);
                startActivity(intent);
                finish();
            }
        });
    }
}