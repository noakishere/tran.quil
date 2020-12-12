package com.example.tranquil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MyProfile extends AppCompatActivity {
    ImageButton appSettingsBtn, exitProfileSettingsBtn;
    Button editAccountBtn;
    TextView userFullNameTextView, userMeditationRankTextView, userTimeSpentMeditatingTextView;
    TextView userLessonsCompletedTextView, userActivitiesCompletedTextView;
    EditText userFullNameEditText, userAgeEditText, userGenderEditText, userUsernameEditText, userEmailEditText;
    EditText userJoinDateEditText, userMeditationRankEditText;

    String previousActivity;
    private Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature((Window.FEATURE_NO_TITLE)); // hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_my_profile);

        // Collecting added details from created Intent
        Intent intent = getIntent();
        previousActivity = intent.getStringExtra("currentActivity");

        // declaring TextViews
        userFullNameTextView = (TextView)findViewById(R.id.userFullNameProfilePage);
        userMeditationRankTextView = (TextView)findViewById(R.id.userMeditationRankProfilePage);
        userTimeSpentMeditatingTextView = (TextView)findViewById(R.id.userTimeSpentMeditatingValueProfilePage);
        userLessonsCompletedTextView = (TextView)findViewById(R.id.userLessonsCompletedValueProfilePage);
        userActivitiesCompletedTextView = (TextView)findViewById(R.id.userActivitiesCompletedValueProfilePage);

        // declaring EditTexts (TextViews)
        userFullNameEditText = (EditText)findViewById(R.id.userNameValueProfilePage);
        userAgeEditText = (EditText)findViewById(R.id.userAgeValueProfilePage);
        userGenderEditText = (EditText)findViewById(R.id.userGenderValueProfilePage);
        userUsernameEditText = (EditText)findViewById(R.id.userUsernameValueProfilePage);
        userEmailEditText = (EditText)findViewById(R.id.userEmailValueProfilePage);
        userJoinDateEditText = (EditText)findViewById(R.id.userJoinDateValueProfilePage);
        userMeditationRankEditText = (EditText)findViewById(R.id.userMeditationRankValueProfilePage);

        // declaring Buttons
        editAccountBtn = (Button)findViewById(R.id.editAccountButtonProfilePage);

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

        // registering click events for editAccountBtn
        editAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Declare variables

                // Collect user data from database

                // Create and show dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MyProfile.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.dialog_update_profile, viewGroup, false);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                // Initialize and assign variables

                // Set data on EditText (dialog box)

                // Update button on-click listener
            }
        });
    }
}