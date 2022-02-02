package com.ahmaddudayef.moviesmade.data.local.dao

import androidx.room.*
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    suspend fun getAllMovie(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getSingleMovie(id: Int): Movie
}