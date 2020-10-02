package com.example.flixster.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcel;
import android.util.Log;
import androidx.core.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.flixster.LowerRatingViewActivity;
import com.example.flixster.MainActivity;
import com.example.flixster.MovieDetailsActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

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
        RelativeLayout container;
        private TextView tvTitle;
        private TextView tvOverview;
        private ImageView ivPoster;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.mvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);

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

        RelativeLayout container;
        private ImageView ivPoster;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.ivPoster2);
            container = itemView.findViewById(R.id.container);
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

    private void bindDefaultView(final ViewHolder1 holder, final Movie movie) {
        holder.getTvOverview().setText(movie.getOverview());
        holder.getTvTitle().setText(movie.getTitle());
        imageUrl = movie.getPosterPath();
        Glide.with(context).load(imageUrl).placeholder(R.drawable.portraitpreview200).fitCenter().transform(new RoundedCorners(30))
                .into(holder.getIvPoster());
        //register click listener on the whole item in the recyclerview
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, movie.getTitle(), Toast.LENGTH_SHORT).show();
                //2. navigate to the new activity by tapping on the row
                Intent i = new Intent(context, LowerRatingViewActivity.class);
                i.putExtra("movie", Parcels.wrap(movie));
                Pair<View, String> posterImageView = Pair.create((View)holder.getIvPoster(), "posterImage");
                Pair<View, String> titleImageView = Pair.create((View)holder.getTvTitle(), "movieTitle");
                Pair<View, String> overviewView = Pair.create((View)holder.getTvOverview(), "overviewBlock");
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)context, posterImageView, titleImageView, overviewView);

                context.startActivity(i, options.toBundle());

            }
        });
    }


    private void bindAlternativeView(final ViewHolder2 holder, final Movie movie) {
        String textOverviewInfo = movie.getOverview();
        if (textOverviewInfo.length() >150){
            textOverviewInfo = textOverviewInfo.substring(0,150) +"...";
        }

        imageUrl =movie.getBackdropPath();
        Glide.with(context).load(imageUrl).placeholder(R.drawable.backdrop600).fitCenter().transform(new RoundedCorners(30))
                .into(holder.getIvPoster());
        Log.d("MovieAdapter", imageUrl);
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, movie.getTitle(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, MovieDetailsActivity.class);
                i.putExtra("movie", Parcels.wrap(movie));
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)context, (View)holder.getIvPoster(), "posterImage");
                context.startActivity(i,options.toBundle());

            }
        });

    }

    private void bindLandScapeView(final ViewHolder1 holder, final Movie movie) {
        holder.getTvOverview().setText(movie.getFullOverView());
        holder.getTvTitle().setText(movie.getTitle());
        imageUrl =movie.getBackdropPath();
        Glide.with(context).load(imageUrl).placeholder(R.drawable.backdrop600).fitCenter().transform(new RoundedCorners(30))
                .into(holder.getIvPoster());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, movie.getTitle(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, MovieDetailsActivity.class);
                i.putExtra("movie", Parcels.wrap(movie));
                Pair<View, String> posterImageView = Pair.create((View)holder.getIvPoster(), "posterImage");
                Pair<View, String> titleImageView = Pair.create((View)holder.getTvTitle(), "movieTitle");
                Pair<View, String> overviewView = Pair.create((View)holder.getTvOverview(), "overviewBlock");
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)context, posterImageView, titleImageView, overviewView);

                context.startActivity(i, options.toBundle());
            }
        });
    }

    //return item count of the movies list
    @Override
    public int getItemCount() {
        return movies.size();
    }

}
