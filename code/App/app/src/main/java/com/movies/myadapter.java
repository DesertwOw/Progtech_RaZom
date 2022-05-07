package com.movies;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder>
{
    List<responseModel> data;

    public myadapter(List<responseModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
            holder.t1.setText(data.get(position).movie_studio);
            holder.t2.setText(data.get(position).movie_category);
            holder.t3.setText(data.get(position).movie_name);
            holder.t4.setText(data.get(position).movie_lenght);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView t1,t2,t3,t4;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            t1=itemView.findViewById(R.id.studio_name);
            t2=itemView.findViewById(R.id.movie_category);
            t3=itemView.findViewById(R.id.movie_name);
            t4=itemView.findViewById(R.id.movie_length);
        }
    }
}
