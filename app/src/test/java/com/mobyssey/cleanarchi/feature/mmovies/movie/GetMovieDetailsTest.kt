
package com.mobyssey.cleanarchi.feature.mmovies.movie

import com.mobyssey.cleanarchi.UnitTest
import com.mobyssey.cleanarchi.core.functional.Either
import com.mobyssey.cleanarchi.feature.mmovies.MoviesRepository
import com.mobyssey.cleanarchi.feature.mmovies.movieDetails.GetMovieDetailsUseCase
import com.mobyssey.cleanarchi.feature.mmovies.movieDetails.MovieDetails
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetMovieDetailsTest : UnitTest() {

    private val MOVIE_ID = 1

    private lateinit var getMovieDetails: GetMovieDetailsUseCase

    @Mock private lateinit var moviesRepository: MoviesRepository

    @Before fun setUp() {
        getMovieDetails = GetMovieDetailsUseCase(moviesRepository)
        given { moviesRepository.movieDetails(MOVIE_ID) }.willReturn(Either.Right(MovieDetails.empty()))
    }

    @Test fun `should get data from repository`() {
        runBlocking { getMovieDetails.run(GetMovieDetailsUseCase.Params(MOVIE_ID)) }

        verify(moviesRepository).movieDetails(MOVIE_ID)
        verifyNoMoreInteractions(moviesRepository)
    }
}
