package com.fanilo.home.viewstate

import com.fanilo.entity.restaurant.Restaurant
import javax.inject.Inject

class MarkerViewStateMapper @Inject constructor() : IMarkerViewStateMapper {
    override fun mapEntityToViewState(entity: Restaurant) = MarkerViewState(
        Pair(entity.location.LatitudeLongitude.latitude, entity.location.LatitudeLongitude.longitude),
        entity.category.firstOrNull()?.name ?: "Generic name"
    )
}