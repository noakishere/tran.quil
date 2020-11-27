package com.example.tranquil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    String user, pass;
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


//    public void openRegisterActivity(View view) {
//        Intent intent = new Intent(MainActivity.this, Register.class);
//        startActivity(intent);
//    }

//    public void loginAndValidate(View view) {
//
//        // validation goes here
//        username = (EditText)findViewById(R.id.idTextView);
//        password = (EditText)findViewById(R.id.passwordTextView);
//        user = username.getText().toString();
//        pass = password.getText().toString();
//        Toast.makeText(getApplicationContext(),"This is a test for the username and password ! "+"Username: " +user + " Password:  "+pass,Toast.LENGTH_LONG).show();
//
//        // open the main menu for now
//       /* Intent intent = new Intent(MainActivity.this, MainMenu.class);
//        startActivity(intent);*/
//    }
}