package com.jacksonueda.movist.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*

/**
 * Created by Jackson on 28/10/17.
 */
class Utils {
    companion object {

        @JvmStatic
        fun getTimeNow(): String {
            return Date().time.toString()
        }

        @JvmStatic
        fun getYear(date: String): String {
            return date.split("-")[0]
        }

        @JvmStatic
        fun loadImage(url: String?, context: Context, imageView: ImageView) {
            url.let {
                val options = RequestOptions().centerCrop()
                Glide.with(context)
                        .load(url)
                        .apply(options)
                        .into(imageView)
            }
        }

    }
}