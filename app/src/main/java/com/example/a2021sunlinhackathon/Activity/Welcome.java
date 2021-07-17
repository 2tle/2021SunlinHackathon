package com.example.a2021sunlinhackathon.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
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

        binding.text1.setText("안녕하세요\n"+name+"회원님");

       binding.button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Welcome.this, WelcomeActivity2.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
               startActivity(intent);
               finish();
           }
       });



    }
}