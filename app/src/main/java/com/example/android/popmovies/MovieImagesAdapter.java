package com.example.android.popmovies;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by toda on 2017/10/05.
 */

public class MovieImagesAdapter extends ArrayAdapter<HashMap<String, String>> {

    private Context mContext;
    private ArrayList<HashMap<String, String>> mGridData;
    private static final String TAG = MovieImagesAdapter.class.getSimpleName();

    public MovieImagesAdapter(Activity context, ArrayList<HashMap<String, String>> mGridData) {
        super(context, 0, mGridData);

        this.mContext = context;
        this.mGridData = mGridData;
    }

    /**
     * Updates grid data and refresh grid items.
     */
    public void setGridData(ArrayList<HashMap<String, String>> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        HashMap<String, String> currentData = mGridData.get(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        View view;
        ViewHolder viewHolder;
        viewHolder = new ViewHolder();
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.image_item, parent, false);
            viewHolder.iconView = (ImageView)view.findViewById(R.id.movie_image);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        //Set image
        Picasso.with(mContext).load(currentData.get("imageUrl"))
                .placeholder(R.mipmap.img_one)
                .error(R.mipmap.img_two)
                .into(viewHolder.iconView);

        return view;
    }

    class ViewHolder{
        ImageView iconView;
    }
}