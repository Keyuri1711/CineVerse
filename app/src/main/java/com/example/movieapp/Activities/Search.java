package com.example.movieapp.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Adapters.SearchAdapter;
import com.example.movieapp.Others.GithubService;
import com.example.movieapp.Others.Searcharray;
import com.example.movieapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Search extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ImageView imageView;
    TextView textView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar= findViewById(R.id.toolbarSearch);
        recyclerView = findViewById(R.id.rview);
        imageView = findViewById(R.id.nothing);
        textView = findViewById(R.id.tv);
        progressBar = findViewById(R.id.progressBar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String text = getIntent().getStringExtra("Search Text");
        Retrofit retrofitClient = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubService service = retrofitClient.create(GithubService.class);
        service.search(text).enqueue(new Callback<Searcharray>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Searcharray> call, Response<Searcharray> response) {
                runOnUiThread(() -> {
                    if (response.body().getResults().isEmpty()) {
                        imageView.setVisibility(View.VISIBLE);
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(Search.this, RecyclerView.VERTICAL, false));
                    recyclerView.setAdapter(new SearchAdapter(Search.this, response.body().getResults()));
                    progressBar.setVisibility(View.GONE);
                });
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFailure(Call<Searcharray> call, Throwable t) {
                runOnUiThread(() -> textView.setText("Loading failed!"));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
