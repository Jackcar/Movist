package com.jacksonueda.movist.data.local

import android.content.ContentUris
import android.net.Uri
import android.provider.BaseColumns
import io.reactivex.annotations.NonNull

/**
 * Created by Jackson on 02/11/17.
 */
class DatabaseContract {

    companion object {
        const val CONTENT_AUTHORITY = "com.jacksonueda.movist"
        const val CONTENT_SCHEME = "content://"
        private val BASE_CONTENT_URI = Uri.parse(CONTENT_SCHEME + CONTENT_AUTHORITY)

        const val CURSOR_TYPE = "vnd.android.cursor.dir/"
        const val CURSOR_ITEM_TYPE = "vnd.android.cursor.item/"

        const val PATH_GENRES = "genres"
        const val PATH_MOVIES = "movies"
    }

    object Movies : BaseColumns {
        @NonNull
        const val CONTENT_URI_STRING = CONTENT_SCHEME + CONTENT_AUTHORITY + "/" + PATH_MOVIES
        val CONTENT_URI = Uri.parse(CONTENT_URI_STRING)

        val CONTENT_TYPE = CURSOR_TYPE + CONTENT_AUTHORITY + "/" + PATH_MOVIES
        val CONTENT_ITEM_TYPE = CURSOR_ITEM_TYPE + CONTENT_AUTHORITY + "/" + PATH_MOVIES

        const val TABLE_NAME = "movies"

        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_VOTE_COUNT = "vote_count"
        const val COLUMN_VOTE_AVERAGE = "vote_average"
        const val COLUMN_POPULARITY = "popularity"
        const val COLUMN_POSTER_PATH = "poster_path"
        const val COLUMN_BACKDROP_PATH = "backdrop_path"
        const val COLUMN_OVERVIEW = "overview"
        const val COLUMN_RELEASE_DATE = "release_date"
        const val COLUMN_STATUS = "status"
        //        val COLUMN_GENRES = "genres"
        const val COLUMN_FAVORITE = "saveMovie"

        fun createQuery() = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INT NOT NULL PRIMARY KEY, " +
                COLUMN_TITLE + " TEXT NOT NULL, " +
                COLUMN_VOTE_COUNT + " INT, " +
                COLUMN_VOTE_AVERAGE + " REAL, " +
                COLUMN_POPULARITY + " REAL, " +
                COLUMN_POSTER_PATH + " TEXT, " +
                COLUMN_BACKDROP_PATH + " TEXT, " +
                COLUMN_OVERVIEW + " TEXT, " +
                COLUMN_RELEASE_DATE + " TEXT, " +
                COLUMN_STATUS + " TEXT, " +
                COLUMN_FAVORITE + " INTEGER NOT NULL DEFAULT 0, " +
//                COLUMN_GENRES + " TEXT, " +
                "UNIQUE (" + COLUMN_ID + ") ON CONFLICT REPLACE)"

        fun deleteQuery() = "DROP TABLE IF EXISTS " + TABLE_NAME

        fun buildUri(id: Long) = ContentUris.withAppendedId(CONTENT_URI, id)
    }

    object Genres : BaseColumns {
        @NonNull
        const val CONTENT_URI_STRING = CONTENT_SCHEME + CONTENT_AUTHORITY + "/" + PATH_GENRES
        val CONTENT_URI = Uri.parse(CONTENT_URI_STRING)

        val CONTENT_TYPE = CURSOR_TYPE + CONTENT_AUTHORITY + "/" + PATH_GENRES
        val CONTENT_ITEM_TYPE = CURSOR_ITEM_TYPE + CONTENT_AUTHORITY + "/" + PATH_GENRES

        const val TABLE_NAME = "genres"

        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"

        fun createQuery() = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INT NOT NULL PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                "UNIQUE (" + COLUMN_ID + ") ON CONFLICT REPLACE)"

        fun deleteQuery() = "DROP TABLE IF EXISTS " + TABLE_NAME

        fun buildUri(id: Long) = ContentUris.withAppendedId(CONTENT_URI, id)
    }

}