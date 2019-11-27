package com.udacity.popularmoviesstage1app.tasks;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;

import com.udacity.popularmoviesstage1app.models.Movie;
import com.udacity.popularmoviesstage1app.utils.QueryUtils;

import java.util.List;

public class MovieLoader extends AsyncTaskLoader<List<Movie>> {

    /** Tag for log messages */
    private static final String LOG_TAG = MovieLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public MovieLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {
        if(TextUtils.isEmpty(mUrl)){
            return null;
        }

        // Perform the network request, parse the response, and extract a list of movies.
        List<Movie> movies = QueryUtils.fetchMoviesData(mUrl);
        return movies;
    }
}
