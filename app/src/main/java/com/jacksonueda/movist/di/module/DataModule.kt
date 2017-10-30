package com.jacksonueda.movist.di.module

import android.app.Application
import com.jacksonueda.movist.BuildConfig
import com.jacksonueda.movist.data.Local.AppLocalDataStore
import com.jacksonueda.movist.data.remote.AppRemoteDataStore
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor


/**
 * Created by Jackson on 20/07/17.
 */
@Module
class DataModule {

    private val apiKey: String = "api_key"

    @Provides
    @Singleton
    fun provideHttpCache(application: Application): Cache {
        val cacheSize: Long = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor { chain ->
                    val original = chain.request()
                    val originalUrl = original.url()

                    val newUrl = originalUrl.newBuilder()
                            .addQueryParameter(apiKey, BuildConfig.MOVIE_DB_API_KEY)
                            .build()

                    val requestBuilder = original.newBuilder().url(newUrl)

                    chain.proceed(requestBuilder.build())
                }
                .cache(cache)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun providesAppLocalDataStore(context: Application) = AppLocalDataStore(context)

    @Provides
    @Singleton
    fun providesRepository() = AppRemoteDataStore()

}