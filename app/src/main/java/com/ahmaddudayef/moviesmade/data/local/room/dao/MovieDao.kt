package com.ahmaddudayef.moviesmade.data.local.room.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getAllFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getDetailMovieById(id: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)
}