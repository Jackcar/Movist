package com.jacksonueda.movist.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.toolbar.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * Created by Jackson on 11/07/17.
 */

abstract class BaseActivity : AppCompatActivity() {

    abstract fun getLayout(): Int

    // ==========================================================================================
    // LIFECYCLE
    // ==========================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        setupToolbar()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    // ==========================================================================================
    // ACTION
    // ==========================================================================================

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> consume { finish() }
        else -> super.onOptionsItemSelected(item)
    }

    // ==========================================================================================
    // SETUP
    // ==========================================================================================

    private fun setupToolbar() {
        toolbar.let {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(true)
        }
    }

    // ==========================================================================================
    // HELPER
    // ==========================================================================================

    inline fun consume(f: () -> Unit): Boolean {
        f()
        return true
    }
}