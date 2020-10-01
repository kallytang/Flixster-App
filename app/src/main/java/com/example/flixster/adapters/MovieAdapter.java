package com.example.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    //usually involves inflating a layout from XML and returning the holder;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);

    }

    //involves populating the data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder " + position);
        //Get the movie at the position passed
        Movie movie = movies.get(position);
        //bind the movie data to the viewholder
        holder.bind(movie);
    }

    //return item count of the movies list
    @Override
    public int getItemCount() {

        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.mvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);

        }
        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;
            //if phone is in landscape
            if(context.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE) {
                //then imageUrl = backdrop
                //backdrop ratio hxw = 1:1781
                imageUrl = movie.getBackdropPath();
                Glide.with(context).load(imageUrl).placeholder(R.drawable.backdrop600).into(ivPoster);
            }else {
                //else image is poster image
                // imdb portrait images are 1:0.675
                imageUrl = movie.getPosterPath();

                Glide.with(context).load(imageUrl).placeholder(R.drawable.portraitpreview200).into(ivPoster);
            }
            Log.d("MovieAdapter", imageUrl);

//            Glide.with(context).load(imageUrl).into(ivPoster);



        }
    }

}
