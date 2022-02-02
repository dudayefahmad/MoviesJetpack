package com.ahmaddudayef.moviesmade.di

import com.ahmaddudayef.moviesmade.presentation.detailtvshow.DetailTvShowViewModel
import com.ahmaddudayef.moviesmade.presentation.detaimovie.DetailMovieViewModel
import com.ahmaddudayef.moviesmade.presentation.favorite.moviefavorite.MovieFavoriteViewModel
import com.ahmaddudayef.moviesmade.presentation.favorite.tvshowfavorite.TvShowFavoriteViewModel
import com.ahmaddudayef.moviesmade.presentation.home.HomeViewModel
import com.ahmaddudayef.moviesmade.presentation.home.movie.MovieViewModel
import com.ahmaddudayef.moviesmade.presentation.home.tvshow.TvShowViewModel
import com.ahmaddudayef.moviesmade.presentation.search.SearchViewModel
import com.ahmaddudayef.moviesmade.presentation.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { MovieFavoriteViewModel(get()) }
    viewModel { TvShowFavoriteViewModel(get()) }
    viewModel { DetailTvShowViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { SettingViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}