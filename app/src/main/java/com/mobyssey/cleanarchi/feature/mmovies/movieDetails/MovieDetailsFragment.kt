
package com.mobyssey.cleanarchi.feature.mmovies.movieDetails

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import com.mobyssey.cleanarchi.R
import com.mobyssey.cleanarchi.core.exception.Failure
import com.mobyssey.cleanarchi.core.extension.*
import com.mobyssey.cleanarchi.core.platform.BaseFragment
import com.mobyssey.cleanarchi.feature.mmovies.MovieFailure.NonExistentMovie
import com.mobyssey.cleanarchi.feature.mmovies.movie.MovieView
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.toolbar.toolbar
import javax.inject.Inject

class MovieDetailsFragment : BaseFragment() {

    companion object {
        private const val PARAM_MOVIE = "param_movie"

        fun forMovie(movie: MovieView): MovieDetailsFragment {
            val movieDetailsFragment = MovieDetailsFragment()
            val arguments = Bundle()
            arguments.putParcelable(PARAM_MOVIE, movie)
            movieDetailsFragment.arguments = arguments

            return movieDetailsFragment
        }
    }

    @Inject lateinit var movieDetailsAnimator: MovieDetailsAnimator

    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    override fun layoutId() = R.layout.fragment_movie_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        activity?.let { movieDetailsAnimator.postponeEnterTransition(it) }

        movieDetailsViewModel = viewModel(viewModelFactory) {
            observe(movieDetails, ::renderMovieDetails)
            failure(failure, ::handleFailure)
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (firstTimeCreated(savedInstanceState)) {
            movieDetailsViewModel.loadMovieDetails((arguments?.get(PARAM_MOVIE) as MovieView).id)
        } else {
            movieDetailsAnimator.scaleUpView(moviePlay)
            movieDetailsAnimator.cancelTransition(moviePoster)
            moviePoster.loadFromUrl((arguments!![PARAM_MOVIE] as MovieView).poster)
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBackPressed() {
        movieDetailsAnimator.fadeInvisible(scrollView, movieDetails)
        if (moviePlay.isVisible())
            movieDetailsAnimator.scaleDownView(moviePlay)
        else
            movieDetailsAnimator.cancelTransition(moviePoster)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun renderMovieDetails(movie: MovieDetailsView?) {
        movie?.let {
            with(movie) {
                activity?.let {
                    moviePoster.loadUrlAndPostponeEnterTransition(poster, it)
                    it.toolbar.title = title
                }
                movieSummary.text = summary
                movieCast.text = cast
                movieDirector.text = director
                movieYear.text = year.toString()
                moviePlay.setOnClickListener { movieDetailsViewModel.playMovie(trailer) }
            }
        }
        movieDetailsAnimator.fadeVisible(scrollView, movieDetails)
        movieDetailsAnimator.scaleUpView(moviePlay)
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> { notify(R.string.failure_network_connection); close() }
            is Failure.ServerError -> { notify(R.string.failure_server_error); close() }
            is NonExistentMovie -> { notify(R.string.failure_movie_non_existent); close() }
        }
    }
}
