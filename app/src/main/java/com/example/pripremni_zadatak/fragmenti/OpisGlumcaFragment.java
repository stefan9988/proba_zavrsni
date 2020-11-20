package com.example.pripremni_zadatak.fragmenti;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pripremni_zadatak.R;
import com.example.pripremni_zadatak.model.Glumac;
import com.example.pripremni_zadatak.model.MyHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OpisGlumcaFragment extends Fragment {
    private TextView textView;
    private List<Glumac>glumacList;
    private MyHelper myHelper;


    public OpisGlumcaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        refresh();
        return inflater.inflate(R.layout.fragment_opis_glumca, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView=view.findViewById(R.id.tvJEBENAPROBA);
        textView.setText(glumacList.isEmpty()+"");
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
}