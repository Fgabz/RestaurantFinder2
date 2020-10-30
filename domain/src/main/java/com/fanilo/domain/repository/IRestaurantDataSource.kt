package com.fanilo.domain.repository

import com.fanilo.entity.Answer
import com.fanilo.entity.restaurant.Restaurant

interface IRestaurantDataSource {
    suspend fun fetchNearRestaurant(coordinate: String): Answer<List<Restaurant>>
}