package com.example.a2021sunlinhackathon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a2021sunlinhackathon.Activity.DonationActivity;
import com.example.a2021sunlinhackathon.Activity.WedviewActivity;
import com.example.a2021sunlinhackathon.Data.DonationData;
import com.example.a2021sunlinhackathon.R;

import java.util.ArrayList;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.DonationViewHolder> {
    private ArrayList<DonationData> arrayList;
    private Context context;
    public DonationAdapter(ArrayList<DonationData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @Override
    public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donate_item,parent,false);
        DonationViewHolder holder = new DonationViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull DonationViewHolder holder, int position) {
        Glide.with(holder.itemView).load(arrayList.get(position).getLdPhotoUrl()).into(holder.ib_left);
        Glide.with(holder.itemView).load(arrayList.get(position).getRdPhotoUrl()).into(holder.ib_right);
        String leftId =arrayList.get(position).getLdName(); //상점 고유 값
        String rightId =arrayList.get(position).getRdName(); //상점 고유 값
        holder.ib_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mItt = new Intent(context, DonationActivity.class);
                mItt.putExtra("url",arrayList.get(position).getLdOutUrl());
                mItt.putExtra("name",arrayList.get(position).getLdName());
                mItt.putExtra("exp",arrayList.get(position).getLdExp());
                mItt.putExtra("imgUrl",arrayList.get(position).getLdPhotoUrl());
                context.startActivity(mItt);
            }
        });
        holder.ib_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mItt = new Intent(context, DonationActivity.class);
                mItt.putExtra("url",arrayList.get(position).getRdOutUrl());
                mItt.putExtra("name",arrayList.get(position).getRdName());
                mItt.putExtra("exp",arrayList.get(position).getRdExp());
                mItt.putExtra("imgUrl",arrayList.get(position).getRdPhotoUrl());
                context.startActivity(mItt);
            }
        });
    }
    @Override
    public int getItemCount() { return (arrayList != null ? arrayList.size() : 0);}

    public class DonationViewHolder extends RecyclerView.ViewHolder {
        public ImageView ib_left;
        public ImageView ib_right;
        public DonationViewHolder(@NonNull View itemView) {
            super(itemView);
            ib_left = itemView.findViewById(R.id.leftImageButton1);
            ib_right = itemView.findViewById(R.id.rightImageButton1);
        }
    }

}
