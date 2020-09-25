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

public class MovieAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Movie> movies;
    private final int PORTRAIT = 0, BACKDROP = 1, LANDSCAPE=3;
    String imageUrl;


    public MovieAdapter2(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    //viewholder1
    class ViewHolder1 extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private TextView tvOverview;
        private ImageView ivPoster;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);

        }
        public TextView getTvTitle() {
            return tvTitle;
        }

        public void setTvTitle(TextView tvTitle) {
            this.tvTitle = tvTitle;
        }

        public TextView getTvOverview() {
            return tvOverview;
        }

        public void setTvOverview(TextView tvOverview) {
            this.tvOverview = tvOverview;
        }

        public ImageView getIvPoster() {
            return ivPoster;
        }

        public void setIvPoster(ImageView ivPoster) {
            this.ivPoster = ivPoster;
        }
    }

    //viewholder2
    class ViewHolder2 extends RecyclerView.ViewHolder{
//        private TextView tvTitle;
//        private TextView tvOverview;
        private ImageView ivPoster;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
//            tvTitle = itemView.findViewById(R.id.tvTitle2);
//            tvOverview = itemView.findViewById(R.id.tvOverview2);
            ivPoster = itemView.findViewById(R.id.ivPoster2);
        }

        public ImageView getIvPoster() {
            return ivPoster;
        }

        public void setIvPoster(ImageView ivPoster) {
            this.ivPoster = ivPoster;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(movies.get(position).getRating()>=7){
            return BACKDROP;
        }else{
            return PORTRAIT;
        }
    }

    //usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(context);
        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            if(viewType==PORTRAIT){
                View regularView = inflater.inflate(R.layout.item_movie, parent, false);
                viewHolder = new ViewHolder1(regularView);
            }else{
                View backdropView = inflater.inflate(R.layout.alt_item_movie, parent, false);
                viewHolder = new ViewHolder2(backdropView);
            }
        }else{
            View defaultLandscape = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder = new ViewHolder1(defaultLandscape);
        }
        return viewHolder;
    }

    //populate the data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (holder.getItemViewType() == PORTRAIT) {
               ViewHolder1 vh1 = (ViewHolder1) holder;
               bindDefaultView(vh1, movie);
            }else{
                ViewHolder2 vh2 = (ViewHolder2) holder;
                bindAlternativeView(vh2, movie);
            }
        }else{
            ViewHolder1 vh3 = (ViewHolder1)holder;
            bindLandScapeView(vh3, movie);
        }
    }

    private void bindDefaultView(ViewHolder1 holder, Movie movie) {
        holder.getTvOverview().setText(movie.getFullOverView());
        holder.getTvTitle().setText(movie.getTitle());
        imageUrl = movie.getPosterPath();
        Glide.with(context).load(imageUrl).placeholder(R.drawable.portraitpreview200)
                .into(holder.getIvPoster());
    }


    private void bindAlternativeView(ViewHolder2 holder, Movie movie) {
        String textOverviewInfo = movie.getFullOverView();
        if (textOverviewInfo.length() >150){
            textOverviewInfo = textOverviewInfo.substring(0,150) +"...";
        }

        imageUrl =movie.getBackdropPath();
        Glide.with(context).load(imageUrl).placeholder(R.drawable.backdrop600)
                .into(holder.getIvPoster());
        Log.d("MovieAdapter", imageUrl);

    }

    private void bindLandScapeView(ViewHolder1 holder, Movie movie) {
        holder.getTvOverview().setText(movie.getFullOverView());
        holder.getTvTitle().setText(movie.getTitle());
        imageUrl =movie.getBackdropPath();
        Glide.with(context).load(imageUrl).placeholder(R.drawable.backdrop600)
                .into(holder.getIvPoster());
    }

    //return item count of the movies list
    @Override
    public int getItemCount() {
        return movies.size();
    }

}
