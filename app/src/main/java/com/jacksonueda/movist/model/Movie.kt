package com.jacksonueda.movist.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.jacksonueda.movist.data.local.DatabaseContract
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Jackson on 28/10/17.
 */

@Entity(tableName = DatabaseContract.Movies.TABLE_NAME)
class Movie() : Serializable {

    @ColumnInfo(name = DatabaseContract.Movies.COLUMN_ID)
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: Int = 0

    @ColumnInfo(name = DatabaseContract.Movies.COLUMN_VOTE_COUNT)
    @SerializedName("vote_count")
    var voteCount: Int = 0

    @ColumnInfo(name = DatabaseContract.Movies.COLUMN_VOTE_AVERAGE)
    @SerializedName("vote_average")
    var voteAverage: Float = 0.0f

    @ColumnInfo(name = DatabaseContract.Movies.COLUMN_TITLE)
    @SerializedName("title")
    var title: String = ""

    @ColumnInfo(name = DatabaseContract.Movies.COLUMN_POPULARITY)
    @SerializedName("popularity")
    var popularity: Double  = 0.0

    @ColumnInfo(name = DatabaseContract.Movies.COLUMN_POSTER_PATH)
    @SerializedName("poster_path")
    var posterPath: String = ""

    @ColumnInfo(name = DatabaseContract.Movies.COLUMN_BACKDROP_PATH)
    @SerializedName("backdrop_path")
    var backdropPath: String = ""

    @ColumnInfo(name = DatabaseContract.Movies.COLUMN_OVERVIEW)
    @SerializedName("overview")
    var overview: String = ""

    @ColumnInfo(name = DatabaseContract.Movies.COLUMN_RELEASE_DATE)
    @SerializedName("release_date")
    var releaseDate: String = ""

    @ColumnInfo(name = DatabaseContract.Movies.COLUMN_STATUS)
    @SerializedName("status")
    var status: String = ""

    @ColumnInfo(name = DatabaseContract.Movies.COLUMN_FAVORITE)
    var favorite: Boolean = false

    @Ignore
    @SerializedName("genres")
    var genres: List<Genre> = listOf()

    constructor(id: Int, voteCount: Int, voteAverage: Float, title: String, popularity: Double, posterPath: String,
                backdropPath: String, overview: String, releaseDate: String, status: String, favorite: Boolean) : this() {
        this.id = id
        this.voteCount = voteCount
        this.voteAverage = voteAverage
        this.title = title
        this.popularity = popularity
        this.posterPath = posterPath
        this.backdropPath = backdropPath
        this.overview = overview
        this.releaseDate = releaseDate
        this.status = status
        this.favorite = favorite
    }

    constructor(id: Int, voteCount: Int, voteAverage: Float, title: String, popularity: Double, posterPath: String,
                backdropPath: String, overview: String, releaseDate: String, status: String, favorite: Boolean,
                genres: List<Genre>) : this() {
        this.id = id
        this.voteCount = voteCount
        this.voteAverage = voteAverage
        this.title = title
        this.popularity = popularity
        this.posterPath = posterPath
        this.backdropPath = backdropPath
        this.overview = overview
        this.releaseDate = releaseDate
        this.status = status
        this.favorite = favorite
        this.genres = genres
    }

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

    fun genreListAsString() = genres.joinToString(" / ", transform = { genre -> genre.name })
}

