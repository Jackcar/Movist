package com.jacksonueda.movist.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Jackson on 28/10/17.
 */
class Video constructor(
        @SerializedName("id")
        val id: String,

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

    fun isFromYoutube(): Boolean {
        return site == "YouTube"
    }
}