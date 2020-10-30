package com.fanilo.entity

data class Coordinate(
    val LatitudeLongitude: LatitudeLongitude,
    val address: String,
    val city: String
)

data class LatitudeLongitude(
    val latitude: Double,
    val longitude: Double
)

data class LatitudeLongitudeBounds(
    val latitudeNorth: Double,
    val latitudeSouth: Double,
    val longitudeEast: Double,
    val longitudeWest: Double
)