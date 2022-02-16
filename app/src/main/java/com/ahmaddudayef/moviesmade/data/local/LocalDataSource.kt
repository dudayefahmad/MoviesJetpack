package com.ahmaddudayef.moviesmade.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.data.local.room.dao.MovieDao
import com.ahmaddudayef.moviesmade.data.local.room.dao.TvShowDao

class LocalDataSource(private val movieDao: MovieDao, private val tvShowDao: TvShowDao) {

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = movieDao.getAllMovies()

    fun getAllTvShows(): DataSource.Factory<Int, TvShowEntity> = tvShowDao.getAllTvShow()

    fun getAllFavoriteMovies(): DataSource.Factory<Int, MovieEntity> =
        movieDao.getAllFavoriteMovies()

    fun getAllFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> =
        tvShowDao.getAllFavoriteTvShows()

    fun getDetailMovieById(id: Int): LiveData<MovieEntity> = movieDao.getDetailMovieById(id)

    fun getDetailTvShowById(id: Int): LiveData<TvShowEntity> = tvShowDao.getDetailTvShowById(id)

    fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvShowEntity>) = tvShowDao.insertTvShows(tvShows)

    fun setFavoriteMovie(movie: MovieEntity) {
        movie.isFavorite = !movie.isFavorite
        movieDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity) {
        tvShow.isFavorite = !tvShow.isFavorite
        tvShowDao.updateTvShow(tvShow)
    }
}