package com.fanilo.home

import com.fanilo.core.annotation.PerFragment
import com.fanilo.entity.restaurant.Restaurant
import com.fanilo.home.viewstate.IMarkerViewStateMapper
import javax.inject.Inject

@PerFragment
class MapPresenter @Inject constructor(private val mapper: IMarkerViewStateMapper) : IMapPresenter {
    override var view: IMapView? = null

    override suspend fun displayRestaurant(restaurantList: List<Restaurant>) {
        view?.displayRestaurant(restaurantList.map { mapper.mapEntityToViewState(it) })
    }
}