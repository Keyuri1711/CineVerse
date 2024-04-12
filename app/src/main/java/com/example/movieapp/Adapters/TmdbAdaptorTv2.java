package com.example.movieapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Activities.DetailsTv;
import com.example.movieapp.Others.TmdbTvResponse;
import com.example.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TmdbAdaptorTv2 extends RecyclerView.Adapter<TmdbAdaptorTv2.GithubViewHolder> {

    private final Context context;
    private final ArrayList<TmdbTvResponse> arrayList;

    public TmdbAdaptorTv2(Context context, ArrayList<TmdbTvResponse> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public GithubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.posterlayout, parent, false);
        return new GithubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GithubViewHolder holder, int position) {
        TmdbTvResponse user = arrayList.get(position);
        holder.bind(user, position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class GithubViewHolder extends RecyclerView.ViewHolder {

        private TmdbTvResponse currentuser;

        ImageButton ib3filled, ib3blank;
        TextView titletv, ratingtv;
        ImageView img2;
        public GithubViewHolder(View itemView) {
            super(itemView);

            ib3filled= itemView.findViewById(R.id.ib3filled);
            ib3blank= itemView.findViewById(R.id.ib3blank);
            titletv= itemView.findViewById(R.id.titletv);
            ratingtv= itemView.findViewById(R.id.ratingtv);
            img2= itemView.findViewById(R.id.img2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detail = new Intent(context, DetailsTv.class);
                    detail.putExtra("ID", currentuser.getId());
                    context.startActivity(detail);
                }
            });
        }

        public void bind(TmdbTvResponse user, int position) {
            currentuser = user;
            titletv.setText(user.getName());
            ratingtv.setText("⭐ " + user.getVote_average() + "/10");
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.getPoster_path()).fit().centerCrop().into(img2);

            ib3blank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ib3filled.setVisibility(View.VISIBLE);
                    ib3blank.setVisibility(View.GONE);
                }
            });

            ib3filled.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ib3filled.setVisibility(View.GONE);
                    ib3blank.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}
