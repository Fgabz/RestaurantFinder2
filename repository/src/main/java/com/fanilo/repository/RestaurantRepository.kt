package com.fanilo.repository

import com.fanilo.domain.repository.IRestaurantDataSource
import com.fanilo.entity.Answer
import com.fanilo.entity.LatitudeLongitudeBounds
import com.fanilo.entity.onSuccess
import com.fanilo.entity.restaurant.Restaurant
import com.fanilo.repository.service.ICacheRestaurantService
import com.fanilo.repository.service.IRemoteFoursquareService
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val remoteFoursquareService: IRemoteFoursquareService,
    private val cacheService: ICacheRestaurantService
) : IRestaurantDataSource {

    override suspend fun fetchNearRestaurant(coordinate: String): Answer<List<Restaurant>> {
        return remoteFoursquareService.fetchNearRestaurant(coordinate).onSuccess {
            cacheService.updateRestaurantCache(it)
        }
    }

    override suspend fun getCachedRestaurant(): Answer<List<Restaurant>> {
        return Answer.Success(cacheService.getAllRestaurant())
    }
}