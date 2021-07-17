package com.example.a2021sunlinhackathon.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a2021sunlinhackathon.Data.Database;
import com.example.a2021sunlinhackathon.R;
import com.example.a2021sunlinhackathon.Model.UserModel;
import com.example.a2021sunlinhackathon.databinding.ActivityJoinBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

public class JoinActivity extends AppCompatActivity {
    ActivityJoinBinding binding;
    String id;
    FirebaseAuth firebaseAuth;
    boolean emailtrue=false;
    boolean pawtrue=false;
    boolean naemtrue=false;

    Uri selectedImageUri;
    private final int GET_GALLERY_IMAGE = 200;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        firebaseAuth = FirebaseAuth.getInstance();
        binding = ActivityJoinBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.profile.setBackground(new ShapeDrawable(new OvalShape()));
        binding.profile.setClipToOutline(true);

        firebaseAuth = FirebaseAuth.getInstance();


        String name=binding.nameinput.getText().toString();



        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);

            }
        });

        binding.idinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emile=binding.idinput.getText().toString();
                if(emile.indexOf("@")!=-1){
                    binding.idtext.setVisibility(View.INVISIBLE);
                    emailtrue=true;
                }else{
                    binding.idtext.setVisibility(View.VISIBLE);
                    emailtrue=false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        binding.pawinput2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String p=binding.pawinput1.getText().toString();
                String p1= binding.pawinput2.getText().toString();
                if(p.equals(p1)){
                    binding.pwetext.setVisibility(View.INVISIBLE);
                    pawtrue=true;
                    Log.d("문자",p+p1);
                }else{
                    binding.pwetext.setVisibility(View.VISIBLE);
                    pawtrue=false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.nameinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String p=binding.pawinput1.getText().toString();
                String p1= binding.pawinput2.getText().toString();
                String emile=binding.idinput.getText().toString();
                String name=binding.nameinput.getText().toString();
                if(p!=null&&p1!=null&&emile!=null&&name!=null){
                    binding.joinbtn.setBackground(getDrawable(R.drawable.rounded_btncolor));
                }
                if (name==null){
                    naemtrue=false;
                    binding.nametext.setVisibility(View.VISIBLE);

                }else{
                    naemtrue=true;
                    binding.nametext.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.profile.getDrawable()==null){
                    binding.profile.setImageResource(R.drawable.ic_launcher_foreground);
                }
                if(pawtrue&&emailtrue&&naemtrue) {
                    final String email = binding.idinput.getText().toString().trim();
                    final String pwe = binding.pawinput1.getText().toString().trim();
                    final String name =binding.nameinput.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(email, pwe)
                            .addOnCompleteListener(JoinActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        int idx = email.indexOf("@");
                                        id = email.substring(0, idx);
                                        Log.d("adsaf"," "+email+ id+ pwe+JoinActivity.this);
                                        Database database =new Database();
                                        database.login(JoinActivity.this, email, id, pwe);
                                        String uid=task.getResult().getUser().getUid();

                                        FirebaseStorage.getInstance().getReference().child("userImages").child(uid).putFile(selectedImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                @SuppressWarnings("VisibleForTests")
                                                String imageUrl = task.getResult().getStorage().getDownloadUrl().toString();
                                                UserModel userModel = new UserModel();
                                                userModel.id = id;
                                                userModel.name = name;
                                                userModel.profileImageUrl = imageUrl;
                                                userModel.uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                                FirebaseDatabase.getInstance().getReference().child("UserProfile").child(uid).setValue(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Intent intent = new Intent(JoinActivity.this, Welcome.class);
                                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                });
                                            }
                                        });



                                    } else {
                                        Toast.makeText(JoinActivity.this, "등록 에러", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            });

                }else {
                    Toast.makeText(JoinActivity.this,"형식을 확인해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            Glide.with(getApplicationContext()).load(selectedImageUri).into(binding.profile);


        }

    }





}