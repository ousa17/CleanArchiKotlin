
package com.mobyssey.cleanarchi.feature.mmovies.movie

import com.mobyssey.cleanarchi.core.interactor.UseCase
import com.mobyssey.cleanarchi.feature.mmovies.MoviesRepository
import javax.inject.Inject

class GetMoviesUseCase
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<List<Movie>, UseCase.None>() {

    override suspend fun run(params: None) = moviesRepository.movies()
}
