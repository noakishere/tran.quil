package com.example.tranquil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature((Window.FEATURE_NO_TITLE)); // hide title
        getSupportActionBar().hide(); // hide title bar
        setContentView(R.layout.activity_main);

        // load directly into Main Menu (until login/registration page has been implemented
        Intent intent = new Intent(MainActivity.this, MainMenu.class);
        startActivity(intent);
    }
}