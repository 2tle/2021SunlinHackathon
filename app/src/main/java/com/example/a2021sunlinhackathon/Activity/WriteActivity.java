package com.example.a2021sunlinhackathon.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.a2021sunlinhackathon.R;
import com.example.a2021sunlinhackathon.databinding.ActivityWriteBinding;

public class WriteActivity extends AppCompatActivity {
    private ActivityWriteBinding binding;
    private final int GET_GALLERY_IMAGE = 200;
    Uri selectedImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        binding = ActivityWriteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, GET_GALLERY_IMAGE);
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WriteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        binding.sendPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            Glide.with(getApplicationContext()).load(selectedImageUri).into(binding.uploadImageView);


        }
    }
}