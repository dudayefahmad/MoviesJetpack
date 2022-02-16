package com.ahmaddudayef.moviesmade.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ahmaddudayef.moviesmade.data.NetworkBoundResource
import com.ahmaddudayef.moviesmade.data.local.LocalDataSource
import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity
import com.ahmaddudayef.moviesmade.data.remote.RemoteDataSource
import com.ahmaddudayef.moviesmade.data.remote.response.Movie
import com.ahmaddudayef.moviesmade.data.remote.vo.ApiResponse
import com.ahmaddudayef.moviesmade.data.source.MovieDataSource
import com.ahmaddudayef.moviesmade.util.AppExecutors
import com.ahmaddudayef.moviesmade.vo.Resource

class MovieRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : MovieDataSource {

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<Movie>>(appExecutors) {

            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            public override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            public override fun createCall(): LiveData<ApiResponse<List<Movie>>> {
                return remoteDataSource.getMovies()
            }

            public override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<MovieEntity>()
                for (movieItem in data) {
                    val movie = MovieEntity(
                        movieItem.id,
                        movieItem.overview,
                        movieItem.title,
                        movieItem.posterPath,
                        movieItem.backdropPath,
                        movieItem.releaseDate,
                        movieItem.voteAverage,
                        false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getAllFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getAllFavoriteMovies(), config).build()
    }

    override fun getDetailMovieById(id: Int): LiveData<MovieEntity> {
        return localDataSource.getDetailMovieById(id)
    }

    override fun setFavoriteMovie(movie: MovieEntity) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie) }
    }

    override suspend fun searchMovies(query: String): List<Movie> {
        return remoteDataSource.searchMovies(query)
    }

    override fun getDetailMovie(id: Int): LiveData<ApiResponse<Movie>> {
        return remoteDataSource.getDetailMovie(id)
    }

}