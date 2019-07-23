package com.dilip.c6tv;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<Data> DataArrayList;
    LayoutInflater inflater;
    public MyCustomAdapter(Activity activity, ArrayList<Data> DataArrayList)
    {
        this.activity=activity;
        this.DataArrayList=DataArrayList;
    }
    @Override
    public Object getItem(int position) {
        return this.DataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater==null)
        {
            inflater=this.activity.getLayoutInflater();
        }
        if (convertView==null)
        {
            convertView=inflater.inflate(R.layout.youtube_list,null);
        }
        ImageView imageView=(ImageView)convertView.findViewById(R.id.igview);
        TextView textView=(TextView)convertView.findViewById(R.id.mytitle);
        LinearLayout linearLayout=(LinearLayout)convertView.findViewById(R.id.root);
        final Data data=(Data)this.DataArrayList.get(position);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent v=new Intent(activity,Videos.class);
                v.putExtra("videoId",data.getVideoId());
                activity.startActivity(v);

            }
        });
        Picasso.get().load(data.getUrl()).into(imageView);
        textView.setText(data.getTitle());
        return convertView;
    }

    @Override
    public int getCount() {
        return this.DataArrayList.size();
    }
}
