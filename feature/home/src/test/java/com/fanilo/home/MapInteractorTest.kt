package com.fanilo.home

import com.fanilo.domain.repository.IRestaurantDataSource
import com.fanilo.entity.Answer
import com.fanilo.entity.Coordinate
import com.fanilo.entity.FailureReason
import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.LatitudeLongitudeBounds
import com.fanilo.entity.restaurant.Category
import com.fanilo.entity.restaurant.Restaurant
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MapInteractorTest {

    private lateinit var interactor: MapInteractor

    private val restaurantDataSource: IRestaurantDataSource = mock()
    private val presenter: IMapPresenter = mock()

    @Before
    fun setUp() {
        interactor = MapInteractor(restaurantDataSource, presenter)
    }

    @Test
    fun fetchOnMapReady_Success_Cache_And_Remote() = runBlocking {
        val job = launch {
            interactor.startEventCoordinateEventDebouncer()
        }

        whenever(restaurantDataSource.getCachedRestaurant())
            .then {
                job.cancel()
                return@then Answer.Success(listOf(mockRestaurantInBounds))
            }

        whenever(restaurantDataSource.fetchNearRestaurant("40.908809, 2.0001")).then {
            Answer.Success(listOf(mockRestaurantInBounds))
        }

        interactor.fetchRestaurant(mockBounds, LatitudeLongitude(40.908809, 2.0001))

        job.join()
        verify(restaurantDataSource).getCachedRestaurant()
        verify(restaurantDataSource).fetchNearRestaurant("40.908809, 2.0001")
        verify(presenter, times(2)).displayRestaurant(listOf(mockRestaurantInBounds))

        return@runBlocking
    }

    @Test
    fun fetchOnMapReady_Remote_Only() = runBlocking {
        val job = launch {
            interactor.startEventCoordinateEventDebouncer()
        }

        whenever(restaurantDataSource.getCachedRestaurant())
            .then {
                job.cancel()
                return@then Answer.Failure(Throwable(), "Error in Cache", FailureReason.CACHE)
            }

        whenever(restaurantDataSource.fetchNearRestaurant("40.908809, 2.0001")).then {
            Answer.Success(listOf(mockRestaurantInBounds))
        }

        interactor.fetchRestaurant(mockBounds, LatitudeLongitude(40.908809, 2.0001))

        job.join()
        verify(restaurantDataSource).getCachedRestaurant()
        verify(restaurantDataSource).fetchNearRestaurant("40.908809, 2.0001")
        verify(presenter).displayRestaurant(listOf(mockRestaurantInBounds))

        return@runBlocking
    }

    @Test
    fun fetchOnMapReady_Cache_Only() = runBlocking {
        val job = launch {
            interactor.startEventCoordinateEventDebouncer()
        }

        whenever(restaurantDataSource.getCachedRestaurant())
            .then {
                job.cancel()
                return@then Answer.Success(listOf(mockRestaurantInBounds))
            }

        whenever(restaurantDataSource.fetchNearRestaurant("40.908809, 2.0001")).then {
            Answer.Failure(Throwable(), "Error in remote", FailureReason.NETWORK)
        }

        interactor.fetchRestaurant(mockBounds, LatitudeLongitude(40.908809, 2.0001))

        job.join()
        verify(restaurantDataSource).getCachedRestaurant()
        verify(restaurantDataSource).fetchNearRestaurant("40.908809, 2.0001")
        verify(presenter).displayRestaurant(listOf(mockRestaurantInBounds))

        return@runBlocking
    }

    private companion object {
        val mockBounds = LatitudeLongitudeBounds(
            48.890798078907, 28.89798798, 4.1234234, 1.21312312
        )
        val mockRestaurantInBounds = Restaurant(
            id = "id",
            location = Coordinate(LatitudeLongitude(40.908809, 2.0001), "address", "Cool City"),
            category = listOf(Category("Fancy Restaurant")),
            name = "Very good restaurant"
        )
    }
}