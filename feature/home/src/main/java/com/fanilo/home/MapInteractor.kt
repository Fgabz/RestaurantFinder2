package com.fanilo.home

import com.fanilo.core.annotation.PerFragment
import com.fanilo.domain.FetchRestaurantUseCase
import com.fanilo.domain.repository.IRestaurantDataSource
import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.LatitudeLongitudeBounds
import com.fanilo.entity.onFailure
import com.fanilo.entity.onSuccess
import timber.log.Timber
import javax.inject.Inject

@PerFragment
class MapInteractor @Inject constructor(
    private val restaurantDataSource: IRestaurantDataSource,
    private val presenter: IMapPresenter
) : FetchRestaurantUseCase {

    override suspend fun fetchRestaurant(cameraBounds: LatitudeLongitudeBounds, latitudeLongitude: LatitudeLongitude) {
        restaurantDataSource.fetchNearRestaurant("${latitudeLongitude.latitude}, ${latitudeLongitude.longitude}").onSuccess {
            presenter.displayRestaurant(cameraBounds, it)
        }.onFailure { error, message, _ ->
            Timber.e(error, message)
        }
    }
}