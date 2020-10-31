package com.fanilo.home.viewstate

import com.fanilo.core.annotation.PerFragment
import com.fanilo.entity.restaurant.Restaurant
import javax.inject.Inject

@PerFragment
class MarkerViewStateMapper @Inject constructor() : IMarkerViewStateMapper {
    override fun mapEntityToViewState(entity: Restaurant) = MarkerViewState(
        Pair(entity.location.LatitudeLongitude.latitude, entity.location.LatitudeLongitude.longitude),
        entity.name,
        entity.location.address
    )
}