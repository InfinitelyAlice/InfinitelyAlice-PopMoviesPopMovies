package com.example.android.popmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MainActivityFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new MainActivityFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_sort_toprated) {
            Log.v(TAG, "sort_toprated item is selected");
            fragment.showTopRatingMovies();
            return true;
        }

        if (id == R.id.action_sort_popular) {
            Log.v(TAG, "sort_popular item is selected");
            fragment.showPopularMovies();
            return true;
        }

        if (id == R.id.action_sort_upcoming) {
            Log.v(TAG, "upcoming item is selected");
            fragment.showUpComingMovies();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
