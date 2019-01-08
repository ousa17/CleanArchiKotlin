
package com.mobyssey.cleanarchi.feature.mmovies.movie

import com.mobyssey.cleanarchi.core.extension.empty


data class Movie(val id: Int, val poster: String) {

    companion object {
        fun empty() = Movie(0, String.empty())
    }
}
