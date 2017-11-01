package com.jacksonueda.movist.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

/**
 * Created by jackson.junior.ext on 30/10/17.
 */

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, func: (FragmentTransaction.() -> FragmentTransaction)? = null) {
    if (func != null)
        supportFragmentManager.inTransaction { replace(frameId, fragment).func() }
    else
        supportFragmentManager.inTransaction { replace(frameId, fragment) }
}