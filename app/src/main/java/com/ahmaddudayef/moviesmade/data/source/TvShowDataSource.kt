package com.ahmaddudayef.moviesmade.data.source

import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow

interface TvShowDataSource {
    suspend fun getTvShow(languange: String): List<TvShow>
}