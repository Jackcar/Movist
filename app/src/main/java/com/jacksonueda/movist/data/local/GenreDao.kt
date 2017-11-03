package com.jacksonueda.movist.data.local

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.jacksonueda.movist.model.Genre
import io.reactivex.Flowable

/**
 * Created by Jackson on 03/11/17.
 */
@Dao
interface GenreDao {

    @Query("SELECT * FROM genres")
    fun genres() : Flowable<List<Genre>>

    @Query("SELECT * FROM genres WHERE id = :arg0")
    fun genre(id: Int) : Flowable<Genre>

    @Insert(onConflict = REPLACE)
    fun insertGenre(genre: Genre)

    @Update(onConflict = REPLACE)
    fun updateGenre(genre: Genre)

    @Delete
    fun deleteGenre(genre: Genre)

}