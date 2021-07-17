package com.example.a2021sunlinhackathon.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.a2021sunlinhackathon.databinding.ActivityLoginBinding;
import com.example.a2021sunlinhackathon.databinding.ActivityWelcomeBinding;

public class Welcome extends AppCompatActivity {
    ActivityWelcomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        SharedPreferences sharedPreferences= getSharedPreferences("User",MODE_PRIVATE);    // test 이름의 기본모드 설정
        String name = sharedPreferences.getString("name","");
        String profile = sharedPreferences.getString("profile","");
        String id = sharedPreferences.getString("id","");

        binding.text1.setText("안녕하세요\n"+name+"회원님");



    }
}