package com.example.movieapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Others.Trailers;
import com.example.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter2 extends RecyclerView.Adapter<GithubViewHolder> {
    private final Context context;
    private final ArrayList<Trailers> arrayList;

    public MovieAdapter2(Context context, ArrayList<Trailers> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public GithubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.trailers, parent, false);
        return new GithubViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(GithubViewHolder holder, int position) {
        Trailers user = arrayList.get(position);
        holder.bind(user, position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

class GithubViewHolder extends RecyclerView.ViewHolder {

    ImageView bt;
    TextView tv;
    private final Context context;
    public GithubViewHolder(View itemView, Context context) {
        super(itemView);

        bt= itemView.findViewById(R.id.bt);
        tv= itemView.findViewById(R.id.tv);
        this.context = context;
    }

    public void bind(final Trailers user, final int position) {

        tv.setText(user.getName());

        Picasso.get().load("https://img.youtube.com/vi/" + user.getKey() + "/maxresdefault.jpg").into(bt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = null;
                //Intent j = new Intent(context, IntroActivity.class);
                j.putExtra("path", user.getKey());
                context.startActivity(j);
            }
        });
    }
}
