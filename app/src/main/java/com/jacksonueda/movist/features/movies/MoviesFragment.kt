package com.jacksonueda.movist.features.movies

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import com.jacksonueda.movist.base.BaseMvpFragment
import com.jacksonueda.movist.R
import com.jacksonueda.movist.utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_movies.*
import android.support.v7.widget.LinearLayoutManager
import com.jacksonueda.movist.features.movieDetails.MovieDetailsActivity
import com.jacksonueda.movist.model.Movie
import kotlinx.android.synthetic.main.movie_item.view.*


/**
 * Created by Jackson on 28/10/17.
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
        mMoviesAdapter = MoviesAdapter(ArrayList(0)) { view, movie ->
            // Execute onClick
            onMovieClick(view, movie)
        }
        moviesRecyclerView.adapter = mMoviesAdapter
    }

    // ==========================================================================================
    // ACTIONS
    // ==========================================================================================

    private fun onMovieClick(view: View, movie: Movie) {
        val p1 = Pair.create(view.moviePoster as View, activity.getString(R.string.transition_movie_poster))
        val p2 = Pair.create(view.movieTitle as View, activity.getString(R.string.transition_movie_title))
        val p3 = Pair.create(view.movieMask as View, activity.getString(R.string.transition_movie_mask))
        val options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, p1, p2, p3)
        val intent = Intent(activity, MovieDetailsActivity::class.java)
        intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE, movie)
        startActivity(intent, options.toBundle())
    }

    // ==========================================================================================
    // VIEW
    // ==========================================================================================

    override fun emptyList() {
        if (mMoviesAdapter.itemCount == 0)
            emptyList.visibility = View.VISIBLE
    }

    override fun displayMovies(movies: List<Movie>) {
        emptyList.visibility = View.GONE
        mMoviesAdapter.add(movies)
    }

    override fun showLoading() {
        moviesProgressBar.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        moviesProgressBar.visibility = View.GONE
    }

}