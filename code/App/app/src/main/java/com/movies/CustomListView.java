package com.movies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomListView extends ArrayAdapter<String> {
    private String[] movie_studio;
    private String[] movie_category;
    private String[] movie_name;
    private String[] movie_length;
    private Activity context;

    public CustomListView(Activity context,String[] movie_studio, String[] movie_category, String[] movie_name, String[] movie_length){
        super(context, R.layout.layout,movie_studio);
        this.context = context;
        this.movie_studio = movie_studio;
        this.movie_category = movie_category;
        this.movie_name = movie_name;
        this.movie_length = movie_length;
    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r= convertView;
        ViewHolder viewHolder =  null;
        if(r==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.layout,null,true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) r.getTag();
        }
        viewHolder.tvw1.setText(movie_studio[position]);
        viewHolder.tvw2.setText(movie_category[position]);
        viewHolder.tvw3.setText(movie_name[position]);
        viewHolder.tvw4.setText(movie_length[position]);

        return r;
    }

    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;
        TextView tvw4;

        ViewHolder(View v){
            tvw1=(TextView)v.findViewById(R.id.movie_studio);
            tvw2=(TextView)v.findViewById(R.id.studio_category);
            tvw3=(TextView)v.findViewById(R.id.movie_name);
            tvw4=(TextView)v.findViewById(R.id.movie_length);
        }
    }
}
