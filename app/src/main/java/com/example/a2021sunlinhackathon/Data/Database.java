package com.example.a2021sunlinhackathon.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;

import com.example.a2021sunlinhackathon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Database {
    public void ppp(Context context,int a){
        if(a==0) {

        }else{
            int ran=(int)(Math.random()*9)+1;

            SharedPreferences sharedPreferences= context.getSharedPreferences("User",context.MODE_PRIVATE);    // test 이름의 기본모드 설정
            SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
            editor.putInt("kind",ran);
            editor.commit();


        }
    }

    public String flow(int num) {

        switch (num) {
            case 1:
                return "해바라기";

            case 2:
                return "벚꽃";

            case 3:
                return "뻐끔플라워";

            case 4:
                return "나팔꽃";

            case 5:
                return "등나무";

            case 6:
                return "단풍나무";

            case 7:
                return "사과나무";

            case 8:
                return "수국";

            case 9:
                return "튤립";

            case 10:
                return "마리모";


        }
       return "툴립";
    }
    public void lvup(int w , int f ,ImageView imageView){
        if(w<10){
            switch (f){
                case 1:
                    imageView.setImageResource(R.drawable.flower1);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.tree1);
                    break;
                case 3:

                    break;
                case 4:
                    imageView.setImageResource(R.drawable.flower1);
                    break;
                case 5:
                    imageView.setImageResource(R.drawable.tree1);
                    break;
                case 6:
                    imageView.setImageResource(R.drawable.tree1);
                    break;
                case 7:
                    imageView.setImageResource(R.drawable.tree1);
                    break;
                case 8:
                    break;
                case 9:
                    imageView.setImageResource(R.drawable.flower1);
                    break;
                case 10:
                    imageView.setImageResource(R.drawable.ma1);
                    break;


            }
        }else if(w>=10&&w<15){
            switch (f){
                case 1:
                    imageView.setImageResource(R.drawable.flower2);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.tree2);
                    break;
                case 3:

                    break;
                case 4:
                    imageView.setImageResource(R.drawable.flower2);
                    break;
                case 5:
                    imageView.setImageResource(R.drawable.tree2);
                    break;
                case 6:
                    imageView.setImageResource(R.drawable.tree2);
                    break;
                case 7:
                    imageView.setImageResource(R.drawable.tree2);
                    break;
                case 8:
                    break;
                case 9:
                    imageView.setImageResource(R.drawable.flower2);
                    break;
                case 10:
                    imageView.setImageResource(R.drawable.ma2);
                    break;


            }

        }else if(w>=15&&w<25){
            switch (f){
                case 1:
                    imageView.setImageResource(R.drawable.flower3);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.tree3);
                    break;
                case 3:

                    break;
                case 4:
                    imageView.setImageResource(R.drawable.flower3);
                    break;
                case 5:
                    imageView.setImageResource(R.drawable.tree3);
                    break;
                case 6:
                    imageView.setImageResource(R.drawable.tree3);
                    break;
                case 7:
                    imageView.setImageResource(R.drawable.tree3);
                    break;
                case 8:
                    break;
                case 9:
                    imageView.setImageResource(R.drawable.flower3);
                    break;
                case 10:
                    imageView.setImageResource(R.drawable.ma3);
                    break;


            }

        }else if(w>=25&&w<40){



        }else if (w>=40){

        }
    }
    public void login(Context context, String email, String id, String pwe) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Login", context.MODE_PRIVATE);    // test 이름의 기본모드 설정
        SharedPreferences.Editor editor = sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
        editor.putString("id", id);
        editor.putString("pwe", pwe);
        editor.putString("email", email);

        editor.commit();


    }

    public void user(Context context, String id, String name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", context.MODE_PRIVATE);    // test 이름의 기본모드 설정
        SharedPreferences.Editor editor = sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
        editor.putString("id", id);
        editor.putString("name", name);
        editor.commit();
    }

    public void water(Context context,int getwater,int num) {

        switch (num) {
            case 1:
                getwater = getwater + 3;
                break;
            case 2:
                getwater = getwater + 1;
                break;
            case 3:
                getwater = getwater + 1;
                break;


        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("Water", context.MODE_PRIVATE);    // test 이름의 기본모드 설정
        SharedPreferences.Editor editor = sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
        editor.putInt("water", getwater);
        editor.commit();
        String a = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("UserProfile/" + a).child("water");
        myRef.setValue(getwater);


    }
    public void updata(){

    }
    public void waterplat(Context context,int water,int plant) {
        Log.d("asdf",plant+"|| "+water);

        String a = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef2 = database.getReference("UserProfile/" + a).child("plant");
        myRef2.setValue(plant+water);
        SharedPreferences sp = context.getSharedPreferences("Water", context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sp.edit();
        editor1.putInt("plant", plant+water);
        editor1.commit();
        SharedPreferences sharedPreferences = context.getSharedPreferences("Water", context.MODE_PRIVATE);    // test 이름의 기본모드 설정
        SharedPreferences.Editor editor = sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
        editor.putInt("water", 0);
        editor.commit();
        DatabaseReference myRef = database.getReference("UserProfile/" + a).child("water");
        myRef.setValue(0);



    }



}
