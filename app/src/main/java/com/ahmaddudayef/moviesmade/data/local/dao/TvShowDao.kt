package com.ahmaddudayef.moviesmade.data.local.dao

import androidx.room.*
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow

@Dao
interface TvShowDao {

    @Query("SELECT * FROM tvshow")
    suspend fun getAllTvShow(): List<TvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShow: TvShow)

    @Delete
    suspend fun deleteTvShow(tvShow: TvShow)

    @Query("SELECT * FROM tvshow WHERE id = :id")
    suspend fun getSingleTvShow(id: Int): TvShow
}