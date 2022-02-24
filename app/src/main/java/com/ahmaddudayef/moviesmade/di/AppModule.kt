package com.ahmaddudayef.moviesmade.di

import com.ahmaddudayef.moviesmade.core.domain.usecase.MovieInteractor
import com.ahmaddudayef.moviesmade.core.domain.usecase.MovieUseCase
import com.ahmaddudayef.moviesmade.core.domain.usecase.TvShowInteractor
import com.ahmaddudayef.moviesmade.core.domain.usecase.TvShowUseCase
import com.ahmaddudayef.moviesmade.detail.DetailViewModel
import com.ahmaddudayef.moviesmade.home.HomeViewModel
import com.ahmaddudayef.moviesmade.home.movie.MovieViewModel
import com.ahmaddudayef.moviesmade.home.tvshow.TvShowViewModel
import com.ahmaddudayef.moviesmade.search.SearchViewModel
import com.ahmaddudayef.moviesmade.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
    factory<TvShowUseCase> { TvShowInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { SettingViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}