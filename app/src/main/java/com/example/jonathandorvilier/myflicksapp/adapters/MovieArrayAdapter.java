package com.example.jonathandorvilier.myflicksapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonathandorvilier.myflicksapp.R;
import com.example.jonathandorvilier.myflicksapp.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Jonathan Dorvilier on 7/21/2017.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);

    }

    public View getView(int position, View convertView, ViewGroup parent){

        // get the data item for this position
        Movie movie = getItem(position);
        // check the existing view being reuses
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);


        }

            // find the image
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);

        //clear out image from convertView
        ivImage.setImageResource(0);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvOverView = (TextView) convertView.findViewById(R.id.tvOverView);
        // populate the data

        tvTitle.setText(movie.getOriginal_Title());
        tvOverView.setText(movie.getOverview());
        //return the View
        Picasso.with(getContext()).load(movie.getPoster_path()).into(ivImage);
        return  convertView;
    }
}

