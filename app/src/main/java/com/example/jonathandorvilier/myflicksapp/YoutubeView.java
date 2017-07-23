package com.example.jonathandorvilier.myflicksapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathandorvilier.myflicksapp.Utils.utils;
import com.example.jonathandorvilier.myflicksapp.models.Movie;
import com.example.jonathandorvilier.myflicksapp.models.Trailer;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Jonathan Dorvilier on 7/23/2017.
 */

public class YoutubeView extends YouTubeBaseActivity {
    private ArrayList<Trailer> trailers;
    private Movie movie;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.youtube_player_view);

        //retrieve movie that's been 'sent' from main activity
        movie = (Movie) getIntent().getSerializableExtra("movie");

        //retrieve ID of movie
        int movieID = movie.getId();

        trailers = new ArrayList<>();

        String url = utils.getBaseURL() + Integer.toString(movieID) + "/videos?api_key=" + utils.getMovieDBAPIkey();

        fetchMovieVideos(url);

    }


    private void fetchMovieVideos(String url) {
        //make sure there's access to the web
        boolean connectivity = utils.checkForConnectivity(this);

        if (!connectivity) {
            Toast.makeText(this, "Unable to continue, no connection detected", Toast.LENGTH_LONG).show();
        } else {
            utils.getClient().get(url, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    JSONArray trailersJsonResults = null;

                    try {
                        trailersJsonResults = response.getJSONArray("results");
                        trailers.addAll(Trailer.fromJSONArray(trailersJsonResults));
                        setUpLayout();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                }
            });
        }
    }

    private void setUpLayout() {

        TextView tvTitle = ButterKnife.findById(this, R.id.tv_title);
        tvTitle.setText(movie.getOriginal_Title());

        setUpVideoPlayer();
    }

    private void setUpVideoPlayer() {
        //trailers contains now all the trailers.
        //Let's randomly select the first one that is type 'Trailer'
        String selected = null;
        Trailer trailer;
        for (int i = 0; i < trailers.size() && selected == null; i++) {
            trailer = trailers.get(i);
            if (trailer.getType().equals("Trailer")) {
                selected = trailer.getKey();
            }
        }

        final String trailerID = selected;
        if (trailerID != null) {

            YouTubePlayerView youTubePlayerView =
                    (YouTubePlayerView) findViewById(R.id.youtube_player);

            youTubePlayerView.initialize(utils.getYouTubeAPIkey(),
                    new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                            YouTubePlayer youTubePlayer, boolean b) {
                            // do any work here to cue video, play video, etc.
                            youTubePlayer.setFullscreen(true);
                            youTubePlayer.loadVideo(trailerID);
                            // or to not play immediately call cueVideo instead
                        }

                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                            YouTubeInitializationResult youTubeInitializationResult) {

                        }
                    });
        }

    }
}
