package com.movies.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.movies.Config;
import com.movies.MainActivity;
import com.movies.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Register extends AppCompatActivity {

    final String REGISTERBUTTON = "Button created";
    final String REGISTERLAYER = "Layer loaded";
    final String UPLOADGOOD = "Good data upload";
    final String UPLOADBAD = "Bad data upload";
    final String GOODREQ = "Good request handled";
    final String BADREQ = "Bad request handled";



    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.i(REGISTERLAYER,"Register layer loaded successfully!");

        TextView username =(TextView) findViewById(R.id.p_username);
        TextView passwd = (TextView) findViewById(R.id.p_password);
        TextView first_name = (TextView) findViewById(R.id.p_first_name);
        TextView last_name = (TextView) findViewById(R.id.p_last_name);
        TextView email = (TextView) findViewById(R.id.p_email);

        MaterialButton registerbtn = (MaterialButton) findViewById(R.id.registerbtn);
        Log.i(REGISTERBUTTON,"Register button created successfully!");

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, passwd, first_name, last_name, email;
                username = String.valueOf(((TextView) findViewById(R.id.p_username)).getText());
                passwd = String.valueOf(((TextView) findViewById(R.id.p_password)).getText());
                first_name = String.valueOf(((TextView) findViewById(R.id.p_first_name)).getText());
                last_name = String.valueOf(((TextView) findViewById(R.id.p_last_name)).getText());
                email = String.valueOf(((TextView) findViewById(R.id.p_email)).getText());
                Register(username, passwd, first_name, last_name, email);
            }
        });

    }

    private void Register(String username, String passwd, String first_name, String last_name, String email){
        if(!username.equals("") && !passwd.equals("") && !first_name.equals("") && !last_name.equals("") && !email.equals("")) {
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = new String[5];
                    field[0] = "username";
                    field[1] = "passwd";
                    field[2] = "first_name";
                    field[3] = "last_name";
                    field[4] = "email";
                    String[] data = new String[5];
                    data[0] = username;
                    data[1] = passwd;
                    data[2] = first_name;
                    data[3] = last_name;
                    data[4] = email;
                    PutData putData = new PutData(Config.showURL + "signup.php", "POST", field, data);
                    Log.i(GOODREQ,"Good request handled to the server");
                    //cmd -> ipconfig -> ipv4 address
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Sign up Success")){
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
}