package com.jacksonueda.movist.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * Created by Jackson on 11/07/17.
 */

abstract class BaseMvpFragment<in V : BaseMvpViewFragment, T : BaseMvpPresenter<V>>
    : Fragment(), BaseMvpViewFragment {

    protected abstract var mPresenter: T

    abstract fun layout(): Int

    // ==========================================================================================
    // LIFECYCLE
    // ==========================================================================================

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(layout(), container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    // ==========================================================================================
    // HELPERS
    // ==========================================================================================

    override fun context() = activity

    override fun fragment() = this

    override fun showMessage(srtResId: Int) {
        Toast.makeText(activity, srtResId, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
}