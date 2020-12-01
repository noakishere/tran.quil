package com.example.tranquil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText emailEditText, passwordEditText;
    Button loginBtn, registerNewUserBtn;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature((Window.FEATURE_NO_TITLE)); // hide title
        getSupportActionBar().hide(); // hide title bar
        setContentView(R.layout.activity_main);

        // declare views
        emailEditText = (EditText)findViewById(R.id.emailEditTextLoginPage);
        passwordEditText = (EditText)findViewById(R.id.passwordEditTextLoginPage);
        loginBtn = (Button)findViewById(R.id.loginButtonLoginPage);
        registerNewUserBtn = (Button)findViewById(R.id.registerNewUserButtonLoginPage);

        //Instantiate firebase authenticator
        fAuth = FirebaseAuth.getInstance();

        // register click event for loginBtn
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String pw = passwordEditText.getText().toString().trim();

                // validate if textfields are empty
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pw)){
                    if(TextUtils.isEmpty(email)){
                        emailEditText.setError("Email is Required.");
                    }
                    if(TextUtils.isEmpty(pw)){
                        passwordEditText.setError("Password is Required.");
                    }
                    return;
                }

                //authenticate the user
                fAuth.signInWithEmailAndPassword(email, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Logged in Successfully.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainMenu.class));
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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

    // opens the Register page to create a new account
    private void registerAsNewUser(){
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivity(intent);
    }
}