package com.ahmaddudayef.moviesmade.home.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ahmaddudayef.moviesmade.core.domain.usecase.MovieUseCase

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getMovies() = movieUseCase.getAllMovies().asLiveData()
}