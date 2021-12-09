package com.example.fantasticshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fantasticshop.adapter.ItemAdapter;

public class ItemDetailsActivity extends AppCompatActivity {
    private ItemAdapter itemAdapter, verticalItemAdapter;
    ImageView item_image, item_star_icon, close;
    TextView item_name, item_description, item_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        initViews();

        //retrieve data from the intent
        String image = getIntent().getStringExtra("image");
        String name = getIntent().getStringExtra("name");
        String desc = getIntent().getStringExtra("desc");
        String price = getIntent().getStringExtra("price");
        Boolean liked = getIntent().getBooleanExtra("liked", false);

        if(liked){
            item_star_icon.setImageResource(R.drawable.ic_star_like);
        }else{
            item_star_icon.setImageResource(R.drawable.ic_star_unlike);
        }

        //we now set the item

        Glide.with(getApplicationContext()).load(Uri.parse(image)).into(item_image);
        item_name.setText(name);
        item_description.setText(desc);
        item_price.setText(price);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemDetailsActivity.this.finish();
            }
        });

    }

    private void initViews() {

        item_image = findViewById(R.id.item_image_id);
        item_name = findViewById(R.id.item_name_id);
        item_description = findViewById(R.id.item_description_id);
        item_price = findViewById(R.id.item_price_id);
        item_star_icon = findViewById(R.id.unlike_id);
        close = findViewById(R.id.close_id);

    }
}