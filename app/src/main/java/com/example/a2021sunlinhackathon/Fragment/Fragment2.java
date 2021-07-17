package com.example.a2021sunlinhackathon.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2021sunlinhackathon.Activity.LodingActivity;
import com.example.a2021sunlinhackathon.Activity.MainActivity;
import com.example.a2021sunlinhackathon.Data.Database;
import com.example.a2021sunlinhackathon.R;
import com.example.a2021sunlinhackathon.databinding.ActivityIllustratedbookBinding;
import com.example.a2021sunlinhackathon.databinding.Fragment2Binding;
import com.example.a2021sunlinhackathon.databinding.Fragment4Binding;
import com.example.a2021sunlinhackathon.illustratedbook;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {
    Database data=new Database();
    boolean isOn = false;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Fragment2Binding binding;
    public Fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    FirebaseAuth firebaseAuth;
    int water;
    int plant;
    int kind;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Fragment2Binding binding = Fragment2Binding.inflate(inflater, container, false);

        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        binding.bbbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.bbbb.setVisibility(View.INVISIBLE);
                data.ppp(getContext(),0);
            }
        });
        DatabaseReference w = database.getReference("UserProfile").child(uid).child("water");
        w.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                water= dataSnapshot.getValue(Integer.class);
                binding.waterCnt.setText(water+"");

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


        DatabaseReference k = database.getReference("UserProfile").child(uid).child("kind");
        k.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                kind= dataSnapshot.getValue(Integer.class);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        DatabaseReference j = database.getReference("UserProfile").child(uid).child("plant");
        j.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                plant= dataSnapshot.getValue(Integer.class);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        String a=data.flow(kind);
        binding.ftlite.setText(a);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), illustratedbook.class);
                startActivity(intent);
            }
        });
        binding.imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.waterplat(getContext(), water,plant);
                data.lvup(water,kind,binding.plant);

            }
        });

        binding.WaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOn) {
                    isOn =false;
                    binding.helpText.setVisibility(View.INVISIBLE);
                } else {
                    isOn =true;
                    binding.helpText.setVisibility(View.VISIBLE);
                }
            }
        });
        return binding.getRoot();
    }
}