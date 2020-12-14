package com.example.tranquil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Sleep extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageButton myProfileBtn;
    ConstraintLayout featuredActivity, windDownActivity, soundEscapeActivity, sleepMusicActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature((Window.FEATURE_NO_TITLE)); // hide title
        getSupportActionBar().hide(); // hide title bar
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sleep);
        bottomNavigationView = findViewById(R.id.bottomNavigationBarSleepPage);

        // declaring ConstraintLayouts
        featuredActivity = (ConstraintLayout)findViewById(R.id.featuredSleepExercise);
        windDownActivity = (ConstraintLayout)findViewById(R.id.windDownExercisesContainer);
        soundEscapeActivity = (ConstraintLayout)findViewById(R.id.soundEscapesContainer);
        sleepMusicActivity = (ConstraintLayout)findViewById(R.id.sleepMusicContainer);

        // declaring ImageButtons
        myProfileBtn = (ImageButton)findViewById(R.id.myProfileBtnSleepPage);

        // set highlighted menu
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        // registering click events for top navigation
        myProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyProfile.class);
                intent.putExtra("currentActivity", "SleepPage");
                startActivity(intent);
            }
        });

        // registering click events for sleep activities
        featuredActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This feature is not yet supported...", Toast.LENGTH_LONG).show();
            }
        });
        windDownActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This feature is not yet supported...", Toast.LENGTH_LONG).show();
            }
        });
        soundEscapeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This feature is not yet supported...", Toast.LENGTH_LONG).show();
            }
        });
        sleepMusicActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This feature is not yet supported...", Toast.LENGTH_LONG).show();
            }
        });

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
                }
                return true;
            }
        });
    }
}