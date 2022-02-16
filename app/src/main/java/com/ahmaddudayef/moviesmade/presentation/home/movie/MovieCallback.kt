package com.ahmaddudayef.moviesmade.presentation.home.movie

import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity

interface MovieCallback {
    fun onItemClicked(data: MovieEntity)
}