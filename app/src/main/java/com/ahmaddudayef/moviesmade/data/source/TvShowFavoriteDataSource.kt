package com.ahmaddudayef.moviesmade.data.source

import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow

interface TvShowFavoriteDataSource {
    suspend fun getTvShow(): List<TvShow>
    suspend fun deleteTvShow(tvShow: TvShow)
    suspend fun insertTvShow(tvShow: TvShow)
    suspend fun getSingleTvShow(id: Int): TvShow
}