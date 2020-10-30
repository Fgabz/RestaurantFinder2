package com.fanilo.home

import com.fanilo.core.annotation.PerFragment
import com.fanilo.domain.FetchRestaurantUseCase
import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.LatitudeLongitudeBounds
import javax.inject.Inject

@PerFragment
class MapController @Inject constructor(private val fetchRestaurantUseCase: FetchRestaurantUseCase) : IMapController {

    override suspend fun onViewReady(cameraBounds: LatitudeLongitudeBounds, latitudeLongitude: LatitudeLongitude) {
        fetchRestaurantUseCase.fetchRestaurant(cameraBounds, latitudeLongitude)
    }
}