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

    public static final String BACK_DROP_PATH = "back_drop_path";
    public static final String POSTER_PATH = "poster_path";
    public static final String MOVIE_TITLE = "movie_title";
    public static final String MOVIE_RATING = "movie_rating";
    public static final String RELEASE_DATE = "release_date";
    public static final String GET_OVERVIEW = "full_overview";


    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
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


        rvMovies.setBackgroundColor(Color.BLACK);

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

    //for unit 2

//    public void openItem(int position) {
//        Movie movieItem = movies.get(position);
//        Log.d("PopUp", "Popup");
//        Intent intent = new Intent(this, MovieInfoDialog.class);
//
//        startActivity(intent);
//
//        String backdropPath = movieItem.getBackdropPath();
//        String posterPath = movieItem.getPosterPath();
//        String title = movieItem.getTitle();
//        String overview = movieItem.getFullOverView();
//        String rating = movieItem.getRating().toString();
//        String releaseDate = movieItem.getReleaseDate();
//    }
}

//todo: https://www.geeksforgeeks.org/how-to-implement-dark-night-mode-in-android-app/
// todo create app pop up info
//todo alternative viewholder

