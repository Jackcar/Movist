package com.jacksonueda.movist.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.jacksonueda.movist.data.AppDataStore
import com.jacksonueda.movist.model.*
import io.reactivex.Observable
import org.jetbrains.anko.doAsync

/**
 * Created by Jackson on 21/07/17.
 */
@Database(entities = arrayOf(Movie::class, Genre::class), version = 2, exportSchema = false)
abstract class AppLocalDataStore : AppDataStore, RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao

    override fun movie(movieId: Int): Observable<Movie> {
        return movieDao().movie(movieId).toObservable()
    }

    override fun movies(page: Int): Observable<List<Movie>> {
        return movieDao().movies().toObservable()
    }

    fun saveMovies(movies: List<Movie>) {
        doAsync {
            for (movie in movies)
                movieDao().insertMovie(movie)
        }
    }

    fun saveMovie(movie: Movie) {
        doAsync {
            movieDao().insertMovie(movie)
        }
    }

    fun updateMovie(movie: Movie) {
        doAsync {
            movieDao().updateMovie(movie)
        }
    }

}