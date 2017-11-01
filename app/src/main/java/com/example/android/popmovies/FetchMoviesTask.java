package com.example.android.popmovies;

import android.os.AsyncTask;
import android.util.Log;

import com.example.android.popmovies.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ME on 2017/11/1.
 */

public class FetchMoviesTask extends AsyncTask<String, Void, ArrayList<HashMap<String, String>>> {
    private static final String TAG = "FetchMoviesTask";
    private String API_KEY = "3ac64d01d1011b15e9f200670abe0b0d";
    private ArrayList<HashMap<String, String>> moviesInfo;
    private MovieImagesAdapter imagesAdapter;
    //private MoviesTaskCompleteListener<ArrayList<HashMap<String, String>>> listener;

    public FetchMoviesTask(ArrayList<HashMap<String, String>> moviesInfo, MovieImagesAdapter imagesAdapter) {
        this.moviesInfo = moviesInfo;
        this.imagesAdapter = imagesAdapter;
        //this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<HashMap<String, String>> doInBackground(String... params) {

        if (params.length == 0) {
            return null;
        }

        String sortOrder = params[0];

        URL requestUrl = NetworkUtils.buildUrl(sortOrder, API_KEY);

        if (moviesInfo != null) {        //TODO 先清空数据，再获取。
            moviesInfo.clear();
        }

        try {
            String jsonResponse = NetworkUtils
                    .getResponseFromHttpUrl(requestUrl);

            Log.v(TAG, "jsonResponse: " + jsonResponse);

            JSONObject getMovieInfo = new JSONObject(jsonResponse);//// // TODO: 2017/10/20 看是中括号还是大括号，圆的用object
            JSONArray resultInfo = getMovieInfo.getJSONArray("results");
            Log.v(TAG, "JSON Result: " + resultInfo.toString());

            for (int i = 0; i < resultInfo.length(); i++) {

                JSONObject r = resultInfo.getJSONObject(i);

                String id = r.getString("id");
                String original_title = r.getString("original_title");
                String vote_average = r.getString("vote_average");
                String overview = r.getString("overview");
                String poster_path = r.getString("poster_path");
                String imageUrl = "http://image.tmdb.org/t/p/w185/" + poster_path;
                String release_date = r.getString("release_date");//// TODO: 2017/10/20 一律用getString

                HashMap<String, String> movieDetailInfo = new HashMap<>();
                movieDetailInfo.put("id", id);
                movieDetailInfo.put("original_title", original_title);
                movieDetailInfo.put("vote_average", vote_average);
                movieDetailInfo.put("overview", overview);
                movieDetailInfo.put("release_date", release_date);
                movieDetailInfo.put("imageUrl", imageUrl);
                moviesInfo.add(movieDetailInfo);

            }
            return moviesInfo;
        } catch (Exception e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<HashMap<String, String>> movieDetailInfo) {
        super.onPostExecute(movieDetailInfo);
        if (movieDetailInfo != null) {
            imagesAdapter.setGridData(movieDetailInfo);
            //// TODO: 2017/10/20 对imgesAdapter中Array<HashMap<String, String>> 类型的成员变量赋值
        }
    }
}

