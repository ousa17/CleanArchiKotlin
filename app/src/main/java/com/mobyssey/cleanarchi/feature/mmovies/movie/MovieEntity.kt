
package com.mobyssey.cleanarchi.feature.mmovies.movie

data class MovieEntity(private val id: Int, private val poster: String) {
    fun toMovie() = Movie(id, poster)
}
