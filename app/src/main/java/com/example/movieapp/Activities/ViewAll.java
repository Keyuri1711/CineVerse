package com.example.movieapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Adapters.ViewAllAdapter;
import com.example.movieapp.Others.GithubService;
import com.example.movieapp.Others.Tmdb2;
import com.example.movieapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewAll extends AppCompatActivity {

    Toolbar toolbar;
    ProgressBar progressBar;
    RecyclerView rvViewAll;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        toolbar= findViewById(R.id.toolbarViewAll);
        progressBar= findViewById(R.id.progressBar);
        rvViewAll= findViewById(R.id.rvViewAll);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Movies");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Retrofit retrofitClient = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubService service = retrofitClient.create(GithubService.class);

        String p = getIntent().getStringExtra("category");

        switch (p) {
            case "nowshowing":
                service.nowShowing().enqueue(new Callback<Tmdb2>() {
                    @Override
                    public void onFailure(Call<Tmdb2> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(Call<Tmdb2> call, Response<Tmdb2> response) {
                        runOnUiThread(() -> {
                            rvViewAll.setLayoutManager(new GridLayoutManager(ViewAll.this, 3, RecyclerView.VERTICAL, false));
                            rvViewAll.setAdapter(new ViewAllAdapter(ViewAll.this, response.body().getResults()));
                            progressBar.setVisibility(View.GONE);
                        });
                    }
                });
                break;

            case "popular":
                service.popularMovies().enqueue(new Callback<Tmdb2>() {
                    @Override
                    public void onFailure(Call<Tmdb2> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(Call<Tmdb2> call, Response<Tmdb2> response) {
                        runOnUiThread(() -> {
                            rvViewAll.setLayoutManager(new GridLayoutManager(ViewAll.this, 3, RecyclerView.VERTICAL, false));
                            rvViewAll.setAdapter(new ViewAllAdapter(ViewAll.this, response.body().getResults()));
                            progressBar.setVisibility(View.GONE);
                        });
                    }
                });
                break;

            case "upcoming":
                service.upcoming().enqueue(new Callback<Tmdb2>() {
                    @Override
                    public void onFailure(Call<Tmdb2> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(Call<Tmdb2> call, Response<Tmdb2> response) {
                        runOnUiThread(() -> {
                            rvViewAll.setLayoutManager(new GridLayoutManager(ViewAll.this, 3, RecyclerView.VERTICAL, false));
                            rvViewAll.setAdapter(new ViewAllAdapter(ViewAll.this, response.body().getResults()));
                            progressBar.setVisibility(View.GONE);
                        });
                    }
                });
                break;

            case "toprated":
                service.toprated().enqueue(new Callback<Tmdb2>() {
                    @Override
                    public void onFailure(Call<Tmdb2> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(Call<Tmdb2> call, Response<Tmdb2> response) {
                        runOnUiThread(() -> {
                            rvViewAll.setLayoutManager(new GridLayoutManager(ViewAll.this, 3, RecyclerView.VERTICAL, false));
                            rvViewAll.setAdapter(new ViewAllAdapter(ViewAll.this, response.body().getResults()));
                            progressBar.setVisibility(View.GONE);
                        });
                    }
                });
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

