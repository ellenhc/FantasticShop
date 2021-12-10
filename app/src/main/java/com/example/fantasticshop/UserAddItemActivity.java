package com.example.fantasticshop;

import static com.example.fantasticshop.SingletonClass.getMyInstance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fantasticshop.fragments.HorizontalItems;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class UserAddItemActivity extends AppCompatActivity {

    private EditText item_name, item_desc, item_category, item_price;
    private ImageView imageView;
    private Button btn_upload, btn_save, cancel_btn;

    private static final int PICK_IMAGE = 100;
    private Uri imageUri;

    DatabaseReference databaseReference;
    FirebaseStorage storage;
    StorageReference storageReference;
    HorizontalItems newItem; // creating a variable for our item class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add_item);

        // Initializing the edittext and buttons
        initViews();

        databaseReference = getMyInstance().getReference();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        newItem = new HorizontalItems();

        btn_upload.setOnClickListener(view -> openGallery());

        cancel_btn.setOnClickListener(view -> {
            //return to home view
            Toast.makeText(UserAddItemActivity.this, "You are cancelling", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UserAddItemActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        btn_save.setOnClickListener(view -> {
            // Getting text from edittext fields
            String item_name_string = item_name.getText().toString();
            String item_desc_string = item_desc.getText().toString();
            String item_price_string = item_price.getText().toString();

            // Checks if name and price were filled out
            if (isValid()) {
                // Image stuff:
                imageView.setDrawingCacheEnabled(true);
                imageView.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();

                // calls to method to add data to database
                addDataToFirebase(item_name_string, data, item_price_string, item_desc_string);
            } else {
                Toast.makeText(UserAddItemActivity.this, "Name and price are required.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        imageView = (ImageView) findViewById(R.id.add_image_id);
        btn_upload = (Button) findViewById(R.id.btn_upload);
        item_name = findViewById(R.id.item_name);
        item_desc = findViewById(R.id.item_desc);
        item_category = findViewById(R.id.item_category);
        item_price = findViewById(R.id.item_price);
        btn_save = findViewById(R.id.btn_save);
        cancel_btn = findViewById(R.id.cancel_btn);
    }

    // Followed ImagePicker tutorial: https://www.tutorialspoint.com/how-to-pick-an-image-from-image-gallery-in-android
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
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

    private void addDataToFirebase(String name, byte[] img, String price, String description) {
        newItem.setId(getMyInstance().getMaxId());
        newItem.setName(name);
        newItem.setPrice(price);
        newItem.setDescription(description);
        newItem.setLiked(false);

        // Saves image to Firebase storage
        StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());

        // Gets url from getDownloadUrl to display image in app
        ref.putBytes(img).addOnSuccessListener(taskSnapshot -> {
            //Toast.makeText(UserAddItemActivity.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
            ref.getDownloadUrl().addOnSuccessListener(downloadUrl -> {
                newItem.setImageUrl(downloadUrl.toString());
                databaseReference.child(newItem.getId()).setValue(newItem);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Toast.makeText(UserAddItemActivity.this, "Item added", Toast.LENGTH_SHORT).show();
                        // We are finally done, go back to the home screen
                        Intent intent = new Intent(UserAddItemActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(UserAddItemActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
                    }
                });
            });
        })

                .addOnFailureListener(e -> {
                    // Error, Image not uploaded
                    Toast.makeText(UserAddItemActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }
}