package com.jacksonueda.movist.features.movieDetails

import android.os.Bundle
import com.jacksonueda.movist.R
import com.jacksonueda.movist.base.BaseActivity
import com.jacksonueda.movist.features.movies.MoviesFragment
import com.jacksonueda.movist.model.Movie
import com.jacksonueda.movist.utils.Utils

class MovieDetailsActivity : BaseActivity() {

    companion object {
        const val EXTRA_MOVIE: String = "EXTRA_MOVIE"
    }

    // ==========================================================================================
    // LIFECYCLE
    // ==========================================================================================

    override fun getLayout(): Int {
        return R.layout.activity_holder
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movie = intent.getSerializableExtra(EXTRA_MOVIE)

        // Start Signup fragment
        Utils.replaceFragment(supportFragmentManager, R.id.frame_layout_holder, MoviesFragment())
    }

}