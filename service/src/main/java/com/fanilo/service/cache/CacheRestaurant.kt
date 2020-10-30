package com.fanilo.service.cache

import com.fanilo.entity.restaurant.Restaurant
import java.util.concurrent.ConcurrentMap
import javax.inject.Inject

class CacheRestaurant @Inject constructor(
    private val map: ConcurrentMap<String, Restaurant?>
) : ICacheRestaurant {

    override fun getAll() = map.values.filterNotNull()

    override fun updateAll(list: List<Restaurant>) {
        list.forEach {
            map[it.id] = it
        }
    }

    override fun flushAll() = map.clear()
}