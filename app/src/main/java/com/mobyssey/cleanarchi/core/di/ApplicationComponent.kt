
package com.mobyssey.cleanarchi.core.di

import com.mobyssey.cleanarchi.core.di.viewmodel.ViewModelModule
import com.mobyssey.cleanarchi.AndroidApplication
import com.mobyssey.cleanarchi.core.navigation.RouteActivity
import com.mobyssey.cleanarchi.feature.mmovies.movie.MoviesFragment
import com.mobyssey.cleanarchi.feature.mmovies.movieDetails.MovieDetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(routeActivity: RouteActivity)
    fun inject(moviesFragment: MoviesFragment)
    fun inject(movieDetailsFragment: MovieDetailsFragment)

}
