package com.sign.language;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Amol Jagtap on 3/15/2018.
 */
public class ParticipantsAdapter extends BaseAdapter {
    int[] myList;
    LayoutInflater inflater;
    Context context;


    public ParticipantsAdapter(Context context, int[] myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return myList.length;
    }

    @Override
    public Object getItem(int i) {
        return myList[i];
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_list_item, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        //  ListData currentListData = getItem(position);

            mViewHolder.imageView.setImageResource(myList[position]);

        return convertView;
    }

    private class MyViewHolder {

        ImageView imageView;

        public MyViewHolder(View item) {

            imageView = (ImageView) item.findViewById(R.id.imageView);

        }
    }

}