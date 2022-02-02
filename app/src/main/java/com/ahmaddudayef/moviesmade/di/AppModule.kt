package com.ahmaddudayef.moviesmade.di

import androidx.room.Room
import com.ahmaddudayef.moviesmade.data.local.FavoriteDatabase
import com.ahmaddudayef.moviesmade.data.local.SettingPreferences
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single { GsonBuilder().setLenient().create() }
    single {
        Room.databaseBuilder(androidContext(), FavoriteDatabase::class.java, "favorite-db").build()
    }
    single {
        SettingPreferences(androidContext())
    }
}