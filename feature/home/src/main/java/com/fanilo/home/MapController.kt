package com.fanilo.home

import com.fanilo.core.annotation.PerFragment
import com.fanilo.domain.FetchOnMapReadyUseCase
import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.LatitudeLongitudeBounds
import javax.inject.Inject

@PerFragment
class MapController @Inject constructor(private val fetchRestaurantUseCase: FetchOnMapReadyUseCase) : IMapController {

    override suspend fun onViewReady(cameraBounds: LatitudeLongitudeBounds, latitudeLongitude: LatitudeLongitude) {
        fetchRestaurantUseCase.fetchOnMapReady(cameraBounds, latitudeLongitude)
    }

    override suspend fun onLoadMoreRestaurant(bounds: LatitudeLongitudeBounds, cameraPosition: LatitudeLongitude) {
        fetchRestaurantUseCase.fetchOnMapReady(bounds, cameraPosition)
    }
}