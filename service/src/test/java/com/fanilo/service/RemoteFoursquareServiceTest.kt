package com.fanilo.service

import com.fanilo.core.exception.HttpErrorException
import com.fanilo.entity.Answer
import com.fanilo.entity.Coordinate
import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.restaurant.Category
import com.fanilo.entity.restaurant.Restaurant
import com.fanilo.repository.service.IRemoteFoursquareService
import com.fanilo.service.mapper.IRemoteFoursquareResponseMapper
import com.fanilo.service.webservice.IFoursquareWebService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class RemoteFoursquareServiceTest {

    private lateinit var remote: IRemoteFoursquareService

    private lateinit var webService: IFoursquareWebService
    private val mapper: IRemoteFoursquareResponseMapper = mock()

    private val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        mockWebServer.start()

        webService = MockRemoteClient(mockWebServer).get(IFoursquareWebService::class.java)
        remote = RemoteFoursquareService(webService, mapper)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun fetchNearRestaurant() = runBlocking {
        mockWebServer.prefetchSuccess("restaurant_success.json")

        whenever(mapper.remoteToEntity(any())).thenReturn(mockRestaurant)

        val result = remote.fetchNearRestaurant("45.342342342")

        Assert.assertTrue(result is Answer.Success)
    }

    @Test
    fun fetchNearRestaurant_Exception() = runBlocking {
        mockWebServer.prefetchError(404, "restaurant_success.json")
        val result = remote.fetchNearRestaurant("45.342342342")

        Assert.assertTrue(result is Answer.Failure)
        Assert.assertTrue((result as Answer.Failure).error is HttpErrorException)
    }

    @Test
    fun fetchNearRestaurant_IoException() = runBlocking {
        val result = remote.fetchNearRestaurant("45.342342342")

        Assert.assertTrue(result is Answer.Failure)
        Assert.assertTrue((result as Answer.Failure).error is IOException)
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