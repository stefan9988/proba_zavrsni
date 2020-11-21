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
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.pripremni_zadatak.R;
import com.example.pripremni_zadatak.adapteri.FilmAdapter;
import com.example.pripremni_zadatak.model.Film;
import com.example.pripremni_zadatak.model.Glumac;
import com.example.pripremni_zadatak.model.MyHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.ForeignCollection;

import java.sql.SQLException;
import java.util.List;

public class OpisGlumcaFragment extends Fragment {
    private Glumac glumac;
    private MyHelper myHelper;
    private int id;
    private TextView tvIme;
    private TextView tvPrezime;
    private TextView tvBio;
    private TextView tvDatum;
    private RatingBar ratingBar;
    private RecyclerView recyclerView;
    private Button btnEdit;
    private Button btnDelate;
    private Button btnAdd;
    private FilmAdapter adapter;
    private ForeignCollection<Film>filmList;



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
        id=getArguments().getInt("Id");
        refresh();
        tvIme=view.findViewById(R.id.tvImeGlumca);
        tvPrezime=view.findViewById(R.id.tvPrezimemeGlumca);
        tvBio=view.findViewById(R.id.tvBioGlumca);
        tvDatum=view.findViewById(R.id.tvDatumGlumca);
        ratingBar=view.findViewById(R.id.rbopis);
        recyclerView=view.findViewById(R.id.rvOpis);
        btnEdit=view.findViewById(R.id.btnEdit);
        btnDelate=view.findViewById(R.id.btnDelate);
        btnAdd=view.findViewById(R.id.btnAdd);

        tvIme.setText(glumac.getIme());
        tvPrezime.setText(glumac.getPrezime());
        tvBio.setText(glumac.getBio());
        tvDatum.setText(glumac.getDate().toString());
        ratingBar.setRating(glumac.getOcena());

        filmList=glumac.getFilms();


        recyclerView=view.findViewById(R.id.rvOpis);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new FilmAdapter(filmList);
        recyclerView.setAdapter(adapter);

        btnDelate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getMyhelper().getGlumacDao().delete(glumac);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                MainFragment mainFragment=new MainFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,mainFragment)
                        .commit();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFilmFragment addFilmFragment = new AddFilmFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("Id",id);
                addFilmFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().addToBackStack(null)
                        .replace(R.id.fragment_container, addFilmFragment)
                        .commit();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditFragment editFragment=new EditFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("Id",id);
                editFragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .addToBackStack(null).replace(R.id.fragment_container,editFragment)
                        .commit();
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