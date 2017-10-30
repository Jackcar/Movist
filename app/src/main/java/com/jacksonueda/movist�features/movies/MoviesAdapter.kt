package com.jacksonueda.movist.features.movies

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jacksonueda.movist.R
import com.jacksonueda.movist.model.Movie
import com.jacksonueda.movist.utils.Utils
import kotlinx.android.synthetic.main.movie_item.view.*

/**
 * Created by Jackson on 29/09/17.
 */
class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private lateinit var mMovies: MutableList<Movie>

    private var mListener: (Movie) -> Unit

    constructor(movies: List<Movie>, listener: (Movie) -> Unit) {
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
        fun bind(movie: Movie, listener: (Movie) -> Unit) = with(itemView) {
            val backgroundUrl = movie.backdropPath ?: movie.posterPath
            movieTitle.text = movie.title + " (" + Utils.getYear(movie.releaseDate) + ")"
            Utils.loadImage(context.getString(R.string.tmdb_background_url) + backgroundUrl,
                    this.context, moviePoster)
            setOnClickListener { listener(movie) }
        }
    }

}