package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Button;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.adapters.MovieAdapter;
import com.example.flixster.adapters.MovieAdapter2;
import com.example.flixster.models.Movie;
import com.facebook.stetho.common.ArrayListAccumulator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String NOW_PLAYING_URL = BuildConfig.NOW_PLAYING_URL;
    public static final String TAG = "MainActivity";
    List<Movie> movies;
    RecyclerView rvMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMovies = findViewById(R.id.rvMovies);
        movies = new ArrayList<>();


        //Create an adapter
        final MovieAdapter2 movieAdapter = new MovieAdapter2(this,movies);

        // set the adapter on the recycler view
        rvMovies.setAdapter(movieAdapter);

        //set a layout manager on the recycler view
        rvMovies.setLayoutManager(new LinearLayoutManager(this));


//        rvMovies.setBackgroundColor(Color.BLACK);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    //get the results array, and save it in results variable
                    JSONArray results = jsonObject.getJSONArray("results");
                    //send an info message
                    Log.i(TAG, results.toString());
                    movies.addAll(Movie.fromJsonArray(results));
                    //whenever the data changes, notify the adapter to update the RV
                    movieAdapter.notifyDataSetChanged();
                    Log.i(TAG, "Movies" + movies.size());
                } catch (JSONException e) {
                    Log.e(TAG, "Hit JSON exception");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });
    }

}


