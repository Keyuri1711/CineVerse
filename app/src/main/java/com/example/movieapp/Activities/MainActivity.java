package com.example.movieapp.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

import com.example.movieapp.Adapters.TmdbAdapter;
import com.example.movieapp.Adapters.TmdbAdapter2;
import com.example.movieapp.Others.GithubService;
import com.example.movieapp.Others.Tmdb2;
import com.example.movieapp.R;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView, rview2, rview3, rview4;
    DrawerLayout drawerLayout;
    Button viewAll1, viewAll2, viewAll3, viewAll4;
    NavigationView nav_view;
    DrawerLayout drawer_layout;
    Toolbar toolbar1;
    TextView tv;

    Retrofit retrofitClient = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    GithubService service = retrofitClient.create(GithubService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        viewAll1= findViewById(R.id.viewAll1);
        viewAll2= findViewById(R.id.viewAll2);
        viewAll3= findViewById(R.id.viewAll3);
        viewAll4= findViewById(R.id.viewAll4);
        drawer_layout= findViewById(R.id.drawer_layout);
        nav_view= findViewById(R.id.nav_view);
        toolbar1= findViewById(R.id.toolbar1);
        recyclerView= findViewById(R.id.rview);
        rview2= findViewById(R.id.rview2);
        rview3= findViewById(R.id.rview3);
        rview4= findViewById(R.id.rview4);
        drawerLayout= findViewById(R.id.drawer_layout);
        tv= findViewById(R.id.tv);

        setSupportActionBar(findViewById(R.id.toolbar1));

        service.nowShowing().enqueue(new Callback<Tmdb2>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NotNull Call<Tmdb2> call, @NotNull Response<Tmdb2> response) {
                runOnUiThread(() -> {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                    TmdbAdapter adapter = new TmdbAdapter(MainActivity.this, response.body().getResults());
                    recyclerView.setAdapter(adapter);
                    PagerSnapHelper snapHelper = new PagerSnapHelper();
                    snapHelper.attachToRecyclerView(findViewById(R.id.rview));
                    findViewById(R.id.progressBar).setVisibility(View.GONE);
                });
            }

            @Override
            public void onFailure(@NotNull Call<Tmdb2> call, @NotNull Throwable t) {
                findViewById(R.id.tv).setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.tv)).setText("Loading failed, check your internet connection !!!");
            }
        });

        service.popularMovies().enqueue(new Callback<Tmdb2>() {
            @Override
            public void onResponse(Call<Tmdb2> call, Response<Tmdb2> response) {
                runOnUiThread(() -> {
                    rview2.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    TmdbAdapter2 adapter = new TmdbAdapter2(MainActivity.this, response.body().getResults());
                    rview2.setAdapter(adapter);
                    PagerSnapHelper snapHelper = new PagerSnapHelper();
                    snapHelper.attachToRecyclerView(findViewById(R.id.rview2));
                    findViewById(R.id.progressBar).setVisibility(View.GONE);
                });
            }

            @Override
            public void onFailure(Call<Tmdb2> call, Throwable t) {
                findViewById(R.id.tv).setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.tv)).setText("Loading failed, check your internet connection !!!");
            }
        });

        service.upcoming().enqueue(new Callback<Tmdb2>() {
            @Override
            public void onResponse(Call<Tmdb2> call, Response<Tmdb2> response) {
                runOnUiThread(() -> {
                    rview3.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    TmdbAdapter adapter = new TmdbAdapter(MainActivity.this, response.body().getResults());
                    rview3.setAdapter(adapter);
                    PagerSnapHelper snapHelper = new PagerSnapHelper();
                    snapHelper.attachToRecyclerView(findViewById(R.id.rview3));
                    findViewById(R.id.progressBar).setVisibility(View.GONE);
                });
            }

            @Override
            public void onFailure(Call<Tmdb2> call, Throwable t) {
                findViewById(R.id.tv).setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.tv)).setText("Loading failed, check your internet connection !!!");
            }
        });

        service.toprated().enqueue(new Callback<Tmdb2>() {
            @Override
            public void onResponse(Call<Tmdb2> call, Response<Tmdb2> response) {
                runOnUiThread(() -> {
                    rview4.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    TmdbAdapter2 adapter = new TmdbAdapter2(MainActivity.this, response.body().getResults());
                    rview4.setAdapter(adapter);
                    PagerSnapHelper snapHelper = new PagerSnapHelper();
                    snapHelper.attachToRecyclerView(findViewById(R.id.rview4));
                    findViewById(R.id.progressBar).setVisibility(View.GONE);
                });
            }

            @Override
            public void onFailure(Call<Tmdb2> call, Throwable t) {
                findViewById(R.id.tv).setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.tv)).setText("Loading failed, check your internet connection !!!");
            }
        });


        viewAll1.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ViewAll.class).putExtra("category", "nowshowing")));
        viewAll2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ViewAll.class).putExtra("category", "popular")));
        viewAll3.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ViewAll.class).putExtra("category", "upcoming")));
        viewAll4.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ViewAll.class).putExtra("category", "toprated")));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_layout, toolbar1,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        nav_view.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
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
                Intent intent = new Intent(MainActivity.this, Search.class);
                intent.putExtra("Search Text", query);
                if (!query.isEmpty()) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Write something to search !!!", Toast.LENGTH_SHORT).show();
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
        } else if (itemId == R.id.nav_tvShows) {
            startActivity(new Intent(this, TVShowsActivity.class));
        }else if (itemId == R.id.nav_about) {
            startActivity(new Intent(this, AboutActivity.class));
        } else if (itemId == R.id.nav_contactUs) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto: keyuridhorajiya17@gmail.com"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Regarding CineVerse");
            startActivity(Intent.createChooser(intent, "Send Email"));
        } else if (itemId == R.id.nav_logout) {
            Intent intent = new Intent(MainActivity.this, IntroActivity.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
