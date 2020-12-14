package com.example.tranquil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class sos extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageButton appSettingsBtn, myProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature((Window.FEATURE_NO_TITLE)); // hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_sos);
        bottomNavigationView = findViewById(R.id.bottomNavigationBarSosPage);

        // declaring ImageButtons
        myProfileBtn = (ImageButton)findViewById(R.id.myProfileBtnSosPage);
        appSettingsBtn = (ImageButton)findViewById(R.id.appSettingsSosPage);

        // registering click events for top navigation
        myProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyProfile.class);
                intent.putExtra("currentActivity", "SosPage");
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
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);

        // menu navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.mainMenuPage:
                        Intent intentMeditation = new Intent(sos.this, MainMenu.class);
                        startActivity(intentMeditation);
                        return true;
                    case R.id.meditationPage:
                        Intent intentSleep = new Intent(sos.this, Meditation.class);
                        startActivity(intentSleep);
                        return true;
                    case R.id.sleepPage:
                        Intent intentDiscover = new Intent(sos.this, Sleep.class);
                        startActivity(intentDiscover);
                        return true;
                }
                return true;
            }
        });
    }
}