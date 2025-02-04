package com.example.a2021sunlinhackathon.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.a2021sunlinhackathon.Activity.WriteActivity;
import com.example.a2021sunlinhackathon.Adapter.PostAdapter;
import com.example.a2021sunlinhackathon.Data.PostData;
import com.example.a2021sunlinhackathon.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class Fragment1 extends Fragment implements OnMapReadyCallback{
    private ArrayList<PostData> arrayList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup fragment1 = (ViewGroup)inflater.inflate(R.layout.fragment_1,container,false);

        ImageButton addpost= (ImageButton) fragment1.findViewById(R.id.addpost);
        addpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WriteActivity.class);
                startActivity(intent);
            }
        });
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        recyclerView = (RecyclerView) fragment1.findViewById(R.id.recycler1);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);



        출처: https://link2me.tistory.com/1858 [소소한 일상 및 업무TIP 다루기]
        arrayList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("Posts");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot s : snapshot.getChildren()) {
                    try {
                        boolean isHeart = false;
                        ArrayList<String> temp = new ArrayList<>();
                        for(int i = 0; i < s.child("heart").getChildrenCount(); i++) {
                            //s.child("heart").child(get)
                            temp.add(s.child("heart").child(i+"").getValue().toString());
                            if(s.child("heart").child(i + "").getValue().toString().equals(uid)) isHeart = true;
                        }

                        PostData postData = new PostData(s.child("name").getValue().toString(),s.child("post").getValue().toString(),s.child("uid").getValue().toString(), s.child("addars").getValue().toString(), s.child("postid").getValue().toString(), isHeart);
                        postData.setHeart(temp);
                        postData.setCount(Integer.parseInt(s.child("count").getValue().toString()));
                        arrayList.add(postData);
                    } catch (Exception e) {
                        Log.e(">",e.getMessage());
                    }
                    /*
                    PostData postData = snapshot1.getValue(PostData.class);
                    arrayList.add(postData); */
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.e(">fragment1>",String.valueOf(error.toException()));
            }
        });
        adapter = new PostAdapter(arrayList, getContext());
        recyclerView.setAdapter(adapter);






        return fragment1;
    }


    @Override
    public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {

    }
}