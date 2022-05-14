package com.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class List extends AppCompatActivity {

    RequestQueue requestQueue1;
    String movie_studio, movie_category, movie_name, movie_length;

    private EditText e_movie_studio, e_movie_category, e_movie_name, e_movie_length;
    private TextInputLayout tmoviestudio, tmoviecategory, tmoviename, tmovielength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        requestQueue1 = Volley.newRequestQueue(getApplicationContext());
        tmoviestudio = (TextInputLayout) findViewById(R.id.tmoviestudio);
        tmoviecategory = (TextInputLayout) findViewById(R.id.tmoviecategory);
        tmoviename = (TextInputLayout) findViewById(R.id.tmoviename);
        tmovielength = (TextInputLayout) findViewById(R.id.tmovielength);
        e_movie_studio = (EditText) findViewById(R.id.e_movie_studio);
        e_movie_category = (EditText) findViewById(R.id.e_movie_category);
        e_movie_name = (EditText) findViewById(R.id.e_movie_name);
        e_movie_length = (EditText) findViewById(R.id.e_movie_length);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.showURL + "Show_movie.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(List.this, response, Toast.LENGTH_LONG).show();

                try {
                    JSONObject json = new JSONObject(response);
                    JSONArray movies = json.getJSONArray("movie");

                    for (int i = 0; i < movies.length(); i++) {
                        JSONObject movie = movies.getJSONObject(i);

                        movie_studio = movie.getString("movie_studio");
                        movie_category = movie.getString("movie_category");
                        movie_name = movie.getString("movie_name");
                        movie_length = movie.getString("movie_length");
                        e_movie_studio.setText(movie_studio);
                        e_movie_category.setText(movie_category);
                        e_movie_name.setText(movie_name);
                        e_movie_length.setText(movie_length);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return  params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}