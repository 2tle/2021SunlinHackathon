package com.example.a2021sunlinhackathon;

import android.content.Context;
import android.content.SharedPreferences;

public class Database {

    public  void login(Context context , String email, String id, String pwe){
        SharedPreferences sharedPreferences= context.getSharedPreferences("Login",context.MODE_PRIVATE);    // test 이름의 기본모드 설정
        SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
        editor.putString("id",id);
        editor.putString("pwe",pwe);
        editor.putString("email",email);
        editor.commit();


    }
    public void user(Context context,String id,String name,String fropil){
        SharedPreferences sharedPreferences= context.getSharedPreferences("User",context.MODE_PRIVATE);    // test 이름의 기본모드 설정
        SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
        editor.putString("id",id);
        editor.putString("name",name);
        editor.putString("fropil",fropil);
        editor.commit();
    }
}
