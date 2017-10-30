package com.jacksonueda.movist.features.movieDetails

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.AttributeSet
import android.view.View
import com.jacksonueda.movist.R
import com.jacksonueda.movist.base.BaseActivity
import com.jacksonueda.movist.base.BaseMvpActivity
import com.jacksonueda.movist.features.movies.MoviesFragment
import com.jacksonueda.movist.model.Movie
import com.jacksonueda.movist.utils.Utils
import com.jacksonueda.movist.utils.replaceFragment
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsActivity : BaseMvpActivity<MovieDetailsContract.View, MovieDetailsContract.Presenter>(), MovieDetailsContract.View {

    override var mPresenter: MovieDetailsContract.Presenter = MovieDetailsPresenter()

    companion object {
        const val EXTRA_MOVIE: String = "EXTRA_MOVIE"
    }

    var mMovie: Movie? = null

    // ==========================================================================================
    // LIFECYCLE
    // ==========================================================================================

    override fun getLayout(): Int {
        return R.layout.fragment_movie_details
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mMovie = intent.getSerializableExtra(MovieDetailsFragment.EXTRA_MOVIE) as Movie

        // Start Signup fragment
//        replaceFragment(MoviesFragment(), R.id.frame_layout_holder)
        Utils.loadImage(getString(R.string.tmdb_background_url) + mMovie?.backdropPath, this, moviePoster)
    }

    override fun fragment(): Fragment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayMovieDetails(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}