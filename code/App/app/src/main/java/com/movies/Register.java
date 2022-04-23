package com.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Register extends AppCompatActivity {


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView username =(TextView) findViewById(R.id.p_username);
        TextView passwd = (TextView) findViewById(R.id.p_password);
        TextView first_name = (TextView) findViewById(R.id.p_first_name);
        TextView last_name = (TextView) findViewById(R.id.p_last_name);
        TextView email = (TextView) findViewById(R.id.p_email);

        MaterialButton registerbtn = (MaterialButton) findViewById(R.id.registerbtn);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, passwd, first_name, last_name, email;
                username = String.valueOf(((TextView) findViewById(R.id.p_username)).getText());
                passwd = String.valueOf(((TextView) findViewById(R.id.p_password)).getText());
                first_name = String.valueOf(((TextView) findViewById(R.id.p_first_name)).getText());
                last_name = String.valueOf(((TextView) findViewById(R.id.p_last_name)).getText());
                email = String.valueOf(((TextView) findViewById(R.id.p_email)).getText());

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
                            PutData putData = new PutData("http://192.168.0.172/Mobile_API/signup.php", "POST", field, data);
                            //cmd -> ipconfig -> ipv4 address
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Sign up Success")){
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