
package com.mobyssey.cleanarchi.feature.mmovies.movieDetails

import com.mobyssey.cleanarchi.core.interactor.UseCase
import com.mobyssey.cleanarchi.feature.mmovies.MoviesRepository
import javax.inject.Inject

class GetMovieDetailsUseCase
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<MovieDetails, GetMovieDetailsUseCase.Params>() {

    override suspend fun run(params: Params) = moviesRepository.movieDetails(params.id)

    data class Params(val id: Int)
}
