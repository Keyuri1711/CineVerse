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
import com.example.movieapp.Others.Searchdetails;
import com.example.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.GithubViewHolder> {

    private final Context context;
    private final ArrayList<Searchdetails> arrayList;

    public SearchAdapter(Context context, ArrayList<Searchdetails> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public GithubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.searchlayout, parent, false);
        return new GithubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GithubViewHolder holder, int position) {
        Searchdetails user = arrayList.get(position);
        holder.bind(user, position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class GithubViewHolder extends RecyclerView.ViewHolder {

        TextView titletv, releasetv, overviewtv;
        private Searchdetails currentuser;
        ImageView img;

        public GithubViewHolder(View itemView) {
            super(itemView);

            titletv= itemView.findViewById(R.id.titletv);
            releasetv= itemView.findViewById(R.id.releasetv);
            overviewtv= itemView.findViewById(R.id.overviewtv);
            img= itemView.findViewById(R.id.img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent l = new Intent(context, Details.class);
                    l.putExtra("ID", currentuser.getId());
                    context.startActivity(l);
                }
            });
        }

        public void bind(Searchdetails user, int position) {
            currentuser = user;
            titletv.setText(user.getTitle());
            releasetv.setText("Release Date: " + user.getRelease_date());
            overviewtv.setText("\n" + user.getOverview());
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.getPoster_path()).into(img);
        }
    }
}
