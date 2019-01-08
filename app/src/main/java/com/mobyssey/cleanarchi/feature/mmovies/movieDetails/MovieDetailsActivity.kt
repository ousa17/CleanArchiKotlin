
package com.mobyssey.cleanarchi.feature.mmovies.movieDetails

import android.content.Context
import android.content.Intent
import com.mobyssey.cleanarchi.core.platform.BaseActivity
import com.mobyssey.cleanarchi.feature.mmovies.movie.MovieView

class MovieDetailsActivity : BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_MOVIE = "com.fernandocejas.INTENT_PARAM_MOVIE"

        fun callingIntent(context: Context, movie: MovieView): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_MOVIE, movie)
            return intent
        }
    }

    override fun fragment() = MovieDetailsFragment.forMovie(intent.getParcelableExtra(INTENT_EXTRA_PARAM_MOVIE))
}
