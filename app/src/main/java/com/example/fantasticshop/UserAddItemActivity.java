package com.example.fantasticshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UserAddItemActivity extends AppCompatActivity {

    private EditText item_name, item_desc, item_category, item_price;
    ImageView imageView;
    Button btn_upload, btn_save, cancel_btn;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    public static final String MY_SHARED_PREF = "sharedPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add_item);

        imageView = (ImageView)findViewById(R.id.add_image_id);
        btn_upload = (Button)findViewById(R.id.btn_upload);
        item_name = findViewById(R.id.item_name);
        item_desc = findViewById(R.id.item_desc);
        item_category = findViewById(R.id.item_category);
        item_price = findViewById(R.id.item_price);
        btn_save = findViewById(R.id.btn_save);
        cancel_btn = findViewById(R.id.cancel_btn);

        btn_upload.setOnClickListener(view -> openGallery());

        cancel_btn.setOnClickListener(view -> {
            //return to home view
            Toast.makeText(UserAddItemActivity.this, "You are cancelling", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UserAddItemActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        btn_save.setOnClickListener(view -> {
            String item_name_string = item_name.getText().toString();
            String item_desc_string = item_desc.getText().toString();
            String item_category_string = item_category.getText().toString();
            String item_price_string = item_price.getText().toString();

            if(isValid()){
                SharedPreferences sharedPreferences = getSharedPreferences(MY_SHARED_PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor myEditor = sharedPreferences.edit();

                myEditor.putString("itemName", item_name_string);
                myEditor.putString("itemDesc", item_desc_string);
                myEditor.putString("itemCategory", item_category_string);
                myEditor.putString("itemPrice", item_price_string);

                myEditor.apply();

                UserAddItemActivity.this.finish();
            }else{
                Toast.makeText(UserAddItemActivity.this, "Name and price are required.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Followed ImagePicker tutorial: https://www.tutorialspoint.com/how-to-pick-an-image-from-image-gallery-in-android
    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    // What items should be required to create a product? All? Just name and price?
    public Boolean isValid() {
        String name = item_name.getText().toString().trim();
        String price = item_price.getText().toString().trim();

        return !name.isEmpty() && !price.isEmpty();
    }
}