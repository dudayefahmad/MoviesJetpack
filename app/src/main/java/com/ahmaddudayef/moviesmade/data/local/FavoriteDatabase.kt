package com.ahmaddudayef.moviesmade.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmaddudayef.moviesmade.data.local.dao.MovieDao
import com.ahmaddudayef.moviesmade.data.local.dao.TvShowDao
import com.ahmaddudayef.moviesmade.data.remote.response.movies.Movie
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow

@Database(entities = [Movie::class, TvShow::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
}