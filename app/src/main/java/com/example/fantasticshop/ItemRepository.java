package com.example.fantasticshop;

import static com.example.fantasticshop.SingletonClass.getMyInstance;

import androidx.annotation.NonNull;

import com.example.fantasticshop.fragments.HorizontalItems;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ItemRepository{

    static CallBackInterface callBackInterface;

    public ItemRepository(CallBackInterface callBackInterface) {
        ItemRepository.callBackInterface = callBackInterface;
    }

    public void setCallBackInterface(CallBackInterface callBackInterface) {
        ItemRepository.callBackInterface = callBackInterface;
    }

    // Let's connect our items database reference

    static final DatabaseReference myDataBaseRef = getMyInstance().getReference();

    // we are now going to create a singleton list containing all our items
    static List<HorizontalItems> itemList = getMyInstance().getItemList();
    // we are now going to update the database to the items list


    static void updateData(){

        myDataBaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    HorizontalItems item = ds.getValue(HorizontalItems.class);

                    if(item != null){
                        itemList.add(item);
                    }

                }

                callBackInterface.updateItemList("Callback done");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



                }


}
