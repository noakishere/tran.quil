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

public class Meditation extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageButton myProfileBtn;
    ConstraintLayout featuredActivity, guidedCourses, timedExercises, breathingExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature((Window.FEATURE_NO_TITLE)); // hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_meditation);
        bottomNavigationView = findViewById(R.id.bottomNavigationBarMeditationPage);

        // declaring ConstraintLayouts
        featuredActivity = (ConstraintLayout)findViewById(R.id.featuredMeditationExercise);
        guidedCourses = (ConstraintLayout)findViewById(R.id.meditationCoursesContainer);
        timedExercises = (ConstraintLayout)findViewById(R.id.timedMeditationExercisesContainer);
        breathingExercises = (ConstraintLayout)findViewById(R.id.breathingExercisesContainer);

        // declaring ImageButtons
        myProfileBtn = (ImageButton)findViewById(R.id.myProfileBtnMeditationPage);

        // registering click events for top navigation
        myProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyProfile.class);
                intent.putExtra("currentActivity", "MeditationPage");
                startActivity(intent);
            }
        });

        // registering click events for meditation activities
        featuredActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This feature is not yet supported...", Toast.LENGTH_LONG).show();
            }
        });
        guidedCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "This feature is not yet supported...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), ActivitiesPage.class);
                startActivity(intent);
            }
        });
        timedExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This feature is not yet supported...", Toast.LENGTH_LONG).show();
            }
        });
        breathingExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This feature is not yet supported...", Toast.LENGTH_LONG).show();
            }
        });

        // set highlighted menu
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        // menu navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.mainMenuPage:
                        Intent intentMeditation = new Intent(Meditation.this, MainMenu.class);
                        startActivity(intentMeditation);
                        return true;
                    case R.id.sleepPage:
                        Intent intentSleep = new Intent(Meditation.this, Sleep.class);
                        startActivity(intentSleep);
                        return true;
                }
                return true;
            }
        });
    }

}