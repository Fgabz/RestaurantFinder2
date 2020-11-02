package com.fanilo.home.viewstate

import com.fanilo.entity.Coordinate
import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.restaurant.Category
import com.fanilo.entity.restaurant.Restaurant
import org.junit.Assert.assertEquals
import org.junit.Test

class MarkerViewStateMapperTest {

    private val mapper = MarkerViewStateMapper()

    @Test
    fun mapEntityToViewState() {
        val actual = mapper.mapEntityToViewState(mockRestaurant)

        assertEquals(
            MarkerViewState(
                Pair(48.890798078907, 58.89798798),
                "Very good restaurant",
                "address"
            ), actual
        )
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