package com.example.pripremni_zadatak.fragmenti;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pripremni_zadatak.R;
import com.example.pripremni_zadatak.model.Glumac;
import com.example.pripremni_zadatak.model.MyHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;


public class EditFragment extends Fragment  {

    private Button btnGotovo;
    private MyHelper myHelper;
    private Glumac glumac;
    private int id;
    private EditText etIme;
    private EditText etPrezime;
    private EditText etBio;
    private RatingBar ratingBar;
    private String ime;
    private String prezime;
    private String bio;



    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id=getArguments().getInt("Id");
        refresh();
        btnGotovo=view.findViewById(R.id.btngotovEdit);
        etIme = view.findViewById(R.id.etEditImeGlumca);
        etPrezime = view.findViewById(R.id.etEditPrezimeGlumca);
        etBio = view.findViewById(R.id.etEditBioGlumca);
        ratingBar=view.findViewById(R.id.rbEdit);

        etIme.setText(glumac.getIme());
        etPrezime.setText(glumac.getPrezime());
        etBio.setText(glumac.getBio());
        ratingBar.setRating(glumac.getOcena());

        btnGotovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ime = etIme.getText().toString();
                prezime = etPrezime.getText().toString();
                bio = etBio.getText().toString();


                glumac.setIme(ime);
                glumac.setPrezime(prezime);
                glumac.setBio(bio);
                glumac.setOcena(ratingBar.getRating());

                try {
                    getMyhelper().getGlumacDao().update(glumac);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                MainFragment mainFragment=new MainFragment();
                getFragmentManager().beginTransaction()
            .replace(R.id.fragment_container,mainFragment).commit();
            }
        });



    }


    private void refresh() {
        try {
            glumac=new Glumac();
            glumac =getMyhelper().getGlumacDao().queryForId(id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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