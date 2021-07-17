package com.example.a2021sunlinhackathon.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.a2021sunlinhackathon.Data.Database;
import com.example.a2021sunlinhackathon.Fragment.Fragment1;
import com.example.a2021sunlinhackathon.Fragment.Fragment2;
import com.example.a2021sunlinhackathon.Fragment.Fragment3;
import com.example.a2021sunlinhackathon.Fragment.Fragment4;
import com.example.a2021sunlinhackathon.R;
import com.example.a2021sunlinhackathon.databinding.ActivityMainBinding;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager=getSupportFragmentManager();
    private AdView mAdView;
    private BottomNavigationView mBottomNV;
    ActivityMainBinding binding;
    Database database=new Database();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        SharedPreferences sf = getSharedPreferences("User", MODE_PRIVATE);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);









        getSupportFragmentManager().beginTransaction().replace(R.id.bottomview,new Fragment1()).commit();

        binding.bottomnavi.setItemSelected(R.id.item_fragment1,true);
        binding.bottomnavi.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case R.id.item_fragment1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.bottomview,new Fragment1()).commit();
                        break;
                    case R.id.item_fragment2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.bottomview,new Fragment2()).commit();
                        break;
                    case R.id.item_fragment3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.bottomview,new Fragment3()).commit();
                        break;
                    case R.id.item_fragment4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.bottomview,new Fragment4()).commit();
                        break;
                }
            }
        });
    }
}