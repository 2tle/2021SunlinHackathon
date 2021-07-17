package com.example.a2021sunlinhackathon.Fragment.WelcomeFragment;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a2021sunlinhackathon.Activity.MainActivity;
import com.example.a2021sunlinhackathon.Activity.Welcome;
import com.example.a2021sunlinhackathon.Activity.WelcomeActivity2;
import com.example.a2021sunlinhackathon.R;

public class WelcomeFragment3 extends Fragment {

    private WelcomeFragment3ViewModel mViewModel;

    public static WelcomeFragment3 newInstance() {
        return new WelcomeFragment3();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.welcome_fragment3_fragment, container, false);
        Button button=v.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WelcomeFragment3ViewModel.class);
        // TODO: Use the ViewModel
    }

}