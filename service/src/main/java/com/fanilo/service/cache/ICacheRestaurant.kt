package com.fanilo.service.cache

import com.fanilo.entity.restaurant.Restaurant

interface ICacheRestaurant {
    fun getAll(): List<Restaurant>
    fun updateAll(list: List<Restaurant>)
    fun flushAll()
}