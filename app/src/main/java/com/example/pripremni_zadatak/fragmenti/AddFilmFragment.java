package com.example.pripremni_zadatak.fragmenti;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pripremni_zadatak.R;
import com.example.pripremni_zadatak.model.Film;
import com.example.pripremni_zadatak.model.Glumac;
import com.example.pripremni_zadatak.model.MyHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.ForeignCollection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AddFilmFragment extends Fragment {
    private EditText etIme;
    private EditText etZanr;
    private EditText etGodina;
    private Button btnGotov;
    private Button btnJos;
    private int id;
    private String ime;
    private String zanr;
    private MyHelper myHelper;
    private Glumac glumac;

    public AddFilmFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_film, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etIme=view.findViewById(R.id.etImeFilma);
        etZanr=view.findViewById(R.id.etZanr);
        btnGotov=view.findViewById(R.id.btngotovFilm);
        btnJos=view.findViewById(R.id.btnjosFilm);
        id=getArguments().getInt("id");

        btnGotov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ime = etIme.getText().toString();
                zanr = etZanr.getText().toString();
                try {
                    glumac=getMyhelper().getGlumacDao().queryForId(id);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                Film film=new Film();
                film.setIme(ime);
                film.setZanr(zanr);
                film.setGodina(new Date());


                try {
                    getMyhelper().getFilmDao().create(film);
                    ForeignCollection<Film>ffilm=getMyhelper().getFilmDao().queryBuilder().where()
                            .eq(Film.IME_FIELD,ime).and().eq(Film.ZANR_FIELD,zanr).queryForFirst();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                glumac.setFilms(film);
                try {
                    getMyhelper().getGlumacDao().update(glumac);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                MainFragment mainFragment=new MainFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,mainFragment);
            }
        });

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