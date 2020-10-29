package com.fanilo.service.mapper

import com.fanilo.entity.restaurant.Restaurant
import com.fanilo.service.api.model.RemoteVenue

interface IRemoteFoursquareResponseMapper {
    suspend fun remoteToEntity(remote: RemoteVenue): Restaurant
}