package com.ahmaddudayef.moviesmade.favorite.di

import com.ahmaddudayef.moviesmade.favorite.movie.MovieFavoriteViewModel
import com.ahmaddudayef.moviesmade.favorite.tvshow.TvShowFavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { MovieFavoriteViewModel(get()) }
    viewModel { TvShowFavoriteViewModel(get()) }
}