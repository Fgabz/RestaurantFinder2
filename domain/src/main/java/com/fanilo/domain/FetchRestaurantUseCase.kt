package com.fanilo.domain

interface FetchRestaurantUseCase {
    suspend fun fetchRestaurant(latitude: Double, longitude: Double)
}