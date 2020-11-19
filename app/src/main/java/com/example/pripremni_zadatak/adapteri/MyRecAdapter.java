package com.example.pripremni_zadatak.adapteri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pripremni_zadatak.R;
import com.example.pripremni_zadatak.model.Glumac;

import java.util.List;

public class MyRecAdapter extends RecyclerView.Adapter<MyRecAdapter.MyViewHolder>{
    private List<Glumac> data;
    private MyOnClickListener listener;

    public MyRecAdapter(MyOnClickListener listener, List<Glumac> data) {
        this.data = data;
        this.listener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(listener,data.get(position).getId(),position,data);
//        holder.tvIme.setText(data.get(position).getIme());
//        holder.tvPrezime.setText(data.get(position).getPrezime());
//        holder.ratingBar.setRating((float) data.get(position).getOcena());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIme;
        private TextView tvPrezime;
        private RatingBar ratingBar;
        private  View wholeView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIme=itemView.findViewById(R.id.tvCardIme);
            tvPrezime=itemView.findViewById(R.id.tvCardPrezime);
            ratingBar=itemView.findViewById(R.id.ratingbar);
            wholeView=itemView;
        }
        public void bind(MyOnClickListener listener, int id,int pos,List<Glumac> data){
            tvIme.setText(data.get(pos).getIme());
            tvPrezime.setText(data.get(pos).getPrezime());
            ratingBar.setRating((float) data.get(pos).getOcena());
            wholeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.MyOnClick(id);
                }
            });
        }
    }
    public interface MyOnClickListener {
        void MyOnClick(int id);
    }
}



