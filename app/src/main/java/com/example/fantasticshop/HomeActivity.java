package com.example.fantasticshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.fantasticshop.fragments.HomeFragment;
import com.example.fantasticshop.fragments.HorizontalItems;

import java.util.List;


public class HomeActivity extends AppCompatActivity implements CallBackInterface{

    private Button btn_add_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Let's load the itemRepository
        ItemRepository repo = new ItemRepository();

        //Let's now update it
        repo.updateData();

        repo.setCallBackInterface(this);

        initViews();

        // adding function to the add item button
        btn_add_item.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UserAddItemActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fragmentInjection() {
        Log.i("CALLBACK'S", "Fragment loading...: ");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_id, new HomeFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void displayItemList(List<HorizontalItems> dataList) {
        Log.i("CALLBACK'S", "my dataList: " + dataList);

        //fragmentInjection;
        fragmentInjection();
    }

    private void initViews() {
        btn_add_item = findViewById(R.id.btn_add_item);
    }



}