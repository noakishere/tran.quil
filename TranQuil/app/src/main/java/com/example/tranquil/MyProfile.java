package com.example.tranquil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

public class MyProfile extends AppCompatActivity {
    ImageButton appSettingsBtn, exitProfileSettingsBtn;

    String previousActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature((Window.FEATURE_NO_TITLE)); // hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_my_profile);

        // Collecting added details from created Intent
        Intent intent = getIntent();
        previousActivity = intent.getStringExtra("currentActivity");

        // declaring ImageButtons
        appSettingsBtn = (ImageButton)findViewById(R.id.appSettingsMyProfilePage);
        exitProfileSettingsBtn = (ImageButton)findViewById(R.id.exitMyProfileSettingsBtn);

        // registering click events for top navigation
        appSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This feature is not yet supported...", Toast.LENGTH_LONG).show();
            }
        });
        exitProfileSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;

                // determine which activity profile page was opened from
                switch(previousActivity){
                    case "MeditationPage":
                        intent = new Intent(getApplicationContext(), Meditation.class);
                        break;
                    case "SleepPage":
                        intent = new Intent(getApplicationContext(), Sleep.class);
                        break;
                    case "DiscoverPage":
                        intent = new Intent(getApplicationContext(), Discover.class);
                        break;
                    case "SosPage":
                        intent = new Intent(getApplicationContext(), sos.class);
                        break;
                    default:
                        intent = new Intent(getApplicationContext(), MainMenu.class);
                }
                startActivity(intent); // return to previous page
            }
        });
    }
}