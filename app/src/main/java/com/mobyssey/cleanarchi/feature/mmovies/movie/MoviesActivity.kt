
package com.mobyssey.cleanarchi.feature.mmovies.movie

import android.content.Context
import android.content.Intent
import com.mobyssey.cleanarchi.core.platform.BaseActivity

class MoviesActivity : BaseActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, MoviesActivity::class.java)
    }

    override fun fragment() = MoviesFragment()
}
