package com.example.movieapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class TvAdaptor2 extends RecyclerView.Adapter<GithubViewHolder2> {

    private final Context context;
    private final ArrayList<Trailers> arrayList;

    public TvAdaptor2(Context context, ArrayList<Trailers> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public GithubViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.trailers, parent, false);
        return new GithubViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(GithubViewHolder2 holder, int position) {
        Trailers user = arrayList.get(position);
        holder.bind(user, position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

class GithubViewHolder2 extends RecyclerView.ViewHolder {

    ImageView bt;
    TextView tv;

    public GithubViewHolder2(View itemView) {
        super(itemView);

        bt= itemView.findViewById(R.id.bt);
        tv= itemView.findViewById(R.id.tv);
    }

    public void bind(Trailers user, int position) {
        tv.setText(user.getName());
        Picasso.get().load("https://img.youtube.com/vi/" + user.getKey() + "/maxresdefault.jpg").fit().centerCrop().into(bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + user.getKey()));
                itemView.getContext().startActivity(j);
            }
        });
    }
}

