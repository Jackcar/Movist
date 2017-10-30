package com.jacksonueda.movist.model

/**
 * Created by Jackson on 28/10/17.
 */
enum class Sort(sort: String) {

    POPULARITY("popularity.desc"),
    VOTE_AVERAGE("vote_average.desc"),
    VOTE_COUNT("vote_average.desc");

}