package com.example.movieapp.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Adapters.TmdbAdaptorTv;
import com.example.movieapp.Adapters.TmdbAdaptorTv2;
import com.example.movieapp.Others.GithubService;
import com.example.movieapp.Others.TmdbTv;
import com.example.movieapp.R;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TVShowsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    ProgressBar progressBar;
    TextView tv;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    RecyclerView rview, rview2, rview3, rview4;
    private Retrofit retrofitClient = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/tv/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private GithubService service = retrofitClient.create(GithubService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshows);

        toolbar = (Toolbar) findViewById(com.example.movieapp.R.id.toolbar1);
        setSupportActionBar(toolbar);
        progressBar= findViewById(R.id.progressBar);
        tv = findViewById(R.id.tv);
        drawerLayout= findViewById(R.id.drawer_layout);
        navigationView= findViewById(R.id.nav_view);
        rview = findViewById(R.id.rview);
        rview2 = findViewById(R.id.rview2);
        rview3 = findViewById(R.id.rview3);
        rview4 = findViewById(R.id.rview4);
        // Retrofit
        service.airingToday().enqueue(new Callback<TmdbTv>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<TmdbTv> call, Response<TmdbTv> response) {
                runOnUiThread(() -> {
                    rview.setLayoutManager(new LinearLayoutManager(TVShowsActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    rview.setAdapter(new TmdbAdaptorTv(TVShowsActivity.this, response.body().getResults()));
                    progressBar.setVisibility(View.GONE);
                });
            }

            @Override
            public void onFailure(Call<TmdbTv> call, Throwable t) {
                tv.setText("Loading failed, check your internet connection !!!");
            }
        });

        service.onTheAir().enqueue(new Callback<TmdbTv>() {
            @Override
            public void onResponse(Call<TmdbTv> call, Response<TmdbTv> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rview2.setLayoutManager(new LinearLayoutManager(TVShowsActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        rview2.setAdapter(new TmdbAdaptorTv2(TVShowsActivity.this, response.body().getResults()));
                    }
                });
            }

            @Override
            public void onFailure(Call<TmdbTv> call, Throwable t) {
                tv.setText("Loading failed!");
            }
        });

        service.popularTv().enqueue(new Callback<TmdbTv>() {
            @Override
            public void onResponse(Call<TmdbTv> call, Response<TmdbTv> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rview3.setLayoutManager(new LinearLayoutManager(TVShowsActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        rview3.setAdapter(new TmdbAdaptorTv(TVShowsActivity.this, response.body().getResults()));
                        PagerSnapHelper snapItemHelper = new PagerSnapHelper();
                        snapItemHelper.attachToRecyclerView(rview3);
                    }
                });
            }

            @Override
            public void onFailure(Call<TmdbTv> call, Throwable t) {
                tv.setText("Loading failed!");
            }
        });

        service.topRatedTv().enqueue(new Callback<TmdbTv>() {
            @Override
            public void onResponse(Call<TmdbTv> call, Response<TmdbTv> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rview4.setLayoutManager(new LinearLayoutManager(TVShowsActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        rview4.setAdapter(new TmdbAdaptorTv2(TVShowsActivity.this, response.body().getResults()));
                    }
                });
            }

            @Override
            public void onFailure(Call<TmdbTv> call, Throwable t) {
                tv.setText("Loading failed!");
            }
        });


        // Drawer setup
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(TVShowsActivity.this, SearchTv.class);
                intent.putExtra("Search Text", query);
                if (!query.isEmpty()) {
                    startActivity(intent);
                } else {
                    Toast.makeText(TVShowsActivity.this, "Write something in search bar !!!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.action_profile) {
            Toast.makeText(this, "User profile will be available after SignIn/SignOut part is completed.", Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_movies) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (itemId == R.id.nav_about) {
            startActivity(new Intent(this, AboutActivity.class));
        } else if (itemId == R.id.nav_contactUs) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto: keyuridhorajiya17@gmail.com"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Regarding CineVerse");
            startActivity(Intent.createChooser(intent, "Send Email"));
        } else if (itemId == R.id.nav_logout) {
            Intent intent = new Intent(TVShowsActivity.this, IntroActivity.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
