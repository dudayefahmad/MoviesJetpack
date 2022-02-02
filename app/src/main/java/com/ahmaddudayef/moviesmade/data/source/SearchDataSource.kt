package com.ahmaddudayef.moviesmade.data.source

import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow

interface SearchDataSource {
    suspend fun searchMovies(query: String): List<Movie>
    suspend fun searchTvShow(query: String): List<TvShow>
}