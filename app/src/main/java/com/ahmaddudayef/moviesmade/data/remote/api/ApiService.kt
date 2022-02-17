package com.ahmaddudayef.moviesmade.data.remote.api

import com.ahmaddudayef.moviesmade.data.remote.response.Movie
import com.ahmaddudayef.moviesmade.data.remote.response.MoviesResponse
import com.ahmaddudayef.moviesmade.data.remote.response.TvShow
import com.ahmaddudayef.moviesmade.data.remote.response.TvShowsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("3/discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String
    ): MoviesResponse

    @GET("3/discover/tv")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String
    ): TvShowsResponse

    @GET("3/search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): MoviesResponse

    @GET("3/search/tv")
    suspend fun searchTvShows(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): TvShowsResponse

    @GET("3/movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): Movie?

    @GET("3/tv/{tv_id}")
    suspend fun getDetailTvShow(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String,
    ): TvShow?


}