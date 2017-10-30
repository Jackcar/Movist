package com.jacksonueda.movist.di.component

import com.jacksonueda.movist.di.module.AppModule
import com.jacksonueda.movist.di.module.DataModule
import com.jacksonueda.movist.data.remote.AppRemoteDataStore
import com.jacksonueda.movist.features.movies.MoviesPresenter
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Jackson on 21/07/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, DataModule::class))
interface AppComponent {
    fun inject(moviesPresenter: MoviesPresenter)
    fun inject(appRemoteDataStore: AppRemoteDataStore)
}