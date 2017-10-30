package com.jacksonueda.movist.features.movieDetails

import com.jacksonueda.movist.base.BaseMvpPresenter
import com.jacksonueda.movist.base.BaseMvpViewFragment
import com.jacksonueda.movist.model.Movie

/**
 * Created by Jackson on 13/07/17.
 */

object MovieDetailsContract {

    interface View : BaseMvpViewFragment {
        fun showLoading()
        fun dismissLoading()
        fun displayMovieDetails(movie: Movie)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun getMovie(movieId: Long)
    }

}