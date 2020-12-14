package com.example.tranquil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MyProfile extends AppCompatActivity {
    ImageButton exitProfileSettingsBtn;
    Button editAccountBtn, updateAccountBtn;
    TextView userFullNameTextView, userMeditationRankTextView, userTimeSpentMeditatingTextView;
    TextView userLessonsCompletedTextView, userActivitiesCompletedTextView;
    EditText userFullNameEditText, userAgeEditText, userGenderEditText, userUsernameEditText, userEmailEditText;
    EditText userJoinDateEditText, userMeditationRankEditText, userPasswordEditText;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    FirebaseUser currentUser;
    private DocumentReference noteRef;

    Timestamp joinedDate;

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
        userPasswordEditText = (EditText)findViewById(R.id.userPasswordValueProfilePage);

        // declaring Buttons
        editAccountBtn = (Button)findViewById(R.id.editAccountButtonProfilePage);
        updateAccountBtn = (Button)findViewById(R.id.updateAccountButtonProfilePage);

        // declaring ImageButtons
        exitProfileSettingsBtn = (ImageButton)findViewById(R.id.exitMyProfileSettingsBtn);

        // initializing firebase stuff
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        currentUser = fAuth.getCurrentUser();
        noteRef = fStore.collection("users").document(userID);

        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                userFullNameTextView.setText(value.getString("fName"));
                userFullNameEditText.setText(value.getString("fName"));
                userAgeEditText.setText(value.get("age").toString());
                userGenderEditText.setText(value.getString("gender"));
                userEmailEditText.setText(value.getString("email"));
                userJoinDateEditText.setText(value.getDate("joinedDate").toString());
                joinedDate = value.getTimestamp("joinedDate");
            }
        });

        // registering click events for top navigation
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
                editAccountBtn.setVisibility(View.INVISIBLE);
                updateAccountBtn.setVisibility(View.VISIBLE);
                userFullNameEditText.setEnabled(true);
                userFullNameEditText.setBackgroundColor(Color.parseColor("#FFFFFF"));

                userAgeEditText.setEnabled(true);
                userAgeEditText.setBackgroundColor(Color.parseColor("#FFFFFF"));

                userGenderEditText.setEnabled(true);
                userGenderEditText.setBackgroundColor(Color.parseColor("#FFFFFF"));

                userEmailEditText.setEnabled(true);
                userEmailEditText.setBackgroundColor(Color.parseColor("#FFFFFF"));

                //userPasswordEditText.setText("");
               // userPasswordEditText.setEnabled(true);
               // userPasswordEditText.setBackgroundColor(Color.parseColor("#FFFFFF"));



                /*
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

                 */
            }
        });


    }

    @SuppressLint("ResourceAsColor")
    public void updateProfile(View v) {
        String name = userFullNameEditText.getText().toString();
        String mail = userEmailEditText.getText().toString();
        int age = Integer.parseInt(userAgeEditText.getText().toString());
        String gender = userGenderEditText.getText().toString();

        String newPass = userPasswordEditText.getText().toString();

        Map<String, Object> user = new HashMap<>();
        user.put("fName", name);
        user.put("email", mail);
        user.put("age", age);
        user.put("gender" , gender);
        user.put("joinedDate", joinedDate);

        noteRef.set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MyProfile.this, "Info updated", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MyProfile.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });

        currentUser.updateEmail(mail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "User email address updated.");
                        }
                    }
                });
//
//        currentUser.updatePassword(newPass)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Log.d("TAG", "Password updated");
//                        } else {
//                            Log.d("TAG", "Error password not updated");
//                        }
//                    }
//                });

        editAccountBtn.setVisibility(View.VISIBLE);
        updateAccountBtn.setVisibility(View.INVISIBLE);
        userFullNameEditText.setEnabled(false);
        userFullNameEditText.setBackgroundColor(android.R.color.transparent);

        userAgeEditText.setEnabled(false);
        userAgeEditText.setBackgroundColor(android.R.color.transparent);

        userGenderEditText.setEnabled(false);
        userGenderEditText.setBackgroundColor(android.R.color.transparent);

        userEmailEditText.setEnabled(false);
        userEmailEditText.setBackgroundColor(android.R.color.transparent);

       // userPasswordEditText.setEnabled(false);
       // userPasswordEditText.setBackgroundColor(Color.parseColor("#FFFFFF"));
    }
}