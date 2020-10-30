package com.fanilo.repository.service

import com.fanilo.entity.restaurant.Restaurant

interface ICacheRestaurantService {
    suspend fun getAllRestaurant(): List<Restaurant>
    suspend fun updateRestaurantCache(list: List<Restaurant>)
}