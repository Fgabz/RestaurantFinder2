package com.fanilo.home.viewstate

data class MarkerViewState(
    val latLng: Pair<Double, Double>,
    val restaurantName: String,
    val restaurantAddress: String
)