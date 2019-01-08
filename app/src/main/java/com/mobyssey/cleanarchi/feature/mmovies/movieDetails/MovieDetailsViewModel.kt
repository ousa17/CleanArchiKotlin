
package com.mobyssey.cleanarchi.feature.mmovies.movieDetails

import android.arch.lifecycle.MutableLiveData
import com.mobyssey.cleanarchi.core.platform.BaseViewModel
import javax.inject.Inject

class MovieDetailsViewModel
@Inject constructor(private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
                    private val playMovie: PlayMovie) : BaseViewModel() {

    var movieDetails: MutableLiveData<MovieDetailsView> = MutableLiveData()

    fun loadMovieDetails(movieId: Int) =
        getMovieDetailsUseCase(GetMovieDetailsUseCase.Params(movieId)) { it.either(::handleFailure, ::handleMovieDetails) }

    fun playMovie(url: String) = playMovie(PlayMovie.Params(url))

    private fun handleMovieDetails(movie: MovieDetails) {
        this.movieDetails.value = MovieDetailsView(movie.id, movie.title, movie.poster,
                movie.summary, movie.cast, movie.director, movie.year, movie.trailer)
    }
}