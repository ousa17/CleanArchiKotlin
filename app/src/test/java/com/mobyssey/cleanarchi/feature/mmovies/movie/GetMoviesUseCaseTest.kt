package com.mobyssey.cleanarchi.feature.mmovies.movie

import com.mobyssey.cleanarchi.UnitTest
import com.mobyssey.cleanarchi.core.functional.Either
import com.mobyssey.cleanarchi.core.interactor.UseCase
import com.mobyssey.cleanarchi.feature.mmovies.MoviesRepository
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetMoviesUseCaseTest : UnitTest(){

    private lateinit var getMoviesUserCase : GetMoviesUseCase

    @Mock private lateinit var moviesRepository : MoviesRepository


    @Before fun setUp(){
        getMoviesUserCase = GetMoviesUseCase(moviesRepository)
        given { moviesRepository.movies() }.willReturn(Either.Right(listOf(Movie.empty())))
    }


    @Test fun `should get data from repository` (){
        runBlocking { getMoviesUserCase.run(UseCase.None()) }
        verify(moviesRepository).movies()
        verifyNoMoreInteractions(moviesRepository)
    }


}