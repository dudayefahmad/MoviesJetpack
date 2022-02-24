package com.ahmaddudayef.moviesmade.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ahmaddudayef.moviesmade.core.domain.usecase.MovieUseCase

class MovieFavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getFavoriteMovie() = movieUseCase.getAllFavoriteMovies().asLiveData()

}