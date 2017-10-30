package com.jacksonueda.movist.model

import com.google.gson.annotations.SerializedName
//import com.pushtorefresh.storio2.sqlite.annotations.StorIOSQLiteColumn
//import com.pushtorefresh.storio2.sqlite.annotations.StorIOSQLiteCreator
//import com.pushtorefresh.storio2.sqlite.annotations.StorIOSQLiteType

/**
 * Created by Jackson on 28/10/17.
 */
//@StorIOSQLiteType(table = "video")
class Video
//@StorIOSQLiteCreator
constructor(
        @SerializedName("id")
        val id: Int,

        @SerializedName("key")
        val key: String,

        @SerializedName("name")
        val name: String,

        @SerializedName("site")
        val site: String,

        @SerializedName("type")
        val type: String
) {
    class Response(
            @SerializedName("id")
            val id: Long,

            @SerializedName("results")
            val results: ArrayList<Video>
    )
}