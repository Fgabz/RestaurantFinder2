package com.fanilo.service.cache

import com.fanilo.entity.Coordinate
import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.restaurant.Category
import com.fanilo.entity.restaurant.Restaurant
import com.fanilo.repository.service.ICacheRestaurantService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class CacheRestaurantServiceTest {

    private lateinit var service: ICacheRestaurantService

    private val cache: ICacheRestaurant = mock()

    @Before
    fun setUp() {
        service = CacheRestaurantService(cache)
    }

    @Test
    fun getAllRestaurant() = runBlocking {
        service.getAllRestaurant()
        verify(cache).getAll()

        return@runBlocking
    }

    @Test
    fun updateRestaurantCache() = runBlocking {
        service.updateRestaurantCache(listOf(mockRestaurant))
        verify(cache).updateAll(listOf(mockRestaurant))
    }

    private companion object {
        val mockRestaurant = Restaurant(
            id = "id",
            location = Coordinate(LatitudeLongitude(48.890798078907, 58.89798798), "address", "Cool City"),
            category = listOf(Category("Fancy Restaurant")),
            name = "Very good restaurant"
        )
    }
}