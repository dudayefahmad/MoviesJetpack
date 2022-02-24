package com.ahmaddudayef.moviesmade.core.domain.repository

import com.ahmaddudayef.moviesmade.core.data.Resource
import com.ahmaddudayef.moviesmade.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface ITvShowRepository {

    fun getAllTvShow(): Flow<Resource<List<TvShow>>>

    fun getAllFavoriteTvShow(): Flow<List<TvShow>>

    fun getDetailTvShowById(id: Int): Flow<TvShow>

    fun setFavoriteTvShow(tvShow: TvShow)

    suspend fun searchTvShow(query: String): Flow<Resource<List<TvShow>>>

    fun getDetailTvShowByIdFromNetwork(id: Int): Flow<Resource<TvShow>>
}