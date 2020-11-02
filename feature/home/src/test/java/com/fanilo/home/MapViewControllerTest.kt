package com.fanilo.home

import androidx.core.os.bundleOf
import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.LatitudeLongitudeBounds
import com.fanilo.home.viewstate.MarkerViewState
import com.fanilo.test.AsyncLiveDataRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule

class MapViewControllerTest {

    @get:Rule
    var rule: TestRule = AsyncLiveDataRule()

    private lateinit var viewController: MapViewController

    private val presenter: IMapPresenter = mock()
    private val controller: IMapController = mock()

    @Before
    fun setUp() {
        viewController = MapViewController(presenter, controller)
    }

    @Test
    fun onCreate() {
        viewController.onCreate()
        verify(presenter).onAttachView(viewController)
    }

    @Test
    fun onViewReady() = runBlocking {
        viewController.onViewReady(bundleOf()).join()
        verify(controller).onViewReady()
    }

    @Test
    fun onViewFinished() {
        viewController.onViewFinished()
        verify(presenter).onDetachView()
    }

    @Test
    fun displayRestaurant() {
        viewController.displayRestaurant(
            listOf(
                MarkerViewState(
                    Pair(45.342343, 85.3343434),
                    "Fqncy restaurant",
                    "address"
                )
            )
        )

        assertEquals(
            viewController.liveDataRestaurantViewState.value, listOf(
                MarkerViewState(
                    Pair(45.342343, 85.3343434),
                    "Fqncy restaurant",
                    "address"
                )
            )
        )
    }

    @Test
    fun onMapReady() = runBlocking {
        viewController.onMapReady(
            LatitudeLongitudeBounds(34.345345, 34.45, 5.45435, 55.5435),
            LatitudeLongitude(7.34, 56.53453425)
        ).join()

        verify(controller).onMapReady(
            LatitudeLongitudeBounds(34.345345, 34.45, 5.45435, 55.5435),
            LatitudeLongitude(7.34, 56.53453425)
        )
    }

    @Test
    fun onLoadMoreRestaurant() = runBlocking {
        viewController.onLoadMoreRestaurant(
            LatitudeLongitudeBounds(34.345345, 34.45, 5.45435, 55.5435),
            LatitudeLongitude(7.34, 56.53453425)
        ).join()

        verify(controller).onLoadMoreRestaurant(
            LatitudeLongitudeBounds(34.345345, 34.45, 5.45435, 55.5435),
            LatitudeLongitude(7.34, 56.53453425)

        )
    }
}