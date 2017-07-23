package com.example.jonathandorvilier.myflicksapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jonathan Dorvilier on 7/23/2017.
 */

public class Trailer{

    private String key;
    private String type;

    //constructors
    public Trailer(){
        super();
    }

    public Trailer(JSONObject jsonObject) throws JSONException {
        key   = jsonObject.getString("key");
        type = jsonObject.getString("type");
    }

    public String getKey() {
        return key;
    }

    public String getType() {
        return type;
    }

    //convert Json object into ArrayList
    public static ArrayList<Trailer> fromJSONArray(JSONArray array){
        ArrayList<Trailer> results = new ArrayList<>();

        for(int i=0; i<array.length(); i++){
            try {
                results.add(new Trailer(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
