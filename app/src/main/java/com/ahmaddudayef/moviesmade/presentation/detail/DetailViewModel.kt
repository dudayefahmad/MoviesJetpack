package com.ahmaddudayef.moviesmade.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity
import com.ahmaddudayef.moviesmade.data.remote.response.Movie
import com.ahmaddudayef.moviesmade.data.remote.response.TvShow
import com.ahmaddudayef.moviesmade.data.remote.vo.ApiResponse
import com.ahmaddudayef.moviesmade.data.repository.MovieRepository
import com.ahmaddudayef.moviesmade.data.repository.TvShowRepository
import kotlinx.coroutines.launch

class DetailViewModel(
    private val movieRepository: MovieRepository,
    private val tvShowRepository: TvShowRepository
) : ViewModel() {

    fun getDetailMovieById(id: Int): LiveData<MovieEntity> {
        return movieRepository.getDetailMovieById(id)
    }

    fun setFavoriteMovie(movie: MovieEntity) {
        movieRepository.setFavoriteMovie(movie)
    }

    fun getDetailTvShowById(id: Int): LiveData<TvShowEntity> {
        return tvShowRepository.getDetailTvShowById(id)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity) {
        viewModelScope.launch { }
        tvShowRepository.setFavoriteTvShow(tvShow)
    }

    fun getDetailMovieByIdFromNetwork(id: Int): LiveData<ApiResponse<Movie>> {
        return movieRepository.getDetailMovie(id)
    }

    fun getDetailTvShowByIdFromNetwork(id: Int): LiveData<ApiResponse<TvShow>> {
        return tvShowRepository.getDetailTvShow(id)
    }
}