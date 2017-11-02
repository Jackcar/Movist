package com.jacksonueda.movist.model

import android.icu.text.DateFormat
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

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
//        @StorIOSQLiteColumn(name = "voteCount")
        @SerializedName("vote_count")
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
        val releaseDate: String,

        @SerializedName("status")
        val status: String,

        @SerializedName("genres")
        val genres: List<Genre>
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

    fun formattedDate(): String {
        var formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = formatter.parse(releaseDate)

        formatter = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
        return formatter.format(date)
    }

    fun genreListAsString(): String {
        return genres.joinToString(" / ", transform = { genre -> genre.name })
    }
}
