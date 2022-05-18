package com.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class Settings extends AppCompatActivity {

    final String BACKBTN = "Button created";
    final String BACKTOMENU = "Back to main menu";
    final String DARKMODEBTN = "Button created";
    final String MODEON = "Mode switch";
    final String MODEOFF = "Mode switch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        MaterialButton backbtn = (MaterialButton) findViewById(R.id.backbtn);
        Log.i(BACKBTN,"Back button created successfully");

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Main_menu.class);
                startActivity(intent);
                finish();
                Log.i(BACKTOMENU,"Handled back to the main menu!");
            }
        });

        MaterialButton darkmodebtn = (MaterialButton) findViewById(R.id.darkmodebtn);
        Log.i(DARKMODEBTN,"Dark mode button created succesfully");

        SharedPreferences appSettingPrefs = getSharedPreferences("AppSettingPrefs", 0);
        SharedPreferences.Editor sharedPrefsEdit = appSettingPrefs.edit();
        Boolean isNightModeOn  = appSettingPrefs.getBoolean("NightMode", false);

        if(isNightModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            darkmodebtn.setText("Dark Mode Off");
            Log.i(MODEOFF,"Dark mode turned off");

        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            darkmodebtn.setText("Dark Mode On");
            Log.i(MODEON,"Dark mode turned on");
        }

        darkmodebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNightModeOn) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    sharedPrefsEdit.putBoolean("NightMode", false);
                    sharedPrefsEdit.apply();
                    darkmodebtn.setText("Dark Mode On");
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    sharedPrefsEdit.putBoolean("NightMode", true);
                    sharedPrefsEdit.apply();
                    darkmodebtn.setText("Dark Mode Off");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent backtomenu = new Intent(getApplicationContext(),Main_menu.class);
        startActivity(backtomenu);
        finish();
    }
}