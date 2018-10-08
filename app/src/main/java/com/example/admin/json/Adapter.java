package com.example.admin.json;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class Adapter extends BaseAdapter {
    Context context;
    List<JobListItems> gsList;
    ImageView imageView;


    public Adapter(Context context,List<JobListItems> gsList){
        this.context=context;
        this.gsList=gsList;
    }
    @Override
    public int getCount() {
        return gsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View cview = LayoutInflater.from(context).inflate(R.layout.texts, viewGroup, false);

        TextView title = cview.findViewById(R.id.title);
        title.setText(gsList.get(i).getTitle());

        TextView id=cview.findViewById(R.id.id);
        id.setText(gsList.get(i).getId());

        TextView des=cview.findViewById(R.id.des);
        des.setText(gsList.get(i).getDes());


        ImageView imageView =cview.findViewById(R.id.image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Main2Activity.class);
                intent.putExtra("video",gsList.get(i).getUrl());
                    intent.putExtra("des", gsList.get(i).getDes());
                    intent.putExtra("tit", gsList.get(i).getTitle());
                    intent.putExtra("gsList", (Serializable) gsList);
                    context.startActivity(intent);
                }


        });
        String s=gsList.get(i).getImage();
        Picasso.with(context).load(s).into(imageView);
        view=cview;

        return view;



    }
}
