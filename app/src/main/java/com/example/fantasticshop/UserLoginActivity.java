package com.example.fantasticshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else {
            System.out.println("Can't login. Try again.");
        }
    }

    // Sends to UserCreateAccountActivity
    public void onCreateAccount(View view){
        Intent intent = new Intent(this, UserCreateAccountActivity.class);
        startActivity(intent);
    }
}