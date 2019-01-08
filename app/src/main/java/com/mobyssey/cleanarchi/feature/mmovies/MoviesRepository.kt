
package com.mobyssey.cleanarchi.feature.mmovies

import com.mobyssey.cleanarchi.core.exception.Failure
import com.mobyssey.cleanarchi.core.functional.Either
import com.mobyssey.cleanarchi.core.platform.NetworkHandler
import com.mobyssey.cleanarchi.feature.mmovies.movie.Movie
import com.mobyssey.cleanarchi.feature.mmovies.movieDetails.MovieDetails
import com.mobyssey.cleanarchi.feature.mmovies.movieDetails.MovieDetailsEntity
import retrofit2.Call
import javax.inject.Inject

interface MoviesRepository {

    fun movies(): Either<Failure, List<Movie>>
    fun movieDetails(movieId: Int): Either<Failure, MovieDetails>


    class Network
    @Inject constructor(private val networkHandler: NetworkHandler, private val service: MoviesService) :
        MoviesRepository {

        override fun movies(): Either<Failure, List<Movie>> {
            return when (networkHandler.isConnected) {
                true -> request(service.movies(), { it.map { it.toMovie() } }, emptyList())
                false, null -> Either.Left(Failure.NetworkConnection)
            }
        }

        override fun movieDetails(movieId: Int): Either<Failure, MovieDetails> {
            return when (networkHandler.isConnected) {
                true -> request(service.movieDetails(movieId), { it.toMovieDetails() }, MovieDetailsEntity.empty())
                false, null -> Either.Left(Failure.NetworkConnection)
            }
        }


        private fun <T, R> request(
            call: Call<T>,
            transform: (T) -> R,
            default: T
        ): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Either.Right(transform((response.body() ?: default)))
                    false -> Either.Left(Failure.ServerError)
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }
    }
}
