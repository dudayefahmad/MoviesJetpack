package com.ahmaddudayef.moviesmade.core.domain.repository

import com.ahmaddudayef.moviesmade.core.data.Resource
import com.ahmaddudayef.moviesmade.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovies(): Flow<Resource<List<Movie>>>

    fun getAllFavoriteMovies(): Flow<List<Movie>>

    fun getDetailMovieById(id: Int): Flow<Movie>

    fun setFavoriteMovie(movie: Movie)

    suspend fun searchMovies(query: String): Flow<Resource<List<Movie>>>

    fun getDetailMovieByIdFromNetwork(id: Int): Flow<Resource<Movie>>

}