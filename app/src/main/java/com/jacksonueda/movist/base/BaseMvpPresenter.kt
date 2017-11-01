package com.jacksonueda.movist.base

/**
 * Created by Jackson on 28/10/17.
 */
interface BaseMvpPresenter<in V : BaseMvpView> {

    fun attachView(view: V)

    fun detachView()

}