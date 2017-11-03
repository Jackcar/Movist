package com.jacksonueda.movist.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Jackson on 02/11/17.
 */
class DatabaseHelper : SQLiteOpenHelper {

    companion object {
        val DATABASE_NAME = "Movist.db"
        val DATABASE_VERSION = 1
    }

    private var mContext: Context

    constructor(context: Context) : super(context, DATABASE_NAME, null, DATABASE_VERSION) {
        this.mContext = context
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseContract.Movies.createQuery())
        sqLiteDatabase.execSQL(DatabaseContract.Genres.createQuery())
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, p1: Int, p2: Int) {
        sqLiteDatabase.execSQL(DatabaseContract.Movies.deleteQuery())
        sqLiteDatabase.execSQL(DatabaseContract.Genres.deleteQuery())
        onCreate(sqLiteDatabase)
    }

}