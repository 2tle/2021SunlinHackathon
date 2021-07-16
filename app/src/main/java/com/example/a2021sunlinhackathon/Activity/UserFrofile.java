package com.example.a2021sunlinhackathon.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.a2021sunlinhackathon.R;
import com.example.a2021sunlinhackathon.databinding.ActivityJoinBinding;
import com.example.a2021sunlinhackathon.databinding.ActivityUserFrofileBinding;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

public class UserFrofile extends AppCompatActivity {
    ActivityUserFrofileBinding binding;
    Uri url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_frofile);
        binding = ActivityUserFrofileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.profile.setBackground(new ShapeDrawable(new OvalShape()));
        binding.profile.setClipToOutline(true);
        SharedPreferences sharedPreferences= getSharedPreferences("User",MODE_PRIVATE);    // test 이름의 기본모드 설정
        String name = sharedPreferences.getString("name","");
        String profile = sharedPreferences.getString("profile","");
        String id = sharedPreferences.getString("id","");

        binding.name.setText(name);
        binding.id.setText(id);
        Log.d("adsf",profile);

        downlodimg();


    }
    public  void downlodimg(){
        String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseStorage storage =FirebaseStorage.getInstance();
        StorageReference storageReference=storage.getReference();
        storageReference.child("userImages/"+uid).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                url=uri;
                Glide.with(UserFrofile.this).load(url).into(binding.profile);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {

            }
        });
    }

}