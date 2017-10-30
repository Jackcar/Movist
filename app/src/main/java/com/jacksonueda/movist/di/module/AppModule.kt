package com.jacksonueda.movist.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Jackson on 20/07/17.
 */
@Module
class AppModule(var mApplication: Application) {

    @Provides
    @Singleton
    fun provideApplication() = mApplication
}