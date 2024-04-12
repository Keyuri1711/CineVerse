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

import com.example.movieapp.Activities.Details;
import com.example.movieapp.Others.Moviecast;
import com.example.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Moviecastadapter extends RecyclerView.Adapter<Moviecastadapter.GithubViewHolder> {

    private final Context context;
    private final ArrayList<Moviecast> arrayList;

    public Moviecastadapter(Context context, ArrayList<Moviecast> arrayList) {
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
        Moviecast user = arrayList.get(position);
        holder.bind(user, position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class GithubViewHolder extends RecyclerView.ViewHolder {

        private Moviecast currentuser;
        TextView titleTv, ratingTv;
        ImageButton ib3blank, ib3filled;
        ImageView img2;
        public GithubViewHolder(View itemView) {
            super(itemView);

            titleTv= itemView.findViewById(R.id.titletv);
            ratingTv= itemView.findViewById(R.id.ratingtv);
            ib3blank= itemView.findViewById(R.id.ib3blank);
            ib3filled= itemView.findViewById(R.id.ib3filled);
            img2= itemView.findViewById(R.id.img2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detail = new Intent(context, Details.class);
                    detail.putExtra("ID", currentuser.getId());
                    context.startActivity(detail);
                }
            });
        }

        public void bind(Moviecast user, int position) {
            currentuser = user;
            titleTv.setText(user.getTitle());
            ratingTv.setText("As " + user.getCharacter());
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.getPoster_path()).fit().centerCrop().into(img2);
            if (user.getPoster_path() == null) {
                Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1024px-No_image_available.svg.png").into(img2);
            }
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
