package com.fanilo.home

import com.fanilo.android.BasePresenter
import com.fanilo.entity.restaurant.Restaurant

interface IMapPresenter : BasePresenter<IMapView> {
    suspend fun displayRestaurant(restaurantList: List<Restaurant>)
}