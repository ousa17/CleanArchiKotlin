
package com.mobyssey.cleanarchi.feature.mmovies.movie

import android.arch.lifecycle.MutableLiveData
import com.mobyssey.cleanarchi.core.interactor.UseCase
import com.mobyssey.cleanarchi.core.platform.BaseViewModel
import javax.inject.Inject

class MoviesViewModel
@Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) : BaseViewModel() {

    var movies: MutableLiveData<List<MovieView>> = MutableLiveData()

    fun loadMovies() = getMoviesUseCase(UseCase.None()) { it.either(::handleFailure, ::handleMovieList) }

    private fun handleMovieList(movies: List<Movie>) {
        this.movies.value = movies.map { MovieView(it.id, it.poster) }
    }
}