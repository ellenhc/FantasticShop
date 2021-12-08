package com.example.fantasticshop.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fantasticshop.R;
import com.example.fantasticshop.SingletonClass;
import com.example.fantasticshop.adapter.ItemAdapter;

import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView, recyclerView2;
    ItemAdapter itemAdapter, verticalItemAdapter;
    List<HorizontalItems> itemsList;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = viewRoot.findViewById(R.id.horizontal_recyclerview_id);
        recyclerView2 = viewRoot.findViewById(R.id.verticalRecyclerView_id);
        itemsList = SingletonClass.getMyInstance().getItemList();

        setItemRecycler(itemsList);

        return viewRoot;
    }

    private void setItemRecycler(List<HorizontalItems> dataList){
        Log.i("CALLBACK'S", "RecyclerView setting... ");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        itemAdapter = new ItemAdapter(getContext(), dataList, R.layout.horizontal_item_layout);
        verticalItemAdapter = new ItemAdapter(getContext(), dataList, R.layout.vertical_item_layout);
        recyclerView.setAdapter(itemAdapter);
        recyclerView2.setAdapter(verticalItemAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onStart() {
        super.onStart();
        setItemRecycler(itemsList);
        itemAdapter.notifyDataSetChanged();
        verticalItemAdapter.notifyDataSetChanged();
        Log.i("CALLBACK'S", "data updated... ");
    }
    //    @SuppressLint("NotifyDataSetChanged")
//    @Override
//    public void displayItemList(List<HorizontalItems> dataList) {
//
//        setItemRecycler(dataList);
//        itemAdapter.notifyDataSetChanged();
//        verticalItemAdapter.notifyDataSetChanged();
//        Log.i("CALLBACK'S", "data updated... ");
//
//    }
}
