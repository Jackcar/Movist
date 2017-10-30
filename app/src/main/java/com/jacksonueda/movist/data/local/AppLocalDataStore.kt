package com.jacksonueda.movist.data.Local

import android.app.Application
import com.jacksonueda.movist.data.AppDataStore
import com.jacksonueda.movist.model.Movie
import io.reactivex.Observable

/**
 * Created by Jackson on 21/07/17.
 */
class AppLocalDataStore(context: Application) : AppDataStore {

    override fun movie(movieId: Long): Observable<Movie> {
        return Observable.just(
                Movie(0,0 , false, 0.0f, "", 0.0, "", "","", "")
        )
    }

    override fun movies(page: Int): Observable<List<Movie>> {
        return Observable.just(listOf())
    }

    fun saveMovies(movies: List<Movie>) {

    }

    fun saveMovie(movie: Movie) {

    }

}