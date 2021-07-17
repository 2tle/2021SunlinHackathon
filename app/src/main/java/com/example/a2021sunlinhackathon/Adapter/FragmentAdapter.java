package com.example.a2021sunlinhackathon.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.a2021sunlinhackathon.DonateFragment;
import com.example.a2021sunlinhackathon.ShopFragment;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> items;


    private ArrayList<String> itext=new ArrayList<String>();

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
        items = new ArrayList<Fragment>();
        items.add(new ShopFragment());
        items.add(new DonateFragment());

        itext.add("친환경 제품판매");
        itext.add("지구를 위한 기부 ");

    }

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @NonNull
    @Override
    public CharSequence getPageTitle(int position){
        return itext.get(position);
    }
}


