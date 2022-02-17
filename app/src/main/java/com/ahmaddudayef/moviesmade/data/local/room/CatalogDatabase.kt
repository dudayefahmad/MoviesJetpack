package com.ahmaddudayef.moviesmade.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.data.local.room.dao.MovieDao
import com.ahmaddudayef.moviesmade.data.local.room.dao.TvShowDao

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class CatalogDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
}