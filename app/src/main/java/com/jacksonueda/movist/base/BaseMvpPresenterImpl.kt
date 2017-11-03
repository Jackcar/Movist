package com.jacksonueda.movist.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Jackson on 28/10/17.
 */
open class BaseMvpPresenterImpl<V : BaseMvpView> : BaseMvpPresenter<V> {
    protected var mDisposable = CompositeDisposable()
    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
        mDisposable.clear()
    }

    override fun add(disposable: Disposable) {
        mDisposable.add(disposable)
    }

}