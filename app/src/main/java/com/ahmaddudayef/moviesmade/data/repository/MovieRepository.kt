package com.ahmaddudayef.moviesmade.data.repository

import com.ahmaddudayef.moviesmade.BuildConfig
import com.ahmaddudayef.moviesmade.data.ApiService
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.data.source.MovieDataSource

class MovieRepository(private val apiService: ApiService) : MovieDataSource {

    override suspend fun getMovies(languange: String): List<Movie> {
        return apiService.getMovies(BuildConfig.API_KEY, languange).results
    }

}