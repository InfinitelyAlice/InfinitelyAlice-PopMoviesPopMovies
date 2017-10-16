package com.example.android.popmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            Log.v(TAG, "sort_toprated item is tapped");
            new MainActivityFragment.FetchLoadingTask().execute(getString(R.string.sort_order_topRated));
            return true;
        }

        if (id == R.id.action_sort_popular) {
            Log.v(TAG, "sort_popular item is tapped");
            new MainActivityFragment.FetchLoadingTask().execute(getString(R.string.sort_order_popular));
            return true;
        }

        if (id == R.id.action_sort_upcoming) {
            Log.v(TAG, "upcoming item is tapped");
            new MainActivityFragment.FetchLoadingTask().execute(getString(R.string.sort_order_upcoming));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
