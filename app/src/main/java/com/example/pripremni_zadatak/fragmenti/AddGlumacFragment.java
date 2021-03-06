package com.example.pripremni_zadatak.fragmenti;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.pripremni_zadatak.R;
import com.example.pripremni_zadatak.model.Glumac;
import com.example.pripremni_zadatak.model.MyHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AddGlumacFragment extends Fragment {
    private EditText etIme;
    private EditText etPrezime;
    private EditText etBio;
    private RatingBar ratingBar;
    private Button btnGotovo;
    private String ime;
    private String prezime;
    private String bio;
    private MyHelper myHelper = null;
    private int id;




    public AddGlumacFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_glumac, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etIme = view.findViewById(R.id.etImeGlumca);
        etPrezime = view.findViewById(R.id.etPrezimeGlumca);
        etBio = view.findViewById(R.id.etBioGlumca);
        ratingBar=view.findViewById(R.id.rbAddGl);
        btnGotovo = view.findViewById(R.id.btnGotovo);
        btnGotovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ime = etIme.getText().toString();
                prezime = etPrezime.getText().toString();
                bio = etBio.getText().toString();



                id=addGlumac();

                MainFragment mainFragment = new MainFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, mainFragment)
                        .commit();
            }
        });




    }


    private int addGlumac() {
        Glumac glumac = new Glumac();
        glumac.setIme(ime);
        glumac.setPrezime(prezime);
        glumac.setBio(bio);
        glumac.setOcena(ratingBar.getRating());
        glumac.setDate(new Date());


        try {
            getMyhelper().getGlumacDao().create(glumac);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            id=getMyhelper().getGlumacDao().queryBuilder().where().eq(Glumac.IME_FIELD,ime)
                    .and().eq(Glumac.PREZIME_FIELD,prezime).queryForFirst().getId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public MyHelper getMyhelper() {
        if (myHelper == null) {
            myHelper = OpenHelperManager.getHelper(getActivity(), MyHelper.class);
        }
        return myHelper;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myHelper != null) {
            OpenHelperManager.releaseHelper();
            myHelper = null;
        }
    }
}