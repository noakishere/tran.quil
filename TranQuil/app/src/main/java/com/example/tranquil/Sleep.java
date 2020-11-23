package com.example.tranquil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Sleep extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature((Window.FEATURE_NO_TITLE)); // hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_sleep);
        bottomNavigationView = findViewById(R.id.bottomNavigationBar);

        // set highlighted menu
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        // menu navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.mainMenuPage:
                        Intent intentMeditation = new Intent(Sleep.this, MainMenu.class);
                        startActivity(intentMeditation);
                        return true;
                    case R.id.meditationPage:
                        Intent intentSleep = new Intent(Sleep.this, Meditation.class);
                        startActivity(intentSleep);
                        return true;
                    case R.id.discoverPage:
                        Intent intentDiscover = new Intent(Sleep.this, Discover.class);
                        startActivity(intentDiscover);
                        return true;
                    case R.id.sosPage:
                        Intent intentSOS = new Intent(Sleep.this, sos.class);
                        startActivity(intentSOS);
                        return true;
                }
                return true;
            }
        });
    }
}