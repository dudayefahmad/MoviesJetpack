package com.ahmaddudayef.moviesmade

import android.app.Application
import com.ahmaddudayef.moviesmade.di.appModule
import com.ahmaddudayef.moviesmade.di.networkModule
import com.ahmaddudayef.moviesmade.di.repositoryModule
import com.ahmaddudayef.moviesmade.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(arrayListOf(appModule, viewModelModule, repositoryModule, networkModule))
        }
    }
}