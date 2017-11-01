package com.jacksonueda.movist.base

import android.content.Context
import android.support.annotation.StringRes

/**
 * Created by Jackson on 28/10/17.
 */
interface BaseMvpView {

    fun context(): Context

    fun showMessage(@StringRes srtResId: Int)

    fun showMessage(message: String)

}