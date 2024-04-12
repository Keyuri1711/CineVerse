package com.example.movieapp.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Adapters.MovieAdapter;
import com.example.movieapp.Adapters.MovieAdapter2;
import com.example.movieapp.Adapters.TmdbAdaptorTv2;
import com.example.movieapp.Others.Cast;
import com.example.movieapp.Others.GithubService;
import com.example.movieapp.Others.Overview;
import com.example.movieapp.Others.TmdbTv;
import com.example.movieapp.Others.Trailerarray;
import com.example.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsTv extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_tv);
        toolbar = (Toolbar) findViewById(R.id.toolbar33);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("TV Show Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int pos = getIntent().getIntExtra("ID", 55);
        Retrofit retrofitClient = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/tv/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubService service = retrofitClient.create(GithubService.class);

        service.overview(pos).enqueue(new Callback<Overview>() {
            @Override
            public void onResponse(Call<Overview> call, Response<Overview> response) {
                runOnUiThread(() -> {
                    ImageView toolbarimage = findViewById(R.id.toolbarimage);
                    ProgressBar progressBar = findViewById(R.id.progressBar);

                    Picasso.get().load("https://image.tmdb.org/t/p/original" + response.body().getBackdrop_path()).fit().centerCrop().into(toolbarimage);
                    progressBar.setVisibility(View.GONE);
                    if (response.body().getBackdrop_path() == null) {
                        Picasso.get().load("https://fasterthemes.com/demo/foodrecipespro-wordpress-theme/wp-content/themes/foodrecipespro/images/no-image.jpg").fit().centerCrop().into(toolbarimage);
                    }
                });
            }

            @Override
            public void onFailure(Call<Overview> call, Throwable t) {
                TextView tv = findViewById(R.id.tv);
                tv.setText("Loading failed!");
            }
        });

        service.overview(pos).enqueue(new Callback<Overview>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Overview> call, Response<Overview> response) {
                runOnUiThread(() -> {
                    TextView tv = findViewById(R.id.tv);
                    StringBuilder genres = new StringBuilder();

                    tv.setText("\n\n⭐ " + response.body().getVote_average() + "/10\n\nPlot :  " + response.body().getOverview() +
                            "\n\nFirst Air Date : " + response.body().getFirst_air_date() + "\n\nStatus : " +
                            response.body().getStatus() + "\n\nGenres : ");
                    for (int i = 0; i < response.body().getGenres().size(); i++) {
                        genres.append(response.body().getGenres().get(i).getName()).append(", ");
                    }
                    tv.setText(tv.getText().toString().concat(genres.toString().substring(0, genres.length() - 2))); //to remove the extra comma
                });
            }

            @Override
            public void onFailure(Call<Overview> call, Throwable t) {
                TextView tv = findViewById(R.id.tv);
                tv.setText("Loading failed!");
            }
        });

        service.trailers(pos).enqueue(new Callback<Trailerarray>() {
            @Override
            public void onResponse(Call<Trailerarray> call, Response<Trailerarray> response) {
                runOnUiThread(() -> {
                    RecyclerView rview3 = findViewById(R.id.rview3);
                    rview3.setLayoutManager(new LinearLayoutManager(DetailsTv.this, LinearLayoutManager.HORIZONTAL, false));
                    rview3.setAdapter(new MovieAdapter2(DetailsTv.this, response.body().getResults()));
                });
            }

            @Override
            public void onFailure(Call<Trailerarray> call, Throwable t) {
                TextView tv = findViewById(R.id.tv);
                tv.setText("Loading failed!!!");
            }
        });

        service.castTv(pos).enqueue(new Callback<Cast>() {
            @Override
            public void onResponse(Call<Cast> call, Response<Cast> response) {
                runOnUiThread(() -> {
                    RecyclerView rview = findViewById(R.id.rview);
                    rview.setLayoutManager(new LinearLayoutManager(DetailsTv.this, LinearLayoutManager.HORIZONTAL, false));
                    rview.setAdapter(new MovieAdapter(DetailsTv.this, response.body().getCast()));
                });
            }

            @Override
            public void onFailure(Call<Cast> call, Throwable t) {
                TextView tv = findViewById(R.id.tv);
                tv.setText("Loading Failed !!!");
            }
        });

        service.similarTv(pos).enqueue(new Callback<TmdbTv>() {
            @Override
            public void onResponse(Call<TmdbTv> call, Response<TmdbTv> response) {
                runOnUiThread(() -> {
                    RecyclerView rview2 = findViewById(R.id.rview2);
                    rview2.setLayoutManager(new LinearLayoutManager(DetailsTv.this, LinearLayoutManager.HORIZONTAL, false));
                    rview2.setAdapter(new TmdbAdaptorTv2(DetailsTv.this, response.body().getResults()));
                });
            }

            @Override
            public void onFailure(Call<TmdbTv> call, Throwable t) {
                TextView tv = findViewById(R.id.tv);
                tv.setText("Loading Failed !!!");
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
