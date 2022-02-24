package com.ahmaddudayef.moviesmade.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ahmaddudayef.moviesmade.core.domain.model.Movie
import com.ahmaddudayef.moviesmade.core.domain.model.TvShow
import com.ahmaddudayef.moviesmade.core.domain.usecase.MovieUseCase
import com.ahmaddudayef.moviesmade.core.domain.usecase.TvShowUseCase

class DetailViewModel(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {

    fun getDetailMovieById(id: Int) = movieUseCase.getDetailMovieById(id).asLiveData()

    fun setFavoriteMovie(movie: Movie) = movieUseCase.setFavoriteMovie(movie)

    fun getDetailTvShowById(id: Int) = tvShowUseCase.getDetailTvShowById(id).asLiveData()

    fun setFavoriteTvShow(tvShow: TvShow) = tvShowUseCase.setFavoriteTvShow(tvShow)

    fun getDetailMovieByIdFromNetwork(id: Int) =
        movieUseCase.getDetailMovieByIdFromNetwork(id).asLiveData()

    fun getDetailTvShowByIdFromNetwork(id: Int) =
        tvShowUseCase.getDetailTvShowByIdFromNetwork(id).asLiveData()
}