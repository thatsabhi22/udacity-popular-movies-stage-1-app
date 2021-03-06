package com.udacity.popularmoviesstage1app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmoviesstage1app.R;
import com.udacity.popularmoviesstage1app.models.MovieList;
import com.udacity.popularmoviesstage1app.ui.DetailActivity;

import java.util.Collections;
import java.util.List;

public class MoviesGridAdapter extends RecyclerView.Adapter<MoviesGridAdapter.MoviesViewHolder> {

    private final LayoutInflater inflater;
    private final Context mContext;
    private List<MovieList> movies = Collections.emptyList();

    public MoviesGridAdapter(Context mContext, List<MovieList> movies) {
        inflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MoviesGridAdapter.MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) inflater.inflate(R.layout.single_movie_tile, parent, false);
        MoviesViewHolder viewHolder = new MoviesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesGridAdapter.MoviesViewHolder holder, int position) {
        final MovieList current = movies.get(position);

        Picasso.get()
                .load(current.posterPath)
                .fit()
                .error(R.drawable.ic_videocam_black_48dp)
                .placeholder(R.drawable.ic_videocam_black_48dp)
                .into((ImageView) holder.singleMovieImageView.findViewById(R.id.movie_poster_imageview));

        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("movie", current);
                        mContext.startActivity(intent);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public void setData(List<MovieList> data) {
        this.movies = data;
        notifyDataSetChanged();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {

        ImageView singleMovieImageView;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);

            singleMovieImageView = itemView.findViewById(R.id.movie_poster_imageview);
        }
    }
}
