package com.example.movieapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Activities.CastDetails;
import com.example.movieapp.Others.MovieDetails;
import com.example.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.GithubViewHolder> {

    private final Context context;
    private final ArrayList<MovieDetails> arrayList;

    public MovieAdapter(Context context, ArrayList<MovieDetails> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public GithubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.profilelayout, parent, false);
        return new GithubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GithubViewHolder holder, int position) {
        MovieDetails user = arrayList.get(position);
        holder.bind(user, position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class GithubViewHolder extends RecyclerView.ViewHolder {
        MovieDetails currentuser;
        TextView charactertv, nametv;
        ImageView img;

        public GithubViewHolder(View itemView) {
            super(itemView);

            charactertv= itemView.findViewById(R.id.charactertv);
            nametv= itemView.findViewById(R.id.nametv);
            img= itemView.findViewById(R.id.img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent l = new Intent(context, CastDetails.class);
                    l.putExtra("castID", currentuser.getId());
                    context.startActivity(l);
                }
            });
        }

        public void bind(MovieDetails user, int position) {
            currentuser = user;
            charactertv.setText(user.getCharacter());
            nametv.setText(user.getName());
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.getProfile_path())
                    .fit().centerCrop().into(img);
            if (user.getProfile_path() == null) {
                Picasso.get().load(
                        "https://wingslax.com/wp-content/uploads/2017/12/no-image-available.png"
                ).into(img);
            }
        }
    }
}

