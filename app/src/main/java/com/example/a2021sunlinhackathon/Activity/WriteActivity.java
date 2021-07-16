package com.example.a2021sunlinhackathon.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.a2021sunlinhackathon.R;
import com.example.a2021sunlinhackathon.databinding.ActivityWriteBinding;

public class WriteActivity extends AppCompatActivity {
    private ActivityWriteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        binding = ActivityWriteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }
}