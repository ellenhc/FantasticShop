package com.example.fantasticshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Takes user to UserLoginActivity when they click the shopping cart image
    public void onEnter(View view){
        Intent intent = new Intent(this, UserLoginActivity.class);
        startActivity(intent);
    }
}