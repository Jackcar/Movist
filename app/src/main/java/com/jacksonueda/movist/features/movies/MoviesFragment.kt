package com.jacksonueda.movist.features.movies

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.jacksonueda.movist.base.BaseMvpFragment
import com.jacksonueda.movist.R
import com.jacksonueda.movist.utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_movies.*
import android.support.v7.widget.LinearLayoutManager
import com.jacksonueda.movist.features.movieDetails.MovieDetailsActivity
import com.jacksonueda.movist.model.Movie
import org.jetbrains.anko.support.v4.startActivity


/**
 * Created by Jackson on 13/07/17.
 */
class MoviesFragment : BaseMvpFragment<MoviesContract.View, MoviesContract.Presenter>(), MoviesContract.View {

    private lateinit var mMoviesAdapter: MoviesAdapter

    override var mPresenter: MoviesContract.Presenter = MoviesPresenter()

    // ==========================================================================================
    // LIFECYCLE
    // ==========================================================================================

    override fun layout(): Int {
        return R.layout.fragment_movies
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        mPresenter.getMovies(1)
    }

    // ==========================================================================================
    // SETUP
    // ==========================================================================================

    private fun setupRecyclerView() {
        // Setup layout manager
        val linearLayoutManager = LinearLayoutManager(context)
        moviesRecyclerView.layoutManager = linearLayoutManager
        moviesRecyclerView.addOnScrollListener(EndlessRecyclerViewScrollListener(
                { page -> mPresenter.getMovies(page) }, linearLayoutManager))

        // Set adapter to recycler view
        mMoviesAdapter = MoviesAdapter(ArrayList(0)) { movie ->
            // Execute onClick
            startActivity<MovieDetailsActivity>(MovieDetailsActivity.EXTRA_MOVIE to movie)
        }
        moviesRecyclerView.adapter = mMoviesAdapter
    }

    // ==========================================================================================
    // VIEW
    // ==========================================================================================

    override fun displayMovies(movies: List<Movie>) {
        mMoviesAdapter.add(movies)
    }

    override fun showLoading() {
        moviesProgressBar.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        moviesProgressBar.visibility = View.GONE
    }

}