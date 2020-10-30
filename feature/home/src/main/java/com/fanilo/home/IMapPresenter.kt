package com.fanilo.home

import com.fanilo.android.BasePresenter
import com.fanilo.entity.LatitudeLongitudeBounds
import com.fanilo.entity.restaurant.Restaurant

interface IMapPresenter : BasePresenter<IMapView> {
    suspend fun displayRestaurant(cameraBounds: LatitudeLongitudeBounds, restaurantList: List<Restaurant>)
}