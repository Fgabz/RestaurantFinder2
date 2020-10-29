package com.fanilo.repository.service

import com.fanilo.entity.Answer
import com.fanilo.entity.restaurant.Restaurant

interface IRemoteFoursquareService {
    suspend fun fetchNearRestaurant(coordinate: String): Answer<List<Restaurant>>
}