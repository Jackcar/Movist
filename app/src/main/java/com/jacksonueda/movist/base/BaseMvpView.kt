package com.jacksonueda.movist.base

import android.content.Context
import android.support.annotation.StringRes

/**
 * Created by Jackson on 13/07/17.
 */
interface BaseMvpView {

    fun context(): Context

    fun showMessage(@StringRes srtResId: Int)

    fun showMessage(message: String)

}