package com.ahmaddudayef.moviesmade.core.data.remote.api

import com.ahmaddudayef.moviesmade.core.data.remote.response.ListMoviesResponse
import com.ahmaddudayef.moviesmade.core.data.remote.response.ListTvShowsResponse
import com.ahmaddudayef.moviesmade.core.data.remote.response.MovieResponse
import com.ahmaddudayef.moviesmade.core.data.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("3/discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String
    ): ListMoviesResponse

    @GET("3/discover/tv")
    suspend fun getTvShows(
        @Query("api_key") apiKey: String
    ): ListTvShowsResponse

    @GET("3/search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): ListMoviesResponse

    @GET("3/search/tv")
    suspend fun searchTvShows(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): ListTvShowsResponse

    @GET("3/movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ): MovieResponse?

    @GET("3/tv/{tv_id}")
    suspend fun getDetailTvShow(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String,
    ): TvShowResponse?


}