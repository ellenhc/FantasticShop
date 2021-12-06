package com.example.fantasticshop.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fantasticshop.R;
import com.example.fantasticshop.fragments.HorizontalItems;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    Context context;
    List<HorizontalItems> itemsList;
    Integer layoutId;


    public ItemAdapter(Context context, List<HorizontalItems> itemList, Integer layoutId) {
        this.context = context;
        this.itemsList = itemList;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        HorizontalItems currentItem = itemsList.get(position);
        Glide.with(context).load(Uri.parse(currentItem.getImageUrl())).into(holder.itemImage);
        holder.itemName.setText(currentItem.getName());
        holder.itemPrice.setText(currentItem.getPrice());
        holder.itemDesc.setText(currentItem.getDescription());

        //let us check if the item has been liked or not
        if(currentItem.getLiked()){
            holder.starIcon.setImageResource(R.drawable.ic_star_like);
        }else{
            holder.starIcon.setImageResource(R.drawable.ic_star_unlike);
        }


    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView itemName, itemPrice, itemDesc;
        ImageView itemImage, starIcon;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name_id);
            itemImage = itemView.findViewById(R.id.item_image_id);
            itemPrice = itemView.findViewById(R.id.item_price_id);
            itemDesc = itemView.findViewById(R.id.item_description_id);
            starIcon = itemView.findViewById(R.id.unlike_id);
        }
    }
}
