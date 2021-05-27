package com.example.login_test3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Writingadapter extends ArrayAdapter<PostUser> {
    public Writingadapter(Context context, int resource){
        super(context, resource);
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(view==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_coustom, null);
            viewHolder = new Writingadapter.ViewHolder();
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

        PostUser postuser = getItem(position);
        viewHolder.mTitle.setText(postuser.title);
        viewHolder.mDate.setText(postuser.date);
        viewHolder.mTime.setText(postuser.time);

        return view;
    }


    private class ViewHolder {
        private ImageView mImage;
        private TextView mTitle;
        private TextView mDate;
        private TextView mTime;
    }
}
