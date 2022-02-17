package com.ahmaddudayef.moviesmade.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.data.remote.response.TvShow
import com.ahmaddudayef.moviesmade.data.remote.vo.ApiResponse
import com.ahmaddudayef.moviesmade.vo.Resource

interface TvShowDataSource {

    fun getAllTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getAllFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>

    fun getDetailTvShowById(id: Int): LiveData<TvShowEntity>

    fun setFavoriteTvShow(tvShow: TvShowEntity)

    suspend fun searchTvShow(query: String): List<TvShow>

    fun getDetailTvShowByIdFromNetwork(id: Int): LiveData<ApiResponse<TvShow>>

}