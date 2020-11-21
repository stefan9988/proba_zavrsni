package com.example.pripremni_zadatak.adapteri;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pripremni_zadatak.R;
import com.example.pripremni_zadatak.model.Film;
import com.example.pripremni_zadatak.model.Glumac;
import com.j256.ormlite.dao.ForeignCollection;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.MyViewHolder>{
    private ForeignCollection<Film> data;
    private List<Film>filmList;


    public FilmAdapter( ForeignCollection<Film> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardfilm,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        for (Film film:data){
            filmList.add(film);
        }
        holder.tvIme.setText(filmList.get(position).getIme());
        holder.tvZanr.setText(filmList.get(position).getZanr());
        holder.tvGod.setText(filmList.get(position).getGodina().toString());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIme;
        private TextView tvZanr;
        private TextView tvGod;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIme=itemView.findViewById(R.id.tvcfime);
            tvZanr =itemView.findViewById(R.id.tvcfzanr);
            tvGod =itemView.findViewById(R.id.tvcfdate);


        }

    }

}

