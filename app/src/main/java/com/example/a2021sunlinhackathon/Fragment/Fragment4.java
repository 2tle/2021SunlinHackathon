package com.example.a2021sunlinhackathon.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.a2021sunlinhackathon.Activity.Editprofile;

import com.example.a2021sunlinhackathon.Adapter.PostAdapter;
import com.example.a2021sunlinhackathon.Adapter.ProfilePostAdapter;
import com.example.a2021sunlinhackathon.Data.PostData;
import com.example.a2021sunlinhackathon.Data.ProfilePostData;
import com.example.a2021sunlinhackathon.R;
import com.example.a2021sunlinhackathon.databinding.Fragment4Binding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment4 extends Fragment {
    private ArrayList<ProfilePostData> arrayList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Fragment4Binding binding;
    Uri url;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment4.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment4 newInstance(String param1, String param2) {
        Fragment4 fragment = new Fragment4();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Fragment4Binding binding = Fragment4Binding.inflate(inflater, container, false);
        //set variables in Binding
        binding.profile.setBackground(new ShapeDrawable(new OvalShape()));
        binding.profile.setClipToOutline(true);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("User", getContext().MODE_PRIVATE);    // test 이름의 기본모드 설정
        String name = sharedPreferences.getString("name", "");
        String profile = sharedPreferences.getString("profile", "");
        String id = sharedPreferences.getString("id", "");
        binding.name.setText(name);
        binding.id.setText(id);
        Log.d("adsf", profile);

        recyclerView = (RecyclerView) binding.getRoot().findViewById(R.id.recycler4);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("Posts");



        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        binding.editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Editprofile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }
        });
        storageReference.child("userImages/" + uid).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                url = uri;
                Glide.with(getContext()).load(url).into(binding.profile);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {

            }
        });

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                arrayList.clear();
                ProfilePostData pfPDt = new ProfilePostData();
                int tmp = 0;
                String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                //Log.d(">>>>",snapshot.getChildren());
                for(DataSnapshot s: snapshot.getChildren()) {
                    try {
                        if(userid.equals(s.child("uid").getValue().toString()) && tmp%2==0) {
                            tmp++;
                            boolean isHeart = false;
                            pfPDt.setL_name(s.child("name").getValue().toString());
                            pfPDt.setL_addars(s.child("name").getValue().toString());
                            pfPDt.setL_isHeartPushed(isHeart);
                            pfPDt.setL_post(s.child("post").getValue().toString());
                            pfPDt.setL_postid(s.child("postid").getValue().toString());
                            pfPDt.setL_uid(s.child("uid").getValue().toString());
                        }
                        else if(userid.equals(s.child("uid").getValue().toString()) && tmp%2 == 1) {
                            boolean isHeart = false;
                            tmp++;
                            pfPDt.setR_name(s.child("name").getValue().toString());
                            pfPDt.setR_addars(s.child("name").getValue().toString());
                            pfPDt.setR_isHeartPushed(isHeart);
                            pfPDt.setR_post(s.child("post").getValue().toString());
                            pfPDt.setR_postid(s.child("postid").getValue().toString());
                            pfPDt.setR_uid(s.child("uid").getValue().toString());
                            arrayList.add(pfPDt);
                        }

                    } catch (Exception e) {
                        Log.e(">",e.getMessage());
                    }
                }
                if(tmp%2 == 1) {
                    Log.d("cnt>","ASDf");
                    pfPDt.setR_post(null);
                    pfPDt.setR_addars(null);
                    pfPDt.setR_isHeartPushed(false);
                    pfPDt.setR_name(null);
                    pfPDt.setR_uid(null);
                    pfPDt.setR_postid("NODATA");
                    arrayList.add(pfPDt);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        adapter = new ProfilePostAdapter(arrayList,getContext());
        recyclerView.setAdapter(adapter);

        return binding.getRoot();

    }


}