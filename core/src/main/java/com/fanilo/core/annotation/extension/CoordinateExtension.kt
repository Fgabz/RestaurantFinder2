package com.fanilo.core.annotation.extension

import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.LatitudeLongitudeBounds

fun LatitudeLongitudeBounds.containsLatitude(latitude: Double): Boolean {
    return (latitude <= this.latitudeNorth
        && latitude >= this.latitudeSouth)
}

fun LatitudeLongitudeBounds.containsLongitude(longitude: Double): Boolean {
    return (longitude <= this.longitudeEast)
        && (longitude >= this.longitudeWest)
}

fun LatitudeLongitudeBounds.contains(latLng: LatitudeLongitude): Boolean {
    return containsLatitude(latLng.latitude)
        && containsLongitude(latLng.longitude)
}