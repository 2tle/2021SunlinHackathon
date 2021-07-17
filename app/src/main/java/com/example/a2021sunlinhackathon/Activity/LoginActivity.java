package com.example.a2021sunlinhackathon.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.a2021sunlinhackathon.Data.Database;
import com.example.a2021sunlinhackathon.R;
import com.example.a2021sunlinhackathon.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    private ActivityLoginBinding binding;
    String uid;
    String id;
    String name;
    String frofile;
    boolean idchak=false;
    boolean pwechak=false;
    Database database=new Database();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        SharedPreferences sf = getSharedPreferences("Login",MODE_PRIVATE);
        String email = sf.getString("email","");
        String pwe = sf.getString("pwe","");

        binding.idinput.setText(email);
        binding.pweinput.setText(pwe);
        if(email!=null&&pwe!=null){
            idchak=true;
            pwechak=true;
        }
        binding.idinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String id=binding.idinput.getText().toString();
                idchak=true;
                if (pwechak&&idchak){
                    binding.loginbtn.setBackground(getDrawable(R.drawable.rounded_btncolor));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String id=binding.idinput.getText().toString();
                idchak=true;
                if (pwechak&&idchak){
                    binding.loginbtn.setBackground(getDrawable(R.drawable.rounded_btncolor));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.pweinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                pwechak=true;
                if (pwechak&&idchak){
                    binding.loginbtn.setBackground(getDrawable(R.drawable.rounded_btncolor));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String id=binding.idinput.getText().toString();
                idchak=true;
                if (pwechak&&idchak){
                    binding.loginbtn.setBackground(getDrawable(R.drawable.rounded_btncolor));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        firebaseAuth = firebaseAuth.getInstance();//firebaseAuth의 인스턴스를 가져옴
        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pwechak==true&&idchak==true){
                    String email = binding.idinput.getText().toString().trim();

                    if(email.contains("@")==true){
                        int idx = email.indexOf("@");
                        String id = email.substring(0, idx);
                        String pwd = binding.pweinput.getText().toString().trim();
                        firebaseAuth.signInWithEmailAndPassword(email, pwd)
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {//성공했을때
                                            database.login(LoginActivity.this, email, id, pwd);
                                            Log.d("adsf",pwd+"");

                                            uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                                            logine();
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }else {
                        Toast.makeText(LoginActivity.this, "이메일 형식이 맞지 않습니다", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "빈 칸을 전부 채워주세요", Toast.LENGTH_SHORT).show();
                }

            }


        });

        binding.joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });

    }
    public void logine(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        Log.d("adsf",uid);
        DatabaseReference getname = database.getReference("UserProfile").child(uid).child("name");
        getname.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name= dataSnapshot.getValue(String.class);
                Log.d("asdf","name="+name);
                SharedPreferences sharedPreferences= getSharedPreferences("User",MODE_PRIVATE);    // test 이름의 기본모드 설정
                SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                editor.putString("name",name);
                editor.commit();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        DatabaseReference getid = database.getReference("UserProfile").child(uid).child("id");
        getid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                id= dataSnapshot.getValue(String.class);
                Log.d("asdf","id"+id);
                SharedPreferences sharedPreferences= getSharedPreferences("User",MODE_PRIVATE);    // test 이름의 기본모드 설정
                SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                editor.putString("id",id);
                editor.commit();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        DatabaseReference getfile = database.getReference("UserProfile").child(uid).child("profileImageUrl");
        getfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                frofile= dataSnapshot.getValue(String.class);
                Log.d("asdf",frofile);
                SharedPreferences sharedPreferences= getSharedPreferences("User",MODE_PRIVATE);    // test 이름의 기본모드 설정
                SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                editor.putString("profile",frofile);
                editor.commit();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        DatabaseReference plnat = database.getReference("UserProfile").child(uid).child("plnat");
        plnat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int plnatt= dataSnapshot.getValue(Integer.class);

                SharedPreferences sharedPreferences= getSharedPreferences("User",MODE_PRIVATE);    // test 이름의 기본모드 설정
                SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                editor.putInt("plnat",plnatt);
                editor.commit();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


    }

}