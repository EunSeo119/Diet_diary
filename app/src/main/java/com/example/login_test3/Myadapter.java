package com.example.login_test3;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Myadapter extends ArrayAdapter<Post> {
    public Myadapter(Context context, int resource){
        super(context, resource);
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_coustom, null);
            viewHolder = new ViewHolder();
            viewHolder.mImage = (ImageView) view.findViewById(R.id.iv_image);
            viewHolder.mTitle = (TextView) view.findViewById(R.id.tv_title);
            viewHolder.mDate = (TextView) view.findViewById(R.id.tv_date);
            viewHolder.mTime = (TextView) view.findViewById(R.id.tv_time);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        TextView date = (TextView) view.findViewById(R.id.tv_date);
        TextView time = (TextView) view.findViewById(R.id.tv_time);
        ImageView image = (ImageView) view.findViewById(R.id.iv_image);

        Post post = getItem(position);
        viewHolder.mTitle.setText(post.title);
        viewHolder.mDate.setText(post.date);
        viewHolder.mTime.setText(post.time);


        Glide.with(getContext()).load(post.imageUrl).into(image);


        return view;
    }

    private class ViewHolder {
        private ImageView mImage;
        private TextView mTitle;
        private TextView mDate;
        private TextView mTime;
        }

    }

