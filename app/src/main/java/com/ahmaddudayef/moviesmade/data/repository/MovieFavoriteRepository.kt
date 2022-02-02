package com.ahmaddudayef.moviesmade.data.repository

import com.ahmaddudayef.moviesmade.data.local.FavoriteDatabase
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.data.source.MovieFavoriteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieFavoriteRepository(
    private val favoriteDatabase: FavoriteDatabase
) : MovieFavoriteDataSource {

    override suspend fun getMovies(): List<Movie> {
        return favoriteDatabase.movieDao().getAllMovie()
    }

    override suspend fun deleteMovie(movie: Movie) {
        return withContext(Dispatchers.IO) {
            favoriteDatabase.movieDao().deleteMovie(movie)
        }
    }

    override suspend fun insertMovie(movie: Movie) {
        return withContext(Dispatchers.IO) {
            favoriteDatabase.movieDao().insertMovie(movie)
        }
    }

    override suspend fun getSingleMovie(id: Int): Movie {
        return favoriteDatabase.movieDao().getSingleMovie(id)
    }


}