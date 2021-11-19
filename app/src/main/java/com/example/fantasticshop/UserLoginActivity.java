package com.example.fantasticshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserLoginActivity extends AppCompatActivity {
    private EditText emailId;
    private EditText passwordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        // Grab id's for email & password inputs
        emailId = (EditText) findViewById(R.id.userLogin_email_input_id);
        passwordId = (EditText) findViewById(R.id.userLogin_password_input_id);
    }

    // Called when user clicks login button
    public void onLogin(View view){
        String email = emailId.getText().toString();
        String password = passwordId.getText().toString();

        String regex = "^(.+)@(.+)$";
        boolean result = email.matches(regex);

        if(result && !password.equals("")){
            System.out.println("Login successful");

            // Once logged in, takes user to HomeActivity
            Toast.makeText(getApplicationContext(), "Successfully logging in", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Can't log in. Try again.", Toast.LENGTH_SHORT).show();
        }
    }

    // Sends to UserCreateAccountActivity
    public void onCreateAccount(View view){
        Intent intent = new Intent(this, UserCreateAccountActivity.class);
        startActivity(intent);
    }
}