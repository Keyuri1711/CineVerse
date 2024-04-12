package com.example.movieapp.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Adapters.Moviecastadapter;
import com.example.movieapp.Adapters.TvCastAdapter;
import com.example.movieapp.Others.Castinfo;
import com.example.movieapp.Others.GithubService;
import com.example.movieapp.Others.Moviecastarray;
import com.example.movieapp.Others.Tvcastarray;
import com.example.movieapp.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CastDetails extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_castdetails);

        Toolbar toolbar = findViewById(R.id.toolbar11);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Cast Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int pos = getIntent().getIntExtra("castID", 0);
        Retrofit retrofitClient = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubService service = retrofitClient.create(GithubService.class);

        service.castinfo(pos).enqueue(new Callback<Castinfo>() {
            @Override
            public void onResponse(Call<Castinfo> call, Response<Castinfo> response) {
                runOnUiThread(() -> {
                    ImageView img1 = findViewById(R.id.img1);
                    ProgressBar progressBar = findViewById(R.id.progressBar);

                    Picasso.get().load("https://image.tmdb.org/t/p/w500" + response.body().getProfile_path()).fit().centerCrop().into(img1);
                    progressBar.setVisibility(View.GONE);
                    if (response.body().getProfile_path() == null) {
                        Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/1024px-No_image_available.svg.png").into(img1);
                    }
                });
            }

            @Override
            public void onFailure(Call<Castinfo> call, Throwable t) {
                TextView tv = findViewById(R.id.tv);
                tv.setText("Loading failed!");
            }
        });

        service.castinfo(pos).enqueue(new Callback<Castinfo>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Castinfo> call, Response<Castinfo> response) {
                runOnUiThread(() -> {
                    TextView tv = findViewById(R.id.tv);
                    if (response.body().getBirthday() != null && response.body().getPlace_of_birth() != null && response.body().getBiography() != null) {
                        int age = 2019 - Integer.parseInt(response.body().getBirthday().substring(0, 4));
                        tv.setText("\nName : " + response.body().getName() + "\n\nAge : " + age + "\nBirthPlace : " + response.body().getPlace_of_birth() +
                                "\n\nBiography :-\n" + response.body().getBiography());
                    } else {
                        tv.setText("No Information Available");
                    }
                });
            }

            @Override
            public void onFailure(Call<Castinfo> call, Throwable t) {
                TextView tv = findViewById(R.id.tv);
                tv.setText("Loading failed!");
            }
        });

        service.moviecast(pos).enqueue(new Callback<Moviecastarray>() {
            @Override
            public void onResponse(Call<Moviecastarray> call, Response<Moviecastarray> response) {
                runOnUiThread(() -> {
                    RecyclerView rview = findViewById(R.id.rview);
                    rview.setLayoutManager(new LinearLayoutManager(CastDetails.this, LinearLayoutManager.HORIZONTAL, false));
                    rview.setAdapter(new Moviecastadapter(CastDetails.this, response.body().getCast()));
                });
            }

            @Override
            public void onFailure(Call<Moviecastarray> call, Throwable t) {
                TextView tv = findViewById(R.id.tv);
                tv.setText("Loading failed!");
            }
        });

        service.tvcast(pos).enqueue(new Callback<Tvcastarray>() {
            @Override
            public void onResponse(Call<Tvcastarray> call, Response<Tvcastarray> response) {
                runOnUiThread(() -> {
                    RecyclerView rview1 = findViewById(R.id.rview1);
                    rview1.setLayoutManager(new LinearLayoutManager(CastDetails.this, LinearLayoutManager.HORIZONTAL, false));
                    rview1.setAdapter(new TvCastAdapter(CastDetails.this, response.body().getCast()));
                });
            }

            @Override
            public void onFailure(Call<Tvcastarray> call, Throwable t) {
                TextView tv = findViewById(R.id.tv);
                tv.setText("Loading failed!");
            }
        });

        findViewById(R.id.seeMore).setOnClickListener(v -> {
            findViewById(R.id.seeMore).setVisibility(View.GONE);
            findViewById(R.id.seeLess).setVisibility(View.VISIBLE);
            TextView tv = findViewById(R.id.tv);
            tv.setMaxLines(Integer.MAX_VALUE);
        });

        findViewById(R.id.seeLess).setOnClickListener(v -> {
            findViewById(R.id.seeLess).setVisibility(View.GONE);
            findViewById(R.id.seeMore).setVisibility(View.VISIBLE);
            TextView tv = findViewById(R.id.tv);
            tv.setMaxLines(15);
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
