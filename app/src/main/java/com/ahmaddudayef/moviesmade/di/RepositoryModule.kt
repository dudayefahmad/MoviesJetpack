package com.ahmaddudayef.moviesmade.di

import com.ahmaddudayef.moviesmade.data.local.LocalDataSource
import com.ahmaddudayef.moviesmade.data.remote.RemoteDataSource
import com.ahmaddudayef.moviesmade.data.repository.MovieRepository
import com.ahmaddudayef.moviesmade.data.repository.SettingRepository
import com.ahmaddudayef.moviesmade.data.repository.TvShowRepository
import com.ahmaddudayef.moviesmade.data.source.MovieDataSource
import com.ahmaddudayef.moviesmade.data.source.SettingDataSource
import com.ahmaddudayef.moviesmade.data.source.TvShowDataSource
import com.ahmaddudayef.moviesmade.util.AppExecutors
import org.koin.dsl.module

val repositoryModule = module {

    single { LocalDataSource(get(), get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single { MovieRepository(get(), get(), get()) as MovieDataSource }
    single { TvShowRepository(get(), get(), get()) as TvShowDataSource }
    single { SettingRepository(get()) as SettingDataSource }

}