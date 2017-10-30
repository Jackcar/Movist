package com.jacksonueda.movist.features.movies

import com.jacksonueda.movist.base.BaseMvpPresenter
import com.jacksonueda.movist.base.BaseMvpViewFragment
import com.jacksonueda.movist.model.Movie

/**
 * Created by Jackson on 13/07/17.
 */

object MoviesContract {

    interface View : BaseMvpViewFragment {
        fun showLoading()
        fun dismissLoading()
        fun displayMovies(movies: List<Movie>)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun getMovies(page: Int)
    }

}