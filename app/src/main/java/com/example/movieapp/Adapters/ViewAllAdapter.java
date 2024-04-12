package com.example.movieapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Activities.Details;
import com.example.movieapp.Others.TmdbResponse;
import com.example.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.GithubViewHolder> {

    private final Context context;
    private final ArrayList<TmdbResponse> arrayList;

    public ViewAllAdapter(Context context, ArrayList<TmdbResponse> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public GithubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_all_layout, parent, false);
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

    class GithubViewHolder extends RecyclerView.ViewHolder {

        TmdbResponse currentuser;
        TextView name, rating;
        ImageView imgViewAll;

        public GithubViewHolder(View itemView) {
            super(itemView);

            name= itemView.findViewById(R.id.name);
            rating= itemView.findViewById(R.id.rating);
            imgViewAll= itemView.findViewById(R.id.imgViewAll);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detail = new Intent(context, Details.class);
                    detail.putExtra("ID", currentuser.getId());
                    context.startActivity(detail);
                }
            });
        }

        public void bind(TmdbResponse user, int position) {
            currentuser = user;
            name.setText(user.getTitle());
            rating.setText("⭐ " + user.getVote_average() + "/10");
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.getPoster_path())
                    .fit().centerCrop()
                    .into(imgViewAll);
        }
    }
}

