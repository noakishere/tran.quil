package com.example.tranquil;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText userFullNameEditText, usernameEditText, passwordEditText;
    Button registerAccountBtn, loginAsExistingUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // declare views
        userFullNameEditText = (EditText)findViewById(R.id.userFullNameEditTextRegisterPage);
        usernameEditText = (EditText)findViewById(R.id.usernameEditTextRegisterPage);
        passwordEditText = (EditText)findViewById(R.id.passwordEditTextRegisterPage);
        registerAccountBtn = (Button)findViewById(R.id.registerButtonRegisterPage);
        loginAsExistingUserBtn = (Button)findViewById(R.id.loginAsExistingUserButtonRegisterPage);

        // register click event for registerAccountBtn
        registerAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAccount();
            }
        });
    }

    // validates entered information, creates a new account, and returns the user back to
    // the login screen
    private void registerAccount(){
        // collect userFullName, username and password from textfields
        String userFullName = userFullNameEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // validate userFullName, username and password are not blank
        if(userFullName.equals("") || username.equals("") || password.equals("")){
            Toast.makeText(getApplicationContext(), "No fields can be left blank!", Toast.LENGTH_SHORT).show();

            // userFullName is left blank
            if(userFullName.equals("")){
                userFullNameEditText.setError("Cannot be left blank.");
            }
            // username is left blank
            if(username.equals("")){
                usernameEditText.setError("Cannot be left blank.");
            }
            // password is left blank
            if(password.equals("")){
                passwordEditText.setError("Cannot be left blank.");
            }
        }
        // verify username does not exist, and create a new account
        else{
            // username is available to use
            if(validateUsername(username) == 0){
                createNewAccount(userFullName, username, password); // creates account in the database

                Toast.makeText(getApplicationContext(), "Account successfully created!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent); // returns user back to the login screen
            }
            // username is not available (already exists)
            else if(validateUsername(username) == 1){
                Toast.makeText(getApplicationContext(), "Invalid username!", Toast.LENGTH_SHORT).show();
                usernameEditText.setError("Username already exists.");
            }
        }
    }

    // validates if username exists in the database
    private int validateUsername(String username){
        // return 0 if username is available
        // return 1 if username is unavailable
        return 0;
    }

    // creates the user account in the database
    private void createNewAccount(String userFullName, String username, String password){

    }
}