package com.jacksonueda.movist.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//import com.pushtorefresh.storio2.sqlite.annotations.StorIOSQLiteColumn
//import com.pushtorefresh.storio2.sqlite.annotations.StorIOSQLiteCreator
//import com.pushtorefresh.storio2.sqlite.annotations.StorIOSQLiteType

/**
 * Created by Jackson on 28/10/17.
 */
//@StorIOSQLiteType(table = "movie")
class Movie
//@StorIOSQLiteCreator
constructor(
        @SerializedName("vote_count")
//        @StorIOSQLiteColumn(name = "voteCount")
        val voteCount: Int,

        @SerializedName("id")
        val id: Int,

        @SerializedName("video")
        val video: Boolean,

        @SerializedName("vote_average")
        val voteAverage: Float,

        @SerializedName("title")
        val title: String,

        @SerializedName("popularity")
        val popularity: Double,

        @SerializedName("poster_path")
        val posterPath: String,

        @SerializedName("backdrop_path")
        val backdropPath: String,

        @SerializedName("overview")
        val overview: String,

        @SerializedName("release_date")
        val releaseDate: String
) : Serializable {
    class Response(
            @SerializedName("page")
            val page: Long,

            @SerializedName("total_results")
            val totalResults: Long,

            @SerializedName("total_pages")
            val totalPages: Long,

            @SerializedName("results")
            val results: List<Movie>
    )
}
