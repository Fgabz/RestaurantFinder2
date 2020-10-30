package com.fanilo.service.mapper

import com.fanilo.entity.Coordinate
import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.restaurant.Category
import com.fanilo.entity.restaurant.Restaurant
import com.fanilo.service.api.model.RemoteVenue
import javax.inject.Inject

class RemoteFoursquareResponseMapper @Inject constructor() : IRemoteFoursquareResponseMapper {

    override suspend fun remoteToEntity(remote: RemoteVenue) = Restaurant(
        id = remote.id,
        location = Coordinate(LatitudeLongitude(remote.location.lat, remote.location.lng), remote.location.address, remote.location.city),
        category = remote.categories.map {
            Category(it.shortName)
        }
    )
}