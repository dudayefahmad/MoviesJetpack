package com.ahmaddudayef.moviesmade.data.local.room.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity

@Dao
interface TvShowDao {

    @Query("SELECT * FROM tvshow")
    fun getAllTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshow WHERE isFavorite = 1")
    fun getAllFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshow WHERE id = :id")
    fun getDetailTvShowById(id: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)
}