package com.jacksonueda.movist.data

import com.jacksonueda.movist.model.Movie
import io.reactivex.Observable

/**
 * Created by Jackson on 20/07/17.
 */
interface AppDataStore {
    fun movies(page: Int): Observable<List<Movie>>
    fun movie(movieId: Long): Observable<Movie>
}