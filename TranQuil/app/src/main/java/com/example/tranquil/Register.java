package com.example.tranquil;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText userFullNameEditText, emailEditText, passwordEditText;
    Button registerAccountBtn, loginAsExistingUserBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // declare views
        userFullNameEditText = (EditText)findViewById(R.id.userFullNameEditTextRegisterPage);
        emailEditText = (EditText)findViewById(R.id.emailEditTextRegisterPage);
        passwordEditText = (EditText)findViewById(R.id.passwordEditTextRegisterPage);
        registerAccountBtn = (Button)findViewById(R.id.registerButtonRegisterPage);
        loginAsExistingUserBtn = (Button)findViewById(R.id.loginAsExistingUserButtonRegisterPage);

        //Instantiate firebase authenticator
        fAuth = FirebaseAuth.getInstance();

        // register click event for registerAccountBtn
        registerAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String pw = passwordEditText.getText().toString().trim();
                String userName = userFullNameEditText.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emailEditText.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(pw)){
                    passwordEditText.setError("Password is Required.");
                    return;
                }

                if(TextUtils.isEmpty(userName)){
                    userFullNameEditText.setError("Name is Required.");
                    return;
                }

                // register the user in firebase

                fAuth.createUserWithEmailAndPassword(email, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else {
                            Toast.makeText(Register.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

        // register click event for loginAsExistingUserBtn
        loginAsExistingUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}