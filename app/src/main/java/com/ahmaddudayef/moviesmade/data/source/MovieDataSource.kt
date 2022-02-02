package com.ahmaddudayef.moviesmade.data.source

import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie

interface MovieDataSource {
    suspend fun getMovies(languange: String): List<Movie>
}