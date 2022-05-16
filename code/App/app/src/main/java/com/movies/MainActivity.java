package com.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    final String FIELDS = "Field creation/modified";
    final String LOGINREQG = "Request sent";
    final String LOGINREQB = "Login failed";
    final String BADCRED = "Bad credentials";
    final String GOODCRED = "Good credentials";
    final String BTNS = "Buttons created";
    final String TOREGIST = "Move to register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView username =(TextView) findViewById(R.id.p_username);
        Log.i("com.movies.MainActivity","username created");
        Log.i(FIELDS,"Username field created");
        TextView passwd =(TextView) findViewById(R.id.p_password);
        Log.i(FIELDS,"Password field created");



        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        Log.i(BTNS,"Login button crated");
        MaterialButton registerbtn = (MaterialButton) findViewById(R.id.registerbtn);
        Log.i(BTNS,"Register button crated");




        //admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, passwd;
                username = String.valueOf(((TextView) findViewById(R.id.p_username)).getText());
                passwd = String.valueOf(((TextView) findViewById(R.id.p_password)).getText());

                if(!username.equals("") && !passwd.equals("") ) {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "passwd";;
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = passwd;
                            Log.i(LOGINREQG,"Request to login");

                            PutData putData = new PutData(Config.showURL + "login.php", "POST", field, data);
                            //cmd -> ipconfig -> ipv4 address
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Login Success")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Main_menu.class);
                                        startActivity(intent);
                                        finish();
                                        Log.i(GOODCRED,"Login successfull");
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), result,Toast.LENGTH_SHORT).show();
                                        Log.e(BADCRED,"Login Failed due to bad credentials");
                                    }
                                }
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(),"All fields are required!", Toast.LENGTH_SHORT).show();
                    Log.e(LOGINREQB,"Request handled badly");
                }
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerbtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Register.class)));
                Log.i(TOREGIST,"Handled to the register page!");
            }
        });

        String filePath = Environment.getExternalStorageDirectory() + "/movies_log.txt";
        try {
            Runtime.getRuntime().exec(new String[]{"logcat", "-f", filePath, "Login Declaration:V", "*:S"});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}