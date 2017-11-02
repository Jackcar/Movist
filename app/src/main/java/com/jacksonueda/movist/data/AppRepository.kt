package com.jacksonueda.movist.data

import io.reactivex.Observable
import com.jacksonueda.movist.data.Local.AppLocalDataStore
import com.jacksonueda.movist.data.remote.AppRemoteDataStore
import com.jacksonueda.movist.model.Movie
import com.jacksonueda.movist.model.Video
import javax.inject.Inject


/**
 * Created by Jackson on 23/07/17.
 */

class AppRepository @Inject constructor(val mAppLocalDataStore: AppLocalDataStore,
                                        val mAppRemoteDataStore: AppRemoteDataStore)
    : AppDataStore {

    override fun movie(movieId: Int): Observable<Movie> {
        return mAppRemoteDataStore.movie(movieId)
    }

    override fun movies(page: Int): Observable<List<Movie>> {
        return mAppRemoteDataStore.movies(page)
//        return Observable.concat(mAppLocalDataStore.movies(page), mAppRemoteDataStore.movies(page))
//                .first(listOf())
//                .toObservable()
    }

    fun videos(movieId: Int): Observable<List<Video>> {
        return mAppRemoteDataStore.videos(movieId)
    }

}