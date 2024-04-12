package com.example.movieapp.Others;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {

    @GET("now_playing?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    Call<Tmdb2> nowShowing();

    @GET("popular?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    Call<Tmdb2> popularMovies();

    @GET("upcoming?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&region=US&page=1")
    Call<Tmdb2> upcoming();

    @GET("top_rated?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    Call<Tmdb2> toprated();

    @GET("{Id}/credits?api_key=20ddfcf94f3bf96b48118c43a689756c")
    Call<Cast> cast(@Path("Id") int id);

    @GET("{Id}/similar?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    Call<Tmdb2> similarmovies(@Path("Id") int id);

    @GET("{Id}/videos?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    Call<Trailerarray> trailers(@Path("Id") int id);

    @GET("{Id}?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US")
    Call<Overview> overview(@Path("Id") int id);

    @GET("person/{Id}?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US")
    Call<Castinfo> castinfo(@Path("Id") int id);

    @GET("person/{Id}/movie_credits?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US")
    Call<Moviecastarray> moviecast(@Path("Id") int id);

    @GET("person/{Id}/tv_credits?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US")
    Call<Tvcastarray> tvcast(@Path("Id") int id);

    @GET("search/movie?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&query&page=1")
    Call<Searcharray> search(@Query("query") String q);

    @GET("search/tv?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&query&page=1")
    Call<Searcharray> searchtv(@Query("query") String q);

    @GET("airing_today?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    Call<TmdbTv> airingToday();

    @GET("on_the_air?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    Call<TmdbTv> onTheAir();

    @GET("popular?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    Call<TmdbTv> popularTv();

    @GET("top_rated?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    Call<TmdbTv> topRatedTv();

    @GET("{Id}/credits?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US")
    Call<Cast> castTv(@Path("Id") int id);

    @GET("{Id}/similar?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    Call<TmdbTv> similarTv(@Path("Id") int id);
}
