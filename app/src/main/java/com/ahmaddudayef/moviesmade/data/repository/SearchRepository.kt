package com.ahmaddudayef.moviesmade.data.repository

import com.ahmaddudayef.moviesmade.BuildConfig
import com.ahmaddudayef.moviesmade.data.ApiService
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow
import com.ahmaddudayef.moviesmade.data.source.SearchDataSource

class SearchRepository(private val apiService: ApiService) : SearchDataSource {

    override suspend fun searchMovies(query: String): List<Movie> {
        return apiService.searchMovie(BuildConfig.API_KEY, query).results
    }

    override suspend fun searchTvShow(query: String): List<TvShow> {
        return apiService.searchTVShow(BuildConfig.API_KEY, query).results
    }

}