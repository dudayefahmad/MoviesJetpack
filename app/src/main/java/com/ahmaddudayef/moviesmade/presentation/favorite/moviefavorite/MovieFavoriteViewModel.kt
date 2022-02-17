package com.ahmaddudayef.moviesmade.presentation.favorite.moviefavorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity
import com.ahmaddudayef.moviesmade.data.source.MovieDataSource

class MovieFavoriteViewModel(
    private val movieRepository: MovieDataSource
) : ViewModel() {

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        return movieRepository.getAllFavoriteMovies()
    }
}