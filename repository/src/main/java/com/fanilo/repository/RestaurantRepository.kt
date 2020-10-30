package com.fanilo.repository

import com.fanilo.domain.repository.IRestaurantDataSource
import com.fanilo.entity.Answer
import com.fanilo.entity.restaurant.Restaurant
import com.fanilo.repository.service.IRemoteFoursquareService
import javax.inject.Inject

class RestaurantRepository @Inject constructor(private val remoteFoursquareService: IRemoteFoursquareService): IRestaurantDataSource {

    override suspend fun fetchNearRestaurant(coordinate: String): Answer<List<Restaurant>> =
        remoteFoursquareService.fetchNearRestaurant(coordinate)

}