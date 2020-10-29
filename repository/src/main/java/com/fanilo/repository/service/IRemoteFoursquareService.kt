package com.fanilo.repository.service

import com.fanilo.entity.Answer

interface IRemoteFoursquareService {
    suspend fun fetchNearRestaurant(coordinate: String): Answer<Boolean>

}