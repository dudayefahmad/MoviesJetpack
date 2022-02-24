package com.ahmaddudayef.moviesmade.core.data.repository

import com.ahmaddudayef.moviesmade.core.data.NetworkBoundResource
import com.ahmaddudayef.moviesmade.core.data.Resource
import com.ahmaddudayef.moviesmade.core.data.local.LocalDataSource
import com.ahmaddudayef.moviesmade.core.data.remote.RemoteDataSource
import com.ahmaddudayef.moviesmade.core.data.remote.api.ApiResponse
import com.ahmaddudayef.moviesmade.core.data.remote.response.TvShowResponse
import com.ahmaddudayef.moviesmade.core.domain.model.TvShow
import com.ahmaddudayef.moviesmade.core.domain.repository.ITvShowRepository
import com.ahmaddudayef.moviesmade.core.util.AppExecutors
import com.ahmaddudayef.moviesmade.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class TvShowRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : ITvShowRepository {

    override fun getAllTvShow(): Flow<Resource<List<TvShow>>> =
        object : NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getAllTvShows().map {
                    DataMapper.mapListTvShowEntitiesToListTvShowDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTvShows()

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val listTvShow = DataMapper.mapListTvShowsResponseToListTvShowEntities(data)
                localDataSource.insertTvShows(listTvShow)
            }

        }.asFlow()

    override fun getAllFavoriteTvShow(): Flow<List<TvShow>> {
        return localDataSource.getAllFavoriteTvShows().map {
            DataMapper.mapListTvShowEntitiesToListTvShowDomain(it)
        }
    }

    override fun getDetailTvShowById(id: Int): Flow<TvShow> {
        return localDataSource.getDetailTvShowById(id).map {
            DataMapper.mapTvShowEntityToTvShowDomain(it)
        }
    }

    override fun setFavoriteTvShow(tvShow: TvShow) {
        val mapper = DataMapper.mapTvShowDomainToTvShowEntity(tvShow)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(mapper) }
    }

    override suspend fun searchTvShow(query: String): Flow<Resource<List<TvShow>>> =
        flow {
            emit(Resource.Loading())
            when (val searchResponse = remoteDataSource.searchTvShows(query).first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(searchResponse.data.map {
                        DataMapper.mapTvShowResponseToTvShowDomain(it)
                    }))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(listOf()))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(searchResponse.errorMessage))
                }
            }
        }

    override fun getDetailTvShowByIdFromNetwork(id: Int): Flow<Resource<TvShow>> =
        flow {
            emit(Resource.Loading())
            when (val detailMovieResponse = remoteDataSource.getDetailTvShow(id).first()) {
                is ApiResponse.Success -> {
                    val detailMovieMapper =
                        DataMapper.mapTvShowResponseToTvShowDomain(detailMovieResponse.data)
                    emit(Resource.Success(detailMovieMapper))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(detailMovieResponse.errorMessage))
                }
            }
        }


}