package com.fanilo.home

import com.fanilo.core.annotation.PerFragment
import com.fanilo.core.annotation.extension.contains
import com.fanilo.entity.LatitudeLongitudeBounds
import com.fanilo.entity.restaurant.Restaurant
import javax.inject.Inject

@PerFragment
class MapPresenter @Inject constructor() : IMapPresenter {
    override var view: IMapView? = null

    override suspend fun displayRestaurant(cameraBounds: LatitudeLongitudeBounds, restaurantList: List<Restaurant>) {
        restaurantList.filter {
            cameraBounds.contains(it.location.LatitudeLongitude)
        }
    }
}