package com.jacksonueda.movist.data.remote

import com.jacksonueda.movist.model.Movie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import com.jacksonueda.movist.model.Sort
import com.jacksonueda.movist.model.Video
import retrofit2.http.Path


/**
 * Created by Jackson on 28/10/17.
 */
interface RestApi {

    @GET("discover/movie")
    fun discoverMovies(@Query("sort_by") sort: String,
                       @Query("page") page: Int): Observable<Movie.Response>

    @GET("movie/{id}")
    fun movie(@Path("id") movieId: Int): Observable<Movie>

    @GET("movie/{id}/videos")
    fun videos(@Path("id") movieId: Int): Observable<Video.Response>

}