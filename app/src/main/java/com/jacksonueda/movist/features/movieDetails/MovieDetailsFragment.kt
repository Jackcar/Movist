package com.jacksonueda.movist.features.movieDetails

import android.os.Bundle
import android.view.View
import com.jacksonueda.movist.R
import com.jacksonueda.movist.base.BaseMvpFragment
import com.jacksonueda.movist.features.movies.MoviesAdapter
import com.jacksonueda.movist.features.movies.MoviesContract
import com.jacksonueda.movist.features.movies.MoviesPresenter
import com.jacksonueda.movist.model.Movie
import com.jacksonueda.movist.utils.Utils
import kotlinx.android.synthetic.main.fragment_movie_details.*

/**
 * Created by Jackson on 13/07/17.
 */
class MovieDetailsFragment : BaseMvpFragment<MoviesContract.View, MoviesContract.Presenter>(), MoviesContract.View {

    companion object {
        const val EXTRA_MOVIE: String = "EXTRA_MOVIE"
    }

    private lateinit var mMoviesAdapter: MoviesAdapter

    override var mPresenter: MoviesContract.Presenter = MoviesPresenter()

    var mMovie: Movie? = null

    // ==========================================================================================
    // LIFECYCLE
    // ==========================================================================================

    override fun layout(): Int {
        return R.layout.fragment_movie_details
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mMovie = arguments.get(EXTRA_MOVIE) as Movie
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Utils.loadImage(context.getString(R.string.tmdb_background_url) + mMovie?.backdropPath, activity, moviePoster)
    }

    // ==========================================================================================
    // SETUP
    // ==========================================================================================


    // ==========================================================================================
    // ACTIONS
    // ==========================================================================================

    // ==========================================================================================
    // VIEW
    // ==========================================================================================

    override fun displayMovies(movies: List<Movie>) {
        mMoviesAdapter.add(movies)
    }

    override fun showLoading() {
//        moviesProgressBar.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
//        moviesProgressBar.visibility = View.GONE
    }

}