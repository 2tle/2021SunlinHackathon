package com.example.a2021sunlinhackathon.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.a2021sunlinhackathon.R;
import com.example.a2021sunlinhackathon.databinding.ActivityMainBinding;
import com.example.a2021sunlinhackathon.databinding.ActivityProfilePostBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ProfilePostActivity extends AppCompatActivity {
    ActivityProfilePostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_post);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        binding = ActivityProfilePostBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.fragBackButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        Log.d(">>>",getIntent().getStringExtra("uid"));
        storageReference.child("userImages/"+getIntent().getStringExtra("uid")).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).into(binding.postProfileImage1);
            }
        });
        String useruid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        storageReference.child("userImages/"+useruid).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).into(binding.userProfileImage1);
            }
        });

        storageReference.child("postImages/"+getIntent().getStringExtra("postid")).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).into(binding.postImageView1);
            }
        });
        binding.userNameTextView1.setText(getIntent().getStringExtra("name"));
        binding.location1.setText(getIntent().getStringExtra("addars"));
        binding.postText1.setText(getIntent().getStringExtra("post"));





    }
}