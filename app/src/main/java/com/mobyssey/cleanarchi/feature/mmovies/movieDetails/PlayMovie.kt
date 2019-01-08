
package com.mobyssey.cleanarchi.feature.mmovies.movieDetails

import android.content.Context
import com.mobyssey.cleanarchi.core.exception.Failure
import com.mobyssey.cleanarchi.core.functional.Either
import com.mobyssey.cleanarchi.core.interactor.UseCase
import com.mobyssey.cleanarchi.core.navigation.Navigator
import javax.inject.Inject

class PlayMovie
@Inject constructor(private val context: Context,
                    private val navigator: Navigator
) : UseCase<UseCase.None, PlayMovie.Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        navigator.openVideo(context, params.url)
        return Either.Right(None())
    }

    data class Params(val url: String)
}
