package com.ahmaddudayef.moviesmade.core.di

import androidx.room.Room
import com.ahmaddudayef.moviesmade.core.BuildConfig
import com.ahmaddudayef.moviesmade.core.data.local.LocalDataSource
import com.ahmaddudayef.moviesmade.core.data.local.datastore.SettingPreferences
import com.ahmaddudayef.moviesmade.core.data.local.room.CatalogDatabase
import com.ahmaddudayef.moviesmade.core.data.remote.RemoteDataSource
import com.ahmaddudayef.moviesmade.core.data.remote.api.ApiService
import com.ahmaddudayef.moviesmade.core.data.repository.MovieRepository
import com.ahmaddudayef.moviesmade.core.data.repository.SettingRepository
import com.ahmaddudayef.moviesmade.core.data.repository.TvShowRepository
import com.ahmaddudayef.moviesmade.core.data.source.SettingDataSource
import com.ahmaddudayef.moviesmade.core.domain.repository.IMovieRepository
import com.ahmaddudayef.moviesmade.core.domain.repository.ITvShowRepository
import com.ahmaddudayef.moviesmade.core.util.AppExecutors
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single { GsonBuilder().setLenient().create() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("catalog".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            CatalogDatabase::class.java,
            "catalog-movies-db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
    factory { get<CatalogDatabase>().movieDao() }
    factory { get<CatalogDatabase>().tvShowDao() }
    single {
        SettingPreferences(androidContext())
    }
}

val networkModule = module {
    single(named("logging")) {
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        } as Interceptor
    }

    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/oD/WAoRPvbez1Y2dfYfuo4yujAcYHXdv1Ivb2v2MOKk=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>(named("logging")))
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get() as Gson))
            .client(get() as OkHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}

val repositoryModule = module {

    single { LocalDataSource(get(), get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> { MovieRepository(get(), get(), get()) }
    single<ITvShowRepository> { TvShowRepository(get(), get(), get()) }
    single<SettingDataSource> { SettingRepository(get()) }

}