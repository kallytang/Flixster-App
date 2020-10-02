package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class LowerRatingViewActivity extends YouTubeBaseActivity {
    private static final String YOUTUBE_API_KEY = BuildConfig.YOUTUBE_API_KEY;
    public static final String VIDEOS_URL = BuildConfig.VIDEOS_URL;
    ImageView mvPosterImage;
    TextView mvTitle;
    RatingBar ratingBar;
    TextView mvOverview;
    TextView releaseDate;
    ImageView playIcon;
    String imgUrl;
    YouTubePlayerView youTubePlayerView;
    RelativeLayout relLayoutFrame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_low_rating_view);

        //finding views
        mvTitle = findViewById(R.id.mvTitle);
        ratingBar = findViewById(R.id.ratingBar);
        mvOverview = findViewById(R.id.mvOverview);
        releaseDate = findViewById(R.id.releaseDate);
        mvPosterImage = findViewById(R.id.mvPosterImage);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);
        relLayoutFrame = findViewById(R.id.imagePreviewBox);
        playIcon = findViewById(R.id.playButtonImg);


        //putting data into the views
        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        mvTitle.setText(movie.getTitle());
        mvOverview.setText(movie.getFullOverView());
        ratingBar.setRating(Float.valueOf(movie.getRating().floatValue()));
        releaseDate.setText("Release Date: "+ movie.getReleaseDate());

        mvPosterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LowerRating", "clicked");
                playIcon.setVisibility(playIcon.GONE);
                mvPosterImage.setVisibility(mvPosterImage.GONE);

            }
        });

        imgUrl = movie.getBackdropPath();
        Glide.with(this).load(imgUrl).placeholder(R.drawable.backdrop600).fitCenter().transform(new RoundedCorners(30))
                .into(mvPosterImage);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(VIDEOS_URL, movie.getMovieId()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                try {
                    JSONArray results = json.jsonObject.getJSONArray("results");
                    if (results.length() ==0){
                        return;
                    }
                    String youtubeKey = results.getJSONObject(0).getString("key");
                    Log.d("DetailActivity", youtubeKey);
                    initializeYoutube(youtubeKey);
                } catch (JSONException e) {
                    Log.e("DetailActivity", "failed to parse JSON data");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });

    }
    private void initializeYoutube(final String youtubeKey) {
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("DetailActivity", "onInitializationSuccess");
                youTubePlayer.loadVideo(youtubeKey);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("DetailActivity", "onInitializeFailure");
            }
        });
    }

}