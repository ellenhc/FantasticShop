package com.example.fantasticshop;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.fantasticshop.fragments.HorizontalItems;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;

import java.util.ArrayList;
import java.util.List;

public class SingletonClass {
    private static SingletonClass myInstance = null;
    private List<HorizontalItems> itemList;
    private final DatabaseReference reference;
    private FirebaseAuth mAuth;

    private SingletonClass() {
        itemList = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://fantasticshop-9cd2b-default-rtdb.firebaseio.com/");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("Singleton", "signInAnonymously:success");
                FirebaseUser user = mAuth.getCurrentUser();
            } else {
                // If sign in fails, display a message to the user.
                Log.w("Singleton", "signInAnonymously:failure", task.getException());
            }
        });

        reference = database.getReference("items");
    }

    public List<HorizontalItems> getItemList() {
        return itemList;
    }

    public DatabaseReference getReference() {
        return reference;
    }

    public static synchronized SingletonClass getMyInstance() {
        if (myInstance == null) {
            myInstance = new SingletonClass();
        }
        return myInstance;
    }

    public String getMaxId() {
        return String.valueOf(itemList.size() + 1);
    }
}
