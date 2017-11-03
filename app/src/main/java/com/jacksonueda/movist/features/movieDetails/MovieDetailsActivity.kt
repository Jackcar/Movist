package com.jacksonueda.movist.features.movieDetails

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.florent37.glidepalette.GlidePalette
import com.jacksonueda.movist.R
import com.jacksonueda.movist.base.BaseMvpActivity
import com.jacksonueda.movist.model.Genre
import com.jacksonueda.movist.model.Movie
import com.jacksonueda.movist.model.Video
import kotlinx.android.synthetic.main.activity_movie_details.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.image
import org.jetbrains.anko.sdk25.coroutines.onClick

class MovieDetailsActivity : BaseMvpActivity<MovieDetailsContract.View, MovieDetailsContract.Presenter>(), MovieDetailsContract.View {

    override var mPresenter: MovieDetailsContract.Presenter = MovieDetailsPresenter()

    companion object {
        const val EXTRA_MOVIE: String = "EXTRA_MOVIE"
    }

    // ==========================================================================================
    // LIFECYCLE
    // ==========================================================================================

    override fun getLayout(): Int {
        return R.layout.activity_movie_details
    }

    private lateinit var mMovie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mMovie = intent.getSerializableExtra(EXTRA_MOVIE) as Movie

        mPresenter.getMovie(mMovie.id)
        mPresenter.getVideos(mMovie.id)

        setupListener()
    }

    // ==========================================================================================
    // SETUP
    // ==========================================================================================

    private fun setupCollapsingToolBar(title: String) {
        val typeFace = Typeface.createFromAsset(assets, "fonts/Montserrat-Bold.ttf")
        collapsingToolBar.setCollapsedTitleTypeface(typeFace)
        collapsingToolBar.setExpandedTitleTypeface(typeFace)
        collapsingToolBar.title = title.toUpperCase()
    }

    // ==========================================================================================
    // HELPER
    // ==========================================================================================

    private fun setupListener() {
        favoriteButton.onClick {
            mMovie.favorite = !mMovie.favorite
            displayFavorite(mMovie)
            mPresenter.saveMovie(mMovie)
        }
    }

    private fun displayFavorite(movie: Movie) {
        if (movie.favorite)
            favoriteButton.image = getDrawable(R.drawable.ic_favorite)
        else
            favoriteButton.image = getDrawable(R.drawable.ic_favorite_border)
    }

    // ==========================================================================================
    // VIEW
    // ==========================================================================================

    override fun showLoading() {
        movieProgressBar.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        movieProgressBar.visibility = View.GONE
    }

    override fun displayMovieDetails(movie: Movie) {
        val coverUrl = movie.backdropPath ?: movie.posterPath

        setupCollapsingToolBar(movie.title)

        movieStatus.text = movie.status
        movieTitle.text = movie.title
        movieReleaseDate.text = movie.formattedDate()
        movieOverview.text = movie.overview
        movieGenres.text = movie.genreListAsString()
        movieRate.text = movie.voteAverage.toString()
        movieRatingBar.rating = (movie.voteAverage / 2)

        displayFavorite(movie)

        // Cover image
        Glide.with(this)
                .load(getString(R.string.tmdb_cover_url) + coverUrl)
                .listener(
                        GlidePalette.with(getString(R.string.tmdb_background_url) + coverUrl)
                                .intoCallBack { palette ->
                                    if (palette?.darkVibrantSwatch?.rgb != null) {
                                        coordinatorLayout.backgroundTintList = ColorStateList.valueOf(palette?.darkVibrantSwatch?.rgb!!)
                                        viewMask.backgroundTintList = ColorStateList.valueOf(palette?.darkVibrantSwatch?.rgb!!)
                                    }
                                }
                )
                .apply(RequestOptions().centerCrop())
                .into(movieCover)
    }

    override fun loadVideos(videos: List<Video>) {
        val lastVideo = videos[videos.lastIndex]

        if (lastVideo.isFromYoutube()) {
            // Show play button
            playVideoButton.visibility = View.VISIBLE

            playVideoButton.onClick {
                browse(getString(R.string.youtube_base_url) + lastVideo.key)
            }
        }
    }
}