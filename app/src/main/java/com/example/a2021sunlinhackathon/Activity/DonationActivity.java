package com.example.a2021sunlinhackathon.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.a2021sunlinhackathon.R;
import com.example.a2021sunlinhackathon.databinding.ActivityDonationBinding;

public class DonationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        ActivityDonationBinding binding = ActivityDonationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.doName.setText(getIntent().getExtras().getString("name"));
        binding.doExp.setText(getIntent().getExtras().getString("exp"));
        Glide.with(this).load(getIntent().getExtras().getString("imgUrl")).into(binding.doImg);
        binding.doBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });
        binding.goDoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getBaseContext(), WedviewActivity.class);
                it.putExtra("url",getIntent().getExtras().getString("url"));
                startActivity(it);
            }
        });

    }
}