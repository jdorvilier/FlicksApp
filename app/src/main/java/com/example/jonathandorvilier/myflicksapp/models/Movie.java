package com.example.jonathandorvilier.myflicksapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.attr.rating;

/**
 * Created by Jonathan Dorvilier on 7/21/2017.
 */

public class Movie {

    String poster_path;
    String original_title;
    String overview;
    int id;
    String release_date;
    String backdrop_path;
    double vote_average;

    public String getPoster_path() {
        return String.format("https://image.tmdb.org/t/p/w342%s", poster_path);
    }

    public String getOriginal_Title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }
    public int getId() {
        return id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getBackdrop_path() {
        return String.format("https://image.tmdb.org/t/p/w780%s", backdrop_path);//300?
    }

    public double getVote_average() {
        //the API returns a x out of 10 rating, so we convert it to the equivalent out of 5
        return rating*0.5;
    }





    public Movie(JSONObject jsonObject) throws JSONException{
        this.id = jsonObject.getInt("id");
        this.poster_path=jsonObject.getString("poster_path");
        this.original_title=jsonObject.getString("original_title");
        this.overview=jsonObject.getString("overview");
        this.backdrop_path=jsonObject.getString("backdrop_path");
        this.release_date =jsonObject.getString("release_date");
        this.vote_average=jsonObject.getDouble("vote_average");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array){
        ArrayList<Movie> result = new ArrayList<>();

        for (int x = 0; x <array.length(); x++){
            try {
               result.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

   }
