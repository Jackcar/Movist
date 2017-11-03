package com.jacksonueda.movist.features.movies

import android.content.res.ColorStateList
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.florent37.glidepalette.GlidePalette
import com.jacksonueda.movist.R
import com.jacksonueda.movist.model.Movie
import com.jacksonueda.movist.utils.Utils
import kotlinx.android.synthetic.main.movie_item.view.*

/**
 * Created by Jackson on 28/10/17.
 */
class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private lateinit var mMovies: MutableList<Movie>

    private var mListener: (View, Movie) -> Unit

    constructor(movies: List<Movie>, listener: (View, Movie) -> Unit) {
        mListener = listener
        set(movies)
    }

    fun set(movies: List<Movie>) {
        mMovies = movies.toMutableList()
        notifyDataSetChanged()
    }

    fun add(movies: List<Movie>) {
        mMovies.addAll(movies)
        notifyDataSetChanged()
    }

    fun clear() {
        mMovies.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount() = mMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(mMovies[position], mListener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie, listener: (View, Movie) -> Unit) = with(itemView) {
            val coverUrl = movie.backdropPath ?: movie.posterPath

            // Transition names
            ViewCompat.setTransitionName(movieTitle, movie.title)
            ViewCompat.setTransitionName(movieMask, movie.title + "_mask")
            ViewCompat.setTransitionName(moviePoster, movie.title + "_image")
            ViewCompat.setTransitionName(movieRate, movie.title + "_rate")

            movieTitle.text = movie.title + " (" + Utils.getYear(movie.releaseDate) + ")"
            movieRate.text = movie.voteAverage.toString()

            // Background cover
            Glide.with(this)
                    .load(context.getString(R.string.tmdb_cover_url) + coverUrl)
                    .listener(
                            GlidePalette.with(context.getString(R.string.tmdb_background_url) + coverUrl)
                                    .intoCallBack { palette ->
                                        if (palette?.darkVibrantSwatch?.rgb != null)
                                            movieMask.backgroundTintList = ColorStateList.valueOf(palette?.darkVibrantSwatch?.rgb!!)
                                    }
                    )
                    .apply(RequestOptions().centerCrop())
                    .into(moviePoster)

            setOnClickListener { listener(itemView, movie) }
        }
    }

}