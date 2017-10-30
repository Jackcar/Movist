package com.jacksonueda.movist.base

import android.support.v4.app.Fragment

/**
 * Created by Jackson on 13/07/17.
 */
interface BaseMvpViewFragment : BaseMvpView {

    fun fragment(): Fragment

}