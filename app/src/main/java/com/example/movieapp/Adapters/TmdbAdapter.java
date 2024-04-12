package com.example.movieapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Activities.Details;
import com.example.movieapp.Others.TmdbResponse;
import com.example.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TmdbAdapter extends RecyclerView.Adapter<TmdbAdapter.GithubViewHolder> {

    private final Context context;
    private final ArrayList<TmdbResponse> arrayList;

    public TmdbAdapter(Context context, ArrayList<TmdbResponse> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public GithubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.backdroplayout, parent, false);
        return new GithubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GithubViewHolder holder, int position) {
        TmdbResponse user = arrayList.get(position);
        holder.bind(user, position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class GithubViewHolder extends RecyclerView.ViewHolder {

        private TmdbResponse currentuser;
        private int currentposition;
        ImageButton ib1filled, ib1blank;
        TextView titletv, ratingtv;
        ImageView img;

        public GithubViewHolder(View itemView) {
            super(itemView);

            ib1filled= itemView.findViewById(R.id.ib1filled);
            ib1blank= itemView.findViewById(R.id.ib1blank);
            titletv= itemView.findViewById(R.id.titletv);
            ratingtv= itemView.findViewById(R.id.ratingtv);
            img= itemView.findViewById(R.id.img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detail = new Intent(context, Details.class);
                    detail.putExtra("ID", currentuser.getId());
                    context.startActivity(detail);
                }
            });
        }

        @SuppressLint({"SimpleDateFormat", "SetTextI18n"})
        public void bind(TmdbResponse user, int position) {
            currentuser = user;
            currentposition = position;
            titletv.setText(user.getTitle());
            ratingtv.setText("⭐ " + user.getVote_average() + "/10");
            Picasso.get().load("https://image.tmdb.org/t/p/original" + user.getBackdrop_path()).fit().centerCrop().into(img);

            if (user.getBackdrop_path() == null) {
                Picasso.get().load("https://fasterthemes.com/demo/foodrecipespro-wordpress-theme/wp-content/themes/foodrecipespro/images/no-image.jpg").fit().centerCrop().into(img);
            }

            ib1blank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ib1filled.setVisibility(View.VISIBLE);
                    ib1blank.setVisibility(View.GONE);
                }
            });

            ib1filled.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ib1filled.setVisibility(View.GONE);
                    ib1blank.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}
