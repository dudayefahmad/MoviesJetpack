package com.ahmaddudayef.moviesmade.core.data.local.room.dao

import androidx.room.*
import com.ahmaddudayef.moviesmade.core.data.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {

    @Query("SELECT * FROM tvshow")
    fun getAllTvShow(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvshow WHERE isFavorite = 1")
    fun getAllFavoriteTvShows(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvshow WHERE id = :id")
    fun getDetailTvShowById(id: Int): Flow<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)
}