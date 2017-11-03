package com.jacksonueda.movist.data.local

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jacksonueda.movist.model.Movie
import io.reactivex.Flowable

/**
 * Created by Jackson on 03/11/17.
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun movies() : Flowable<List<Movie>>

    @Query("SELECT * FROM movies WHERE id = :arg0")
    fun movie(id: Int) : Flowable<Movie>

    @Insert(onConflict = REPLACE)
    fun insertMovie(movie: Movie)

    @Update(onConflict = REPLACE)
    fun updateMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

}