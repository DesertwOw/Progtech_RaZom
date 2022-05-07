package com.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class List extends AppCompatActivity {


    RecyclerView recview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recview=findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        processdata();
    }

    public void processdata()
    {
        Call<java.util.List<responseModel>> call=apicontroller.getInstance().getapi().getdata();

        call.enqueue(new Callback<java.util.List<responseModel>>() {
            @Override
            public void onResponse(Call<java.util.List<responseModel>> call, Response<java.util.List<responseModel>> response) {
                java.util.List<responseModel> data = response.body();
                myadapter adapter = new myadapter(data);
                recview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<java.util.List<responseModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}