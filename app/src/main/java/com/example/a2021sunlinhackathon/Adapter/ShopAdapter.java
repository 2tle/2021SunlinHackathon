package com.example.a2021sunlinhackathon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a2021sunlinhackathon.Data.ShopData;
import com.example.a2021sunlinhackathon.R;
import com.example.a2021sunlinhackathon.WedviewActivity;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {
    private ArrayList<ShopData> arrayList;
    private Context context;
    public ShopAdapter(ArrayList<ShopData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item,parent,false);
        ShopViewHolder holder = new ShopViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder ,int position) {
        Glide.with(holder.itemView).load(arrayList.get(position).getLeftImageUrl()).into(holder.ib_left);
        Glide.with(holder.itemView).load(arrayList.get(position).getRightImageUrl()).into(holder.ib_right);
        String leftId =arrayList.get(position).getLeftShopId(); //상점 고유 값
        String rightId =arrayList.get(position).getRightShopId(); //상점 고유 값
        holder.ib_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mItt = new Intent(context, WedviewActivity.class);
                mItt.putExtra("url",arrayList.get(position).getLeftOutUrl());
                context.startActivity(mItt);
            }
        });
        holder.ib_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mItt = new Intent(context, WedviewActivity.class);
                mItt.putExtra("url",arrayList.get(position).getRightOutUrl());
                context.startActivity(mItt);
            }
        });
    }
    @Override
    public int getItemCount() { return (arrayList != null ? arrayList.size() : 0);}

    public class ShopViewHolder extends RecyclerView.ViewHolder {
        public ImageButton ib_left;
        public ImageButton ib_right;
        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            ib_left = itemView.findViewById(R.id.leftImageButton);
            ib_right = itemView.findViewById(R.id.rightImageButton);
        }
    }

}
