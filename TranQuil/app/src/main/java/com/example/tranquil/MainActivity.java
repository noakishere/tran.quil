package com.example.tranquil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usernameEditText, passwordEditText;
    Button loginBtn, registerNewUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature((Window.FEATURE_NO_TITLE)); // hide title
        getSupportActionBar().hide(); // hide title bar
        setContentView(R.layout.activity_main);

        // declare views
        usernameEditText = (EditText)findViewById(R.id.usernameEditTextLoginPage);
        passwordEditText = (EditText)findViewById(R.id.passwordEditTextLoginPage);
        loginBtn = (Button)findViewById(R.id.loginButtonLoginPage);
        registerNewUserBtn = (Button)findViewById(R.id.registerNewUserButtonLoginPage);

        // register click event for loginBtn
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAsExistingUser();
            }
        });

        // register click event of registerNewUserBtn
        registerNewUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAsNewUser();
            }
        });
    }

    // validates user information, and logs in user to the main screen (main menu page)
    private void loginAsExistingUser(){
        // collect username and password from text fields
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // validate userFullName, username and password are not blank
        if(username.equals("") || password.equals("")){
            Toast.makeText(getApplicationContext(), "No fields can be left blank!", Toast.LENGTH_SHORT).show();

            // username is left blank
            if(username.equals("")){
                usernameEditText.setError("Cannot be left blank.");
            }
            // password is left blank
            if(password.equals("")){
                passwordEditText.setError("Cannot be left blank.");
            }
        }
        // validate username and password in database
        else{
            // username and password are correct
            if(validateUserCredentials(username, password) == 0){
                Toast.makeText(getApplicationContext(), "User login is currently not supported...", Toast.LENGTH_LONG).show(); // remove once database is working
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
            // username and password is incorrect
            else if(validateUserCredentials(username, password) == 1){
                Toast.makeText(getApplicationContext(), "Invalid username and password!", Toast.LENGTH_LONG).show();
                usernameEditText.setError("Username does not exist.");
                passwordEditText.setError("Invalid password.");
            }
            // username is correct and password is incorrect
            else if(validateUserCredentials(username, password) == 2){
                Toast.makeText(getApplicationContext(), "Invalid password!", Toast.LENGTH_SHORT).show();
                passwordEditText.setError("Invalid password.");
            }
            // username is incorrect and password is correct
            else if(validateUserCredentials(username, password) == 3){
                Toast.makeText(getApplicationContext(), "Invalid username!", Toast.LENGTH_SHORT).show();
                usernameEditText.setError("Username does not exist.");
            }
        }
    }

    // opens the Register page to create a new account
    private void registerAsNewUser(){
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivity(intent);
    }

    // validates username and password in the database (to be supported)
    private int validateUserCredentials(String username, String password){
        // return 0 if username and password are correct
        // return 1 if username and password are incorrect
        // return 2 if username is correct and password is incorrect
        // return 3 if username is incorrect and password is correct
        return 3;
    }
}