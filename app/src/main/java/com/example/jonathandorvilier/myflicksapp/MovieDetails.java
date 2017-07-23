package com.example.jonathandorvilier.myflicksapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.jonathandorvilier.myflicksapp.models.Movie;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

import static com.example.jonathandorvilier.myflicksapp.R.id.ivMovieImage;

/**
 * Created by Jonathan Dorvilier on 7/23/2017.
 */

public class MovieDetails extends AppCompatActivity{

    TextView title;
    TextView release_date;
    RatingBar vote_average;
    TextView ivImage;

  protected  void  OnCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.movie_details);

      final Movie movie = (Movie) getIntent().getSerializableExtra("movie");

      title = ButterKnife.findById(this, R.id.tvTitle);
      title.setText(movie.getOriginal_Title());

      release_date = ButterKnife.findById(this, R.id.release_date);
      release_date.setText("Release date:" +movie.getRelease_date());

      vote_average =ButterKnife.findById(this, R.id.rating_bar);
      vote_average.setRating((float) movie.getVote_average());

      ivImage = ButterKnife.findById(this,ivMovieImage);

      ivImage.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = null;

              //launch video activity
              // intent = new Intent(MovieDetails.this, YouTubeActivity.class);

              if (intent != null) {
                  // put movie as "extra" into the bundle for access in YouTubeActivity
                 //intent.putExtra("movie", movie); //Need to review after youtube
                  startActivity(intent);
              }

          }
      });

      Picasso.with(this).load(movie.getBackdrop_path())
              .transform(new RoundedCornersTransformation(20, 20))
              .placeholder(R.drawable.placeholder)
              .into((Target) ivImage);


  }

}
