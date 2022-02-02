package com.ahmaddudayef.moviesmade.di

import com.ahmaddudayef.moviesmade.data.repository.*
import com.ahmaddudayef.moviesmade.data.source.*
import org.koin.dsl.module

val repositoryModule = module {

    single { MovieRepository(get()) as MovieDataSource }
    single { TvShowRepository(get()) as TvShowDataSource }
    single { MovieFavoriteRepository(get()) as MovieFavoriteDataSource }
    single { TvShowFavoriteRepository(get()) as TvShowFavoriteDataSource }
    single { SearchRepository(get()) as SearchDataSource }
    single { SettingRepository(get()) as SettingDataSource }
}