package com.ahmaddudayef.moviesmade.data.repository

import com.ahmaddudayef.moviesmade.BuildConfig
import com.ahmaddudayef.moviesmade.data.ApiService
import com.ahmaddudayef.moviesmade.data.remote.response.tvshow.TvShow
import com.ahmaddudayef.moviesmade.data.source.TvShowDataSource

class TvShowRepository(private val apiService: ApiService) : TvShowDataSource {

    override suspend fun getTvShow(languange: String): List<TvShow> {
        return apiService.getTvShow(BuildConfig.API_KEY, languange).results
    }

}