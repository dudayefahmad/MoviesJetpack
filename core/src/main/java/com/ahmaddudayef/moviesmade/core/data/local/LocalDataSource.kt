package com.ahmaddudayef.moviesmade.core.data.local

import com.ahmaddudayef.moviesmade.core.data.local.entity.MovieEntity
import com.ahmaddudayef.moviesmade.core.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.core.data.local.room.dao.MovieDao
import com.ahmaddudayef.moviesmade.core.data.local.room.dao.TvShowDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao, private val tvShowDao: TvShowDao) {

    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getAllTvShows(): Flow<List<TvShowEntity>> = tvShowDao.getAllTvShow()

    fun getAllFavoriteMovies(): Flow<List<MovieEntity>> =
        movieDao.getAllFavoriteMovies()

    fun getAllFavoriteTvShows(): Flow<List<TvShowEntity>> =
        tvShowDao.getAllFavoriteTvShows()

    fun getDetailMovieById(id: Int): Flow<MovieEntity> = movieDao.getDetailMovieById(id)

    fun getDetailTvShowById(id: Int): Flow<TvShowEntity> = tvShowDao.getDetailTvShowById(id)

    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    suspend fun insertTvShows(tvShows: List<TvShowEntity>) = tvShowDao.insertTvShows(tvShows)

    fun setFavoriteMovie(movie: MovieEntity) {
        movie.isFavorite = !movie.isFavorite
        movieDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity) {
        tvShow.isFavorite = !tvShow.isFavorite
        tvShowDao.updateTvShow(tvShow)
    }
}