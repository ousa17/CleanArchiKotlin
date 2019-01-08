
package com.mobyssey.cleanarchi.feature.mmovies

import com.mobyssey.cleanarchi.core.exception.Failure


class MovieFailure {
    class ListNotAvailable: Failure.FeatureFailure()
    class NonExistentMovie: Failure.FeatureFailure()
}

