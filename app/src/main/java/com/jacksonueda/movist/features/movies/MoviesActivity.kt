package com.jacksonueda.movist.features.movies

import android.os.Bundle
import com.jacksonueda.movist.base.BaseActivity
import com.jacksonueda.movist.R
import com.jacksonueda.movist.utils.Utils

class MoviesActivity : BaseActivity() {

    // ==========================================================================================
    // LIFECYCLE
    // ==========================================================================================

    override fun getLayout(): Int {
        return R.layout.activity_holder
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Start Signup fragment
        Utils.replaceFragment(supportFragmentManager, R.id.frame_layout_holder, MoviesFragment())
    }

}
