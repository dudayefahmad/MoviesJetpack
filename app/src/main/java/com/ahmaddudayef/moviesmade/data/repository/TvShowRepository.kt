package com.ahmaddudayef.moviesmade.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ahmaddudayef.moviesmade.data.NetworkBoundResource
import com.ahmaddudayef.moviesmade.data.local.LocalDataSource
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.data.remote.RemoteDataSource
import com.ahmaddudayef.moviesmade.data.remote.response.TvShow
import com.ahmaddudayef.moviesmade.data.remote.vo.ApiResponse
import com.ahmaddudayef.moviesmade.data.source.TvShowDataSource
import com.ahmaddudayef.moviesmade.util.AppExecutors
import com.ahmaddudayef.moviesmade.vo.Resource

class TvShowRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : TvShowDataSource {
    override fun getAllTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShow>>> {
                return remoteDataSource.getTvShows()
            }

            override fun saveCallResult(data: List<TvShow>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (tvShowItem in data) {
                    val tvShow = TvShowEntity(
                        tvShowItem.id,
                        tvShowItem.firstAirDate,
                        tvShowItem.overview,
                        tvShowItem.posterPath,
                        tvShowItem.backdropPath,
                        tvShowItem.voteAverage,
                        tvShowItem.name,
                        false
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getAllFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getAllFavoriteTvShows(), config).build()
    }

    override fun getDetailTvShowById(id: Int): LiveData<TvShowEntity> {
        return localDataSource.getDetailTvShowById(id)
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShow) }
    }

    override suspend fun searchTvShow(query: String): List<TvShow> {
        return remoteDataSource.searchTvShows(query)
    }

    override fun getDetailTvShow(id: Int): LiveData<ApiResponse<TvShow>> {
        return remoteDataSource.getDetailTvShow(id)
    }


}