package com.ahmaddudayef.moviesmade.core.data.repository

import com.ahmaddudayef.moviesmade.core.data.NetworkBoundResource
import com.ahmaddudayef.moviesmade.core.data.Resource
import com.ahmaddudayef.moviesmade.core.data.local.LocalDataSource
import com.ahmaddudayef.moviesmade.core.data.remote.RemoteDataSource
import com.ahmaddudayef.moviesmade.core.data.remote.api.ApiResponse
import com.ahmaddudayef.moviesmade.core.data.remote.response.MovieResponse
import com.ahmaddudayef.moviesmade.core.domain.model.Movie
import com.ahmaddudayef.moviesmade.core.domain.repository.IMovieRepository
import com.ahmaddudayef.moviesmade.core.util.AppExecutors
import com.ahmaddudayef.moviesmade.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapListMovieEntitiesToListMovieDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val listMovie = DataMapper.mapListMoviesResponseToListMovieEntities(data)
                localDataSource.insertMovies(listMovie)
            }
        }.asFlow()

    override fun getAllFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getAllFavoriteMovies().map {
            DataMapper.mapListMovieEntitiesToListMovieDomain(it)
        }
    }

    override fun getDetailMovieById(id: Int): Flow<Movie> {
        return localDataSource.getDetailMovieById(id).map {
            DataMapper.mapMovieEntityToMovieDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie) {
        val mapper = DataMapper.mapMovieDomainToMovieEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(mapper) }
    }

    override suspend fun searchMovies(query: String): Flow<Resource<List<Movie>>> =
        flow {
            emit(Resource.Loading())
            when (val searchResponse = remoteDataSource.searchMovies(query).first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(searchResponse.data.map {
                        DataMapper.mapMovieResponseToMovieDomain(it)
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

    override fun getDetailMovieByIdFromNetwork(id: Int): Flow<Resource<Movie>> =
        flow {
            emit(Resource.Loading())
            when (val detailMovieResponse = remoteDataSource.getDetailMovie(id).first()) {
                is ApiResponse.Success -> {
                    val detailMovieMapper = DataMapper.mapMovieResponseToMovieDomain(detailMovieResponse.data)
                    emit(Resource.Success(detailMovieMapper))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(detailMovieResponse.errorMessage))
                }
            }
        }

}