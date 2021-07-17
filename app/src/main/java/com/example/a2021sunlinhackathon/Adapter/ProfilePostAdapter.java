package com.example.a2021sunlinhackathon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a2021sunlinhackathon.Activity.ProfilePostActivity;
import com.example.a2021sunlinhackathon.Data.PostData;
import com.example.a2021sunlinhackathon.Data.ProfilePostData;
import com.example.a2021sunlinhackathon.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class ProfilePostAdapter extends RecyclerView.Adapter<ProfilePostAdapter.ProfilePostViewHolder> {
    //public
    private ArrayList<ProfilePostData> arrayList;
    private Context context;
    public ProfilePostAdapter(ArrayList<ProfilePostData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }



    @Override
    public ProfilePostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userpofileitem, parent, false);
        ProfilePostViewHolder holder = new ProfilePostViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ProfilePostViewHolder holder, int position) {

        //userProfile => 지금 로그인 된 유저프로필, bestComment => 댓글 있으면 그중에서 좋은 댓글 하나 보여주기

        String useruid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();

        storageReference.child("postImages/"+arrayList.get(position).getL_postid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri).into(holder.iv_leftUserPost);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {

            }
        });
        holder.iv_leftUserPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent로 데이터 싹 넘기
                Intent leftIntent = new Intent(context, ProfilePostActivity.class);
                leftIntent.putExtra("name",arrayList.get(position).getL_name());
                leftIntent.putExtra("post",arrayList.get(position).getL_post());
                leftIntent.putExtra("uid",arrayList.get(position).getL_uid());
                leftIntent.putExtra("addars",arrayList.get(position).getL_addars());
                leftIntent.putExtra("postid",arrayList.get(position).getL_postid());
                leftIntent.putExtra("isHeartPushed",arrayList.get(position).isL_isHeartPushed());
                context.startActivity(leftIntent);
            }
        });
        if(!arrayList.get(position).getR_postid().equals("NODATA")) {
            //holder.iv_rightUserPost.setClickable(true);
            storageReference.child("postImages/"+arrayList.get(position).getR_postid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(context).load(uri).into(holder.iv_rightUserPost);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {

                }
            });
            holder.iv_rightUserPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent로 데이터 싹 넘겨버리기
                    Intent leftIntent = new Intent(context, ProfilePostActivity.class);
                    leftIntent.putExtra("name",arrayList.get(position).getR_name());
                    leftIntent.putExtra("post",arrayList.get(position).getR_post());
                    leftIntent.putExtra("uid",arrayList.get(position).getR_uid());
                    leftIntent.putExtra("addars",arrayList.get(position).getR_addars());
                    leftIntent.putExtra("postid",arrayList.get(position).getR_postid());
                    leftIntent.putExtra("isHeartPushed",arrayList.get(position).isR_isHeartPushed());
                    context.startActivity(leftIntent);
                }
            });
        } else {
            holder.iv_rightUserPost.setVisibility(View.INVISIBLE);
        }




    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }


    public class ProfilePostViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_leftUserPost;
        public ImageView iv_rightUserPost;
        public ProfilePostViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_leftUserPost = itemView.findViewById(R.id.leftUserPost);
            iv_rightUserPost = itemView.findViewById(R.id.rightUserPost);
        }
    }
}
