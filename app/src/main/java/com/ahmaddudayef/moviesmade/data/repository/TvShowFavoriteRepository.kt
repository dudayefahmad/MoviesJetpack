package com.ahmaddudayef.moviesmade.data.repository

import com.ahmaddudayef.moviesmade.data.local.FavoriteDatabase
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow
import com.ahmaddudayef.moviesmade.data.source.TvShowFavoriteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TvShowFavoriteRepository(
    private val favoriteDatabase: FavoriteDatabase
) : TvShowFavoriteDataSource {

    override suspend fun getTvShow(): List<TvShow> {
        return favoriteDatabase.tvShowDao().getAllTvShow()
    }

    override suspend fun deleteTvShow(tvShow: TvShow) {
        return withContext(Dispatchers.IO) {
            favoriteDatabase.tvShowDao().deleteTvShow(tvShow)
        }
        return
    }

    override suspend fun insertTvShow(tvShow: TvShow) {
        return withContext(Dispatchers.IO) {
            favoriteDatabase.tvShowDao().insertTvShow(tvShow)
        }
    }

    override suspend fun getSingleTvShow(id: Int): TvShow {
        return favoriteDatabase.tvShowDao().getSingleTvShow(id)
    }

}
