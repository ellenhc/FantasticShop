package com.example.fantasticshop;

import static com.example.fantasticshop.ItemRepository.updateData;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.fantasticshop.fragments.HomeFragment;


public class HomeActivity extends AppCompatActivity implements CallBackInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Let's now update it
        updateData();

        //Let's load the itemRepository
        ItemRepository repo = new ItemRepository(this);

        {
            //Fragment injection into the home page
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_id, new HomeFragment());
        transaction.addToBackStack(null);
        transaction.commit();
        }

        repo.setCallBackInterface(this);

    }

    @Override
    public void updateItemList(String msg) {
        Log.i("CALLBACK'S", "my data: " + msg);

    }
}