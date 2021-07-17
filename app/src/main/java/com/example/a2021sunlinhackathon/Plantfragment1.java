package com.example.a2021sunlinhackathon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2021sunlinhackathon.Adapter.BookListAdapter;
import com.example.a2021sunlinhackathon.Data.BookListData;
import com.example.a2021sunlinhackathon.Data.ProfilePostData;
import com.example.a2021sunlinhackathon.databinding.FragmentPlantfragment1Binding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Plantfragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Plantfragment1 extends Fragment {
    private ArrayList<BookListData> arrayList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Plantfragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Plantfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Plantfragment1 newInstance(String param1, String param2) {
        Plantfragment1 fragment = new Plantfragment1();
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
        FragmentPlantfragment1Binding binding = FragmentPlantfragment1Binding.inflate(inflater, container,false);
        recyclerView = (RecyclerView) binding.getRoot().findViewById(R.id.recyclerp1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        for(int i = 0; i< 3 ; i++) {
            BookListData blDt = new BookListData();
            blDt.setPlantText1("첫번째");
            blDt.setPlantText2("두번째");
            blDt.setPlantText3("세번째");

            blDt.setPlantUrl1("url1");
            blDt.setPlantUrl2("url2");
            blDt.setPlantUrl3("url3");
            arrayList.add(blDt);
        }
        BookListAdapter bookListAdapter=new BookListAdapter(arrayList);


        recyclerView.setAdapter(bookListAdapter);
        return binding.getRoot();
    }
}