package com.example.a2021sunlinhackathon.Adapter;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2021sunlinhackathon.Data.BookListData;
import com.example.a2021sunlinhackathon.R;


import java.util.ArrayList;


public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListViewHolder> {
    private ArrayList<BookListData> arrayList;
    private Context context;
    public BookListAdapter(ArrayList<BookListData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @Override
    public BookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booklist,parent,false);
        BookListViewHolder holder = new BookListViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull BookListViewHolder holder ,int position) {
        // 이미지 넣는것만 하세요.
        holder.iv_plant1.setImageResource(R.drawable.ic_launcher_foreground);
        holder.iv_plant2.setImageResource(R.drawable.ic_launcher_foreground);
        holder.iv_plant3.setImageResource(R.drawable.ic_launcher_foreground);


        //여긴 텍스트
        holder.tv_plant1.setText(arrayList.get(position).getPlantText1());
        holder.tv_plant2.setText(arrayList.get(position).getPlantText2());
        holder.tv_plant3.setText(arrayList.get(position).getPlantText3());

    }
    @Override
    public int getItemCount() {return (arrayList != null ? arrayList.size() : 0); }
    public class BookListViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_plant1;
        public TextView tv_plant1;
        public ImageView iv_plant2;
        public TextView tv_plant2;
        public ImageView iv_plant3;
        public TextView tv_plant3;
        public BookListViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_plant1 = itemView.findViewById(R.id.imageview1);
            tv_plant1 = itemView.findViewById(R.id.text1);

            iv_plant2 = itemView.findViewById(R.id.imageview2);
            tv_plant2 = itemView.findViewById(R.id.text2);

            iv_plant3 = itemView.findViewById(R.id.imageview3);
            tv_plant3 = itemView.findViewById(R.id.text3);

            iv_plant1.setBackground(new ShapeDrawable(new OvalShape()));
            iv_plant1.setClipToOutline(true);

            iv_plant2.setBackground(new ShapeDrawable(new OvalShape()));
            iv_plant2.setClipToOutline(true);

            iv_plant3.setBackground(new ShapeDrawable(new OvalShape()));
            iv_plant3.setClipToOutline(true);
        }
    }
}
