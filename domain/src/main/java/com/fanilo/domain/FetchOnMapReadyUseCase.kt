package com.fanilo.domain

import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.LatitudeLongitudeBounds

interface FetchOnMapReadyUseCase {
    suspend fun fetchRestaurant(cameraBounds: LatitudeLongitudeBounds, latitudeLongitude: LatitudeLongitude)
}