package com.example.flixster;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.flixster.models.Movie;

public class MovieInfoDialog extends AppCompatDialogFragment {
    ImageView posterImage;
    TextView movieTitle;
    TextView movieOverview;
    TextView releaseDate;
    RatingBar ratingBar;
    TextView ratingFraction;
    private MovieInfoDialog listener;

    @NonNull
    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.movie_info_dialogue, null);
        builder.setView(view).setTitle("MovieInfo");

        posterImage = view.findViewById(R.id.posterImage);
        movieTitle = view.findViewById(R.id.movieTitle);
        movieOverview = view.findViewById(R.id.movieOverview);
        releaseDate = view.findViewById(R.id.releaseDate);
        ratingBar = view.findViewById(R.id.ratingBar);
        ratingFraction = view.findViewById(R.id.ratingFraction);
        return builder.create();
    }


}
