package com.ahmaddudayef.moviesmade.presentation.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity
import com.ahmaddudayef.moviesmade.data.source.MovieDataSource
import com.ahmaddudayef.moviesmade.vo.Resource

class MovieViewModel(private val movieRepository: MovieDataSource) : ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return movieRepository.getAllMovies()
    }
}