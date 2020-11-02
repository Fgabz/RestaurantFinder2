package com.fanilo.home

import com.fanilo.core.annotation.PerFragment
import com.fanilo.domain.FetchOnMapReadyUseCase
import com.fanilo.domain.StartCoordinateEvenDebouncerUseCase
import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.LatitudeLongitudeBounds
import javax.inject.Inject

@PerFragment
class MapController @Inject constructor(
    private val fetchRestaurantUseCase: FetchOnMapReadyUseCase,
    private val coordinateEvenDebouncerUseCase: StartCoordinateEvenDebouncerUseCase
) : IMapController {

    override suspend fun onViewReady() {
        coordinateEvenDebouncerUseCase.startEventCoordinateEventDebouncer()
    }

    override suspend fun onMapReady(cameraBounds: LatitudeLongitudeBounds, latitudeLongitude: LatitudeLongitude) {
        fetchRestaurantUseCase.fetchRestaurant(cameraBounds, latitudeLongitude)
    }

    override suspend fun onLoadMoreRestaurant(bounds: LatitudeLongitudeBounds, cameraPosition: LatitudeLongitude) {
        fetchRestaurantUseCase.fetchRestaurant(bounds, cameraPosition)
    }
}