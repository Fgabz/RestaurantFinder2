package com.fanilo.service.cache

import com.fanilo.entity.restaurant.Restaurant
import com.fanilo.repository.service.ICacheRestaurantService
import javax.inject.Inject

class CacheRestaurantService @Inject constructor(private val cache: ICacheRestaurant) : ICacheRestaurantService {

    override suspend fun getAllRestaurant() = cache.getAll()

    override suspend fun updateRestaurantCache(list: List<Restaurant>) = cache.updateAll(list)
}