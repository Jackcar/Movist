package com.jacksonueda.movist.data.remote

import com.jacksonueda.movist.App
import com.jacksonueda.movist.data.AppDataStore
import com.jacksonueda.movist.data.local.AppLocalDataStore
import com.jacksonueda.movist.model.Movie
import com.jacksonueda.movist.model.Video
import io.reactivex.Observable
import io.reactivex.rxkotlin.withLatestFrom
import io.reactivex.rxkotlin.zipWith
import javax.inject.Inject
import retrofit2.Retrofit

/**
 * Created by Jackson on 20/07/17.
 */
class AppRemoteDataStore : AppDataStore {

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var appLocalDataStore: AppLocalDataStore

    init {
        App.appComponent().inject(this)
    }

    override fun movies(page: Int): Observable<List<Movie>> {
        return retrofit.create(RestApi::class.java).discoverMovies("popularity.desc", page)
                .retry(2)
                .map { response -> response.results }
                .doOnNext { movies ->
                    appLocalDataStore.saveMovies(movies)
                }
    }

    override fun movie(movieId: Int): Observable<Movie> {
        return retrofit.create(RestApi::class.java).movie(movieId)
                .retry(2)
                .doOnNext { movie ->
                    appLocalDataStore.saveMovie(movie)
                }
    }

    fun videos(movieId: Int): Observable<List<Video>> {
        return retrofit.create(RestApi::class.java).videos(movieId)
                .retry(2)
                .map { response -> response.results }
    }

}
