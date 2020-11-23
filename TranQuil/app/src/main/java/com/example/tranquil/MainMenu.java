package com.example.tranquil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenu extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature((Window.FEATURE_NO_TITLE)); // hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_main_menu);
        bottomNavigationView = findViewById(R.id.bottomNavigationBar);

        // set highlighted menu
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        // menu navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.meditationPage:
                        Intent intentMeditation = new Intent(MainMenu.this, Meditation.class);
                        startActivity(intentMeditation);
                        return true;
                    case R.id.sleepPage:
                        Intent intentSleep = new Intent(MainMenu.this, Sleep.class);
                        startActivity(intentSleep);
                        return true;
                    case R.id.discoverPage:
                        Intent intentDiscover = new Intent(MainMenu.this, Discover.class);
                        startActivity(intentDiscover);
                        return true;
                    case R.id.sosPage:
                        Intent intentSOS = new Intent(MainMenu.this, sos.class);
                        startActivity(intentSOS);
                        return true;
                }
                return false;
            }
        });
    }
}