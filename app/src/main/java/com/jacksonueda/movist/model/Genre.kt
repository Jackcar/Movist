package com.jacksonueda.movist.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Jackson on 28/10/17.
 */
//@StorIOSQLiteType(table = "movie")
class Genre
//@StorIOSQLiteCreator
constructor(
//        @StorIOSQLiteColumn(name = "id")
        val id: Int,
        val name: String
) : Serializable