package com.ahmaddudayef.moviesmade.data.source

import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie

interface MovieFavoriteDataSource {
    suspend fun getMovies(): List<Movie>
    suspend fun deleteMovie(movie: Movie)
    suspend fun insertMovie(movie: Movie)
    suspend fun getSingleMovie(id: Int): Movie
}