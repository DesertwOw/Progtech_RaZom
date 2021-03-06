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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Profile extends AppCompatActivity {

    String urladdress = Config.showURL + "getProfile.php";
    String[] Userid;
    String[] Username;
    String[] Firstname;
    String[] Lastname;
    String[] Email;
    BufferedInputStream is;
    String line = null;
    String result = null;

    final String BACKBTN = "Button created";
    final String PROFILELAYER = "Layer loaded";
    final String UPLOADGOOD = "Good data upload";
    final String UPLOADBAD = "Bad data upload";
    final String GOODREQ = "Good request handled";
    final String BADREQ = "Bad request handled";
    final String FETCHG = "Fetch request good";
    final String FETCHB = "Fetch request failed";
    final String GETREQ = "Get request started";
    final String BADGETREQ = "Get request failed";
    final String JSONENCODE = "Json encoded succesfuly";
    final String JSONFAIL = "Failed the encoding";

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
        collectData();
        getDatas();
        userid.setText(User.userid);
        username.setText(User.username);
        passwd.setText(User.password);
        first_name.setText(User.firstname);
        last_name.setText(User.lastname);
        email.setText(User.email);

        MaterialButton backbtn = (MaterialButton) findViewById(R.id.backbtn);
        Log.i(BACKBTN,"Back button created successfully!");


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main_menu.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void collectData()
    {
        try {
            URL url = new URL(urladdress);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            is = new BufferedInputStream(con.getInputStream());
            Log.i(GETREQ,"Request started");
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e(BADGETREQ,"Request failed");
        }
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            while((line=br.readLine()) != null){
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();
            Log.i(FETCHG,"Fetch finished");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e(FETCHB,"Fetch failed");
        }

        try{
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;
            Userid = new String[ja.length()];
            Username = new String[ja.length()];
            Firstname = new String[ja.length()];
            Lastname = new String[ja.length()];
            Email = new String[ja.length()];

            for(int i = 0; i<=ja.length(); i++){
                jo = ja.getJSONObject(i);
                Userid[i] = jo.getString("id");
                Username[i] = jo.getString("username");
                Firstname[i] = jo.getString("first_name");
                Lastname[i] = jo.getString("last_name");
                Email[i] = jo.getString("email");

                Log.i(JSONENCODE,"Data loaded");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(JSONFAIL,"Data load failed");
        }
    }

    private void getDatas(){
        for(int i = 0; i < Userid.length; i++){
            if(User.username.equals(Username[i])){
                User.userid = Userid[i];
                User.firstname = Firstname[i];
                User.lastname = Lastname[i];
                User.email = Email[i];
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent backtomenu = new Intent(getApplicationContext(),Main_menu.class);
        startActivity(backtomenu);
        finish();
    }
}