package com.example.android.popmovies;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getSimpleName();
    @BindView(R.id.mv_display_title) TextView mMovieTitle;
    @BindView(R.id.mv_releaseDate) TextView mMovieReleaseDate;
    @BindView(R.id.mv_overview) TextView mMovieOverview;
    @BindView(R.id.mv_rating) TextView mRatingNumber;
    @BindView(R.id.mv_image) ImageView mMovieImage;
    @BindView(R.id.ratingBar) RatingBar mMovieRating;

    Context mContext;

    String mReleaseDate;
    String mImage;
    String mTitle;
    String mOverview;
    String mRating;
    HashMap<String, String> currentMovieData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        currentMovieData = (HashMap<String, String>) getIntent().getSerializableExtra("currentMovieData");
        Log.d(TAG, currentMovieData.get("original_title"));

        if (currentMovieData != null) {
            mTitle = currentMovieData.get("original_title");
            mMovieTitle.setText(mTitle);

            mReleaseDate = currentMovieData.get("release_date");
            mMovieReleaseDate.setText(mReleaseDate);

            mImage = currentMovieData.get("imageUrl");
            Picasso.with(mContext).load(mImage).into(mMovieImage);

            mOverview = currentMovieData.get("overview");
            mMovieOverview.setText(mOverview);

            mRating = currentMovieData.get("vote_average");
            mMovieRating.setNumStars(10);
            mMovieRating.setStepSize((float) 0.2);
            mMovieRating.setRating(Float.valueOf(mRating));
            mRatingNumber.setText(mRating);

        }

    }
}
