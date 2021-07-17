package com.example.a2021sunlinhackathon.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a2021sunlinhackathon.Data.Database;
import com.example.a2021sunlinhackathon.R;
import com.example.a2021sunlinhackathon.databinding.ActivityMainBinding;
import com.example.a2021sunlinhackathon.databinding.ActivityProfilePostBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ProfilePostActivity extends AppCompatActivity {
    ActivityProfilePostBinding binding;
    int count;
    boolean isHeart;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
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
        count = getIntent().getExtras().getInt("count");
        isHeart = getIntent().getExtras().getBoolean("isHeartPushed");
        binding.userNameTextView1.setText(getIntent().getStringExtra("name"));
        binding.location1.setText(getIntent().getStringExtra("addars"));
        binding.postText1.setText(getIntent().getStringExtra("post"));
        binding.commentButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getBaseContext(), CommentActivity.class);
                it.putExtra("id",getIntent().getStringExtra("postid"));
                startActivity(it);
            }
        });
        binding.heartCount1.setText("좋아요 "+count+"개");
        if(isHeart) {
            binding.heartButton1.setImageResource(R.drawable.ic_fheart);
        } else {
            binding.heartButton1.setImageResource(R.drawable.ic_heart);
        }
        binding.heartButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isHeart) {
                    Toast.makeText(getBaseContext(),"이미 좋아요를 눌렀습니다.",Toast.LENGTH_LONG).show();
                } else {
                    isHeart = true;
                    binding.heartButton1.setImageResource(R.drawable.ic_fheart);
                    count += 1;
                    mDatabase.child("Posts").child(getIntent().getExtras().getString("postid")).child("heart").child(count+"").setValue(useruid);
                    mDatabase.child("Posts").child(getIntent().getExtras().getString("postid")).child("count").setValue(count);
                    binding.heartCount1.setText("좋아요 "+count+"개");
                    SharedPreferences sf = getBaseContext().getSharedPreferences("Water", MODE_PRIVATE);
                    int plant = sf.getInt("plant", 0);
                    int water = sf.getInt("water", 0);
                    Database database = new Database();
                    database.water(getBaseContext(), water,2);
                }
            }
        });





    }
}