package com.fanilo.home

import com.fanilo.core.annotation.PerFragment
import com.fanilo.domain.FetchRestaurantUseCase
import com.fanilo.domain.repository.IRestaurantDataSource
import com.fanilo.entity.onFailure
import com.fanilo.entity.onSuccess
import timber.log.Timber
import javax.inject.Inject

@PerFragment
class MapInteractor @Inject constructor(
    private val restaurantDataSource: IRestaurantDataSource
) : FetchRestaurantUseCase {

    override suspend fun fetchRestaurant(latitude: Double, longitude: Double) {
        restaurantDataSource.fetchNearRestaurant("$latitude, $longitude").onSuccess {

        }.onFailure { error, message, _ ->
            Timber.e(error, message)
        }
    }
}