package com.example.android.popmovies;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivityFragment extends Fragment{

    private static final String TAG = MainActivityFragment.class.getSimpleName();

    GridView gridView;

    private MovieImagesAdapter imagesAdapter;

    private ArrayList<HashMap<String, String>> moviesInfo;

    public MainActivityFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.gridview, container, false);

        if (moviesInfo == null) {
            moviesInfo = new ArrayList<>();
            imagesAdapter = new MovieImagesAdapter(getActivity(), moviesInfo);
            new FetchMoviesTask(moviesInfo, imagesAdapter).execute(getString(R.string.sort_order_upcoming));
        }

        gridView = rootView.findViewById(R.id.image_grid);
        gridView.setAdapter(imagesAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                HashMap<String, String> currentData;
                currentData = moviesInfo.get(position);
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("currentMovieData", currentData);
                startActivity(intent);
            }
        });
        return rootView;
    }

    public void showPopularMovies(){
        new FetchMoviesTask(moviesInfo, imagesAdapter).execute("popular");
    }

    public void showTopRatingMovies(){
        new FetchMoviesTask(moviesInfo, imagesAdapter).execute("top_rated");
    }

    public void showUpComingMovies(){
        new FetchMoviesTask(moviesInfo, imagesAdapter).execute("upcoming");
    }
}