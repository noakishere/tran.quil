package com.example.tranquil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenu extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageButton appSettingsBtn, myProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature((Window.FEATURE_NO_TITLE)); // hiding the title
        getSupportActionBar().hide(); // hiding the title bar
        setContentView(R.layout.activity_main_menu);
        bottomNavigationView = findViewById(R.id.bottomNavigationBarMainMenu);

        // declaring ImageButtons
        myProfileBtn = (ImageButton)findViewById(R.id.myProfileBtnMainMenu);
        appSettingsBtn = (ImageButton)findViewById(R.id.appSettingsMainMenu);

        // registering click events for top navigation
        myProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyProfile.class);
                intent.putExtra("currentActivity", "MainMenu");
                startActivity(intent);
            }
        });
        appSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This feature is not yet supported...", Toast.LENGTH_LONG).show();
            }
        });

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
                }
                return false;
            }
        });
    }


}