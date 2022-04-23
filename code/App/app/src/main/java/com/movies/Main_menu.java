package com.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Main_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.menuProfile){
                    Toast.makeText(Main_menu.this, "My Profile", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Main_menu.this, Profile.class);
                    startActivity(intent);
                    finish();


                }
                else if(id == R.id.menuSettings){
                    Toast.makeText(Main_menu.this, "Settings", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Main_menu.this, Settings.class); //TODO
                    startActivity(intent);
                    finish();

                }
                else if(id == R.id.menuMovies){
                    Toast.makeText(Main_menu.this, "Movies", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Main_menu.this, Movies.class); //TODO
                    startActivity(intent);
                    finish();

                }
                else if(id == R.id.menuStars){
                    Toast.makeText(Main_menu.this, "Stars", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Main_menu.this, Stars.class); //TODO
                    startActivity(intent);
                    finish();

                }

                return true;
            }
        });

    }
}