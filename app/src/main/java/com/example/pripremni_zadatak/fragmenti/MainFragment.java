package com.example.pripremni_zadatak.fragmenti;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pripremni_zadatak.R;
import com.example.pripremni_zadatak.adapteri.MyRecAdapter;
import com.example.pripremni_zadatak.model.Glumac;
import com.example.pripremni_zadatak.model.MyHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements MyRecAdapter.MyOnClickListener {
    private RecyclerView recyclerView;
    private MyHelper myHelper=null;
    private List<Glumac>glumacList;
    private Button btnEmpty;
    private TextView tvEmpty;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        refresh();

        recyclerView=view.findViewById(R.id.rvmain);
        tvEmpty=view.findViewById(R.id.tvEMPTY);
        btnEmpty=view.findViewById(R.id.btnAddGlumac);
        btnEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddGlumacFragment addGlumacFragment=new AddGlumacFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,addGlumacFragment)
                        .addToBackStack(null).commit();
            }
        });

//        if (glumacList.isEmpty()){
//            recyclerView.setVisibility(View.GONE);
//            tvEmpty.setVisibility(View.VISIBLE);
//        }
//        else {
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        MyRecAdapter adapter=new MyRecAdapter(this,glumacList);
        recyclerView.setAdapter(adapter);
    //}

    }

    private void refresh() {
        try {
            glumacList=new ArrayList<>();
            glumacList =getMyhelper().getGlumacDao().queryForAll();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public MyHelper getMyhelper(){
        if (myHelper==null){
            myHelper= OpenHelperManager.getHelper(getActivity(),MyHelper.class);
        }
        return  myHelper;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myHelper!= null){
            OpenHelperManager.releaseHelper();
            myHelper=null;
        }
    }

    @Override
    public void MyOnClick(int id) {
        OpisGlumcaFragment opisGlumcaFragment=new OpisGlumcaFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("Id",id);
        opisGlumcaFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,opisGlumcaFragment)
                .addToBackStack(null).commit();
    }
}