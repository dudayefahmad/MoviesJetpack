package com.ahmaddudayef.moviesmade.data

import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movies
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShows
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("3/discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Movies

    @GET("3/discover/tv")
    suspend fun getTvShow(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): TvShows

    @GET("3/search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Movies

    @GET("3/search/tv")
    suspend fun searchTVShow(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): TvShows

    @GET("3/discover/movie")
    suspend fun getReleaseToday(
        @Query("api_key") apiKey: String,
        @Query("primary_release_date.gte") releaseDateGte: String,
        @Query("primary_release_date.lte") releaseDateLte: String
    ): Movies
}