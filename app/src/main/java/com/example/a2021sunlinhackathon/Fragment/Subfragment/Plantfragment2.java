package com.example.a2021sunlinhackathon.Fragment.Subfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2021sunlinhackathon.Adapter.BookListAdapter;
import com.example.a2021sunlinhackathon.Data.BookListData;
import com.example.a2021sunlinhackathon.R;
import com.example.a2021sunlinhackathon.databinding.FragmentPlantfragment2Binding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Plantfragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Plantfragment2 extends Fragment {
    private ArrayList<BookListData> arrayList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FragmentPlantfragment2Binding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Plantfragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Plantfragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Plantfragment2 newInstance(String param1, String param2) {
        Plantfragment2 fragment = new Plantfragment2();
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
        FragmentPlantfragment2Binding binding = FragmentPlantfragment2Binding.inflate(inflater, container,false);
        recyclerView = (RecyclerView) binding.getRoot().findViewById(R.id.recyclerp2);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        BookListData blDt = new BookListData();
        blDt.setPlantText1("사과나무");
        blDt.setPlantText2("수국");
        blDt.setPlantText3("튤립");

        blDt.setPlantUrl1("url1");
        blDt.setPlantUrl2("url2");
        blDt.setPlantUrl3("url3");
        arrayList.add(blDt);
        BookListData blDt2 = new BookListData();

        blDt2.setPlantText1("마리모");
        blDt2.setPlantText2("추후 공개 예정");
        blDt2.setPlantText3("추후 공개 예정");

        blDt2.setPlantUrl1("url1");
        blDt2.setPlantUrl2("url2");
        blDt2.setPlantUrl3("url3");
        arrayList.add(blDt2);


        adapter = new BookListAdapter(arrayList, getContext());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        return binding.getRoot();
    }
}