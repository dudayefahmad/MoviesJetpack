package com.ahmaddudayef.moviesmade.presentation.favorite.moviefavorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity
import com.ahmaddudayef.moviesmade.data.repository.MovieRepository

class MovieFavoriteViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        return movieRepository.getAllFavoriteMovies()
    }
}