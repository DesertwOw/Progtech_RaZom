package com.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView userid = (TextView) findViewById(R.id.userid);
        TextView username =(TextView) findViewById(R.id.username);
        TextView passwd = (TextView) findViewById(R.id.password);
        TextView first_name = (TextView) findViewById(R.id.first_name);
        TextView last_name = (TextView) findViewById(R.id.last_name);
        TextView email = (TextView) findViewById(R.id.email);

        MaterialButton modifybtn = (MaterialButton) findViewById(R.id.modifybtn);
        MaterialButton backbtn = (MaterialButton) findViewById(R.id.backbtn);

        modifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, passwd, first_name, last_name, email;
                username = String.valueOf(((TextView) findViewById(R.id.username)).getText());
                passwd = String.valueOf(((TextView) findViewById(R.id.password)).getText());
                first_name = String.valueOf(((TextView) findViewById(R.id.first_name)).getText());
                last_name = String.valueOf(((TextView) findViewById(R.id.last_name)).getText());
                email = String.valueOf(((TextView) findViewById(R.id.email)).getText());

               // if(!username.equals("") && !passwd.equals("") && !first_name.equals("") && !last_name.equals("") && !email.equals("")) {
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
                            data[1] = "balint05";
                            data[2] = "2000";
                            data[3] = "Bálint";
                            data[4] = "Zombori";
                            data[5] = "zbalint00@gmail.com";
                            PutData putData = new PutData("http://192.168.0.172/Mobile_API/modify.php", "POST", field, data);
                            //cmd -> ipconfig -> ipv4 address
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Modify Success")){
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
               // }
               // else {
                    //Toast.makeText(getApplicationContext(),"All fields are required!", Toast.LENGTH_SHORT).show();
               // }
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