package com.example.a2021sunlinhackathon.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.a2021sunlinhackathon.Adapter.CommentAdapter;
import com.example.a2021sunlinhackathon.Data.CommentData;
import com.example.a2021sunlinhackathon.Data.PostData;
import com.example.a2021sunlinhackathon.R;
import com.example.a2021sunlinhackathon.databinding.ActivityCommentBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommentActivity extends AppCompatActivity {
    private ArrayList<CommentData> arrayList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private String postId;
    String tltile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        SharedPreferences sharedPreferences= getSharedPreferences("User",MODE_PRIVATE);    // test 이름의 기본모드 설정
        String name = sharedPreferences.getString("name","");

        ActivityCommentBinding binding = ActivityCommentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        postId = getIntent().getStringExtra("id");
        Log.d(">>>",postId);
        String useruid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        StorageReference stRef = FirebaseStorage.getInstance().getReference();
        stRef.child("userImages/"+useruid).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(view).load(uri).into(binding.writerProfile);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {

            }
        });
        binding.writerProfile.setBackground(new ShapeDrawable(new OvalShape()));
        binding.writerProfile.setClipToOutline(true);
        recyclerView = (RecyclerView)binding.commentRecycler;
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference("Posts/"+postId+"/comment");

        String time=getTime();
        binding.sendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Posts").child(postId).child("comment").child(useruid+time).child("comment").setValue(binding.commentInput.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        FirebaseDatabase.getInstance().getReference().child("Posts").child(postId).child("comment").child(useruid+time).child("id").setValue(useruid);
                        FirebaseDatabase.getInstance().getReference().child("Posts").child(postId).child("comment").child(useruid+time).child("name").setValue(name);
                        binding.commentInput.setText("");
                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                arrayList.clear();
                                for(DataSnapshot s : snapshot.getChildren()) {
                                    try {
                                        CommentData cd = new CommentData();
                                        cd.setCommentUserProfile(s.child("id").getValue().toString());
                                        cd.setCommentComment(s.child("comment").getValue().toString());
                                        //cd.setCommentUsername("TEST");
                                        cd.setCommentUsername(s.child("name").getValue().toString());
                                        arrayList.add(cd);
                                    } catch(Exception e) {
                                        Log.e(">",e.getMessage());
                                    }

                                }
                                adapter.notifyDataSetChanged();

                            }

                            @Override
                            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                            }
                        });


                    }
                });


            }
        });
        binding.cmtBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getBaseContext(), MainActivity.class);
                startActivity(it);
            }
        });



        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot s : snapshot.getChildren()) {
                    try {
                        CommentData cd = new CommentData();
                        cd.setCommentUserProfile(s.child("id").getValue().toString());
                        cd.setCommentComment(s.child("comment").getValue().toString());
                        //cd.setCommentUsername("TEST");
                        cd.setCommentUsername(s.child("name").getValue().toString());
                        arrayList.add(cd);
                    } catch(Exception e) {
                        Log.e(">",e.getMessage());
                    }

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        adapter = new CommentAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);


    }
    private String getTime(){
        SimpleDateFormat mFormat = new SimpleDateFormat(" yyyy-MM-dd k:mm:ss");
        long mNow;
        Date mDate;
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
}