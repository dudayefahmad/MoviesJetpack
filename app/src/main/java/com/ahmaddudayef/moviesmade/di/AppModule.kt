package com.ahmaddudayef.moviesmade.di

import androidx.room.Room
import com.ahmaddudayef.moviesmade.data.local.datastore.SettingPreferences
import com.ahmaddudayef.moviesmade.data.local.room.CatalogDatabase
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single { GsonBuilder().setLenient().create() }
    factory { get<CatalogDatabase>().movieDao() }
    factory { get<CatalogDatabase>().tvShowDao() }
    single {
        Room.databaseBuilder(androidContext(), CatalogDatabase::class.java, "catalog-db").build()
    }
    single {
        SettingPreferences(androidContext())
    }
}