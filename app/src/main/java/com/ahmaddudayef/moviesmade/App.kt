package com.ahmaddudayef.moviesmade

import android.app.Application
import com.ahmaddudayef.moviesmade.core.di.appModule
import com.ahmaddudayef.moviesmade.core.di.networkModule
import com.ahmaddudayef.moviesmade.core.di.repositoryModule
import com.ahmaddudayef.moviesmade.di.useCaseModule
import com.ahmaddudayef.moviesmade.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(arrayListOf(appModule, viewModelModule, repositoryModule, networkModule, useCaseModule))
        }
    }
}