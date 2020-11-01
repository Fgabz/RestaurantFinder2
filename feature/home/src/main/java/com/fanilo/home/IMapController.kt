package com.fanilo.home

import com.fanilo.android.BaseController
import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.LatitudeLongitudeBounds

interface IMapController : BaseController {
    suspend fun onViewReady(cameraBounds: LatitudeLongitudeBounds, latitudeLongitude: LatitudeLongitude)
    suspend fun onLoadMoreRestaurant(bounds: LatitudeLongitudeBounds, cameraPosition: LatitudeLongitude)
}