package com.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Main_menu extends AppCompatActivity {

    final String ActivityLoad = "Main menu xml loaded";
    final String ImageMenuLoad = "Image menu xml loaded";
    final String DrawerLayoutLoad = "Drawer xml loaded";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Log.i(ActivityLoad,"Main menu loaded Successfully");

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        Container.collectData();
        String studio = null;
        String title = null;
        String category = null;
        String length = null;
        Container.fillContainer(studio, title,category, length);
        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        Log.i(DrawerLayoutLoad,"Drawer loaded Successfully");

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                drawerLayout.openDrawer(GravityCompat.START);
            }

        });
        Log.i(ImageMenuLoad,"Image menu loaded Successfully");
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
                    Intent intent = new Intent(Main_menu.this, Settings.class);
                    startActivity(intent);
                    finish();

                }
                else if(id == R.id.menuMovies){
                    Toast.makeText(Main_menu.this, "Movies", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Main_menu.this, Movies.class);
                    startActivity(intent);
                    finish();

                }
                else if(id == R.id.menuListMovies){
                    Toast.makeText(Main_menu.this, "Movies", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Main_menu.this, Listed_movies_from_database.class);
                    startActivity(intent);
                    finish();

                }
                else if(id == R.id.menuStars) {
                    Toast.makeText(Main_menu.this, "Stars", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Main_menu.this, Stars.class);
                    startActivity(intent);
                    finish();

                }

                else if(id == R.id.menuActors){
                    Toast.makeText(Main_menu.this, "Actors", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Main_menu.this, Actors.class);
                    startActivity(intent);
                    finish();

                }

                else if(id == R.id.menuDirectors){
                    Toast.makeText(Main_menu.this, "Directros", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Main_menu.this, Directors.class);
                    startActivity(intent);
                    finish();

                }

                return true;
            }
        });

    }
}