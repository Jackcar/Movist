package com.jacksonueda.movist.data.local

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.content.UriMatcher
import android.database.SQLException

/**
 * Created by Jackson on 02/11/17.
 */
class Provider : ContentProvider() {

    private val MOVIES = 100
    private val MOVIE_ID = 101

    private val GENRES = 200

    private val sUriMatcher = buildUriMatcher()
    private lateinit var mDbHelper: DatabaseHelper

    private fun buildUriMatcher(): UriMatcher {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        val authority = DatabaseContract.CONTENT_AUTHORITY

        matcher.addURI(authority, DatabaseContract.PATH_MOVIES, MOVIES)
        matcher.addURI(authority, DatabaseContract.PATH_MOVIES + "/*", MOVIE_ID)

        matcher.addURI(authority, DatabaseContract.PATH_GENRES, GENRES)

        return matcher
    }

    override fun onCreate(): Boolean {
        mDbHelper = DatabaseHelper(context)
        return true
    }

    override fun query(uri: Uri?, projection: Array<out String>?, selection: String?,
                       selectionArgs: Array<out String>?, sortOrder: String?): Cursor {
        val cursor: Cursor
        when (sUriMatcher.match(uri)) {
            MOVIES -> cursor = mDbHelper.readableDatabase.query(
                    DatabaseContract.Movies.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null, null,
                    sortOrder)
            MOVIE_ID -> cursor = mDbHelper.readableDatabase.query(
                    DatabaseContract.Movies.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null, null,
                    sortOrder)
            GENRES -> cursor = mDbHelper.readableDatabase.query(
                    DatabaseContract.Genres.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null, null,
                    sortOrder)
            else -> throw UnsupportedOperationException("Unknown uri " + uri)
        }
        cursor.setNotificationUri(context.contentResolver, uri)
        return cursor
    }

    override fun getType(uri: Uri) = when (sUriMatcher.match(uri)) {
        MOVIES -> DatabaseContract.Movies.CONTENT_TYPE
        MOVIE_ID -> DatabaseContract.Movies.CONTENT_ITEM_TYPE
        GENRES -> DatabaseContract.Movies.CONTENT_TYPE
        else -> throw UnsupportedOperationException("Unknown URI " + uri);
    }

    override fun insert(uri: Uri, contentValues: ContentValues): Uri? {
        val db = mDbHelper.writableDatabase
        var retUri: Uri? = null

        when (sUriMatcher.match(uri)) {
            MOVIES -> {
                val id: Long = db.insert(DatabaseContract.Movies.TABLE_NAME, null, contentValues)
                if (id > 0)
                    retUri = DatabaseContract.Movies.buildUri(id)
                else
                    throw SQLException("Failed to insert row " + uri)
            }
            GENRES -> {
                val id: Long = db.insert(DatabaseContract.Genres.TABLE_NAME, null, contentValues)
                if (id > 0)
                    retUri = DatabaseContract.Genres.buildUri(id)
                else
                    throw SQLException("Failed to insert row " + uri)
            }
        }
        context.contentResolver.notifyChange(uri, null)

        return retUri
    }


    override fun update(uri: Uri?, contentValues: ContentValues?, selection: String?,
                        selectionArgs: Array<out String>?): Int {
        val db = mDbHelper.writableDatabase
        val update: Int

        when (sUriMatcher.match(uri)) {
        //Case for User
            MOVIES -> update = db.update(DatabaseContract.Movies.TABLE_NAME, contentValues, selection, selectionArgs)
            GENRES -> update = db.update(DatabaseContract.Genres.TABLE_NAME, contentValues, selection, selectionArgs)
            else -> throw UnsupportedOperationException("Unknown URI " + uri)
        }

        if (update > 0)
            context!!.contentResolver.notifyChange(uri, null)

        return update
    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int {
        val db = mDbHelper.writableDatabase
        val rowsDeleted: Int

        when (sUriMatcher.match(uri)) {
            MOVIES -> rowsDeleted = db.delete(DatabaseContract.Movies.TABLE_NAME, selection, selectionArgs)
            GENRES -> rowsDeleted = db.delete(DatabaseContract.Genres.TABLE_NAME, selection, selectionArgs)
            else -> throw UnsupportedOperationException("Unknown URI " + uri)
        }

        if (selection != null && rowsDeleted > 0)
            context.contentResolver.notifyChange(uri, null)

        return rowsDeleted
    }

}