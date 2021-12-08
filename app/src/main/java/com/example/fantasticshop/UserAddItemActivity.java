package com.example.fantasticshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class UserAddItemActivity extends AppCompatActivity {

    EditText item_name, item_desc, item_category, item_price;
    Button btn_upload, btn_okay, cancel_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add_item);
    }
}