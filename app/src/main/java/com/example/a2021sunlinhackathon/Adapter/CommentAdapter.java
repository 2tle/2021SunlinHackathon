package com.example.a2021sunlinhackathon.Adapter;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a2021sunlinhackathon.Activity.CommentActivity;
import com.example.a2021sunlinhackathon.Data.CommentData;
import com.example.a2021sunlinhackathon.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private ArrayList<CommentData> arrayList;
    private Context context;
    public CommentAdapter(ArrayList<CommentData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        CommentViewHolder holder = new CommentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentViewHolder holder, int position) {
        holder.tv_comment.setText(arrayList.get(position).getCommentComment());
        holder.tv_username.setText(arrayList.get(position).getCommentUsername());
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child("userImages/"+arrayList.get(position).getCommentUserProfile()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri).into(holder.iv_profile);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_profile;
        public TextView tv_username;
        public TextView tv_comment;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_profile = itemView.findViewById(R.id.commentProfile);
            tv_username = itemView.findViewById(R.id.commentUsername);
            tv_comment = itemView.findViewById(R.id.commentComment);
            iv_profile.setBackground(new ShapeDrawable(new OvalShape()));
            iv_profile.setClipToOutline(true);

        }
    }
}
